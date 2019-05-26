package com.zx.seckill.controller;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.seckill.dao.MiaoshaUserDao;
import com.zx.seckill.rabbitmq.MiaoshaMessage;
import com.zx.seckill.result.Result;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.zx.seckill.access.AccessLimit;
import com.zx.seckill.domain.MiaoshaOrder;
import com.zx.seckill.domain.MiaoshaUser;
import com.zx.seckill.rabbitmq.MQSender;
import com.zx.seckill.redis.GoodsKey;
import com.zx.seckill.redis.MiaoshaKey;
import com.zx.seckill.redis.OrderKey;
import com.zx.seckill.redis.RedisService;
import com.zx.seckill.result.CodeMsg;
import com.zx.seckill.service.GoodsService;
import com.zx.seckill.service.MiaoshaService;
import com.zx.seckill.service.MiaoshaUserService;
import com.zx.seckill.service.OrderService;
import com.zx.seckill.vo.GoodsVo;

/**
 * @Description:主入口
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean {

	@Autowired
	MiaoshaUserService userService;
	@Autowired
	RedisService redisService;
	@Autowired
	GoodsService goodsService;
	@Autowired
	OrderService orderService;
	@Autowired
	MiaoshaService miaoshaService;
	@Autowired
	MQSender sender;

	@Autowired
    MiaoshaUserDao miaoshaUserDao;
	
	private HashMap<Long, Boolean> localOverMap =  new HashMap<Long, Boolean>();
	
	// 系统初始化
	public void afterPropertiesSet() throws Exception {
		List<GoodsVo> goodsList = goodsService.listGoodsVo();       // 获取商品列表
		if(goodsList == null) {
			return;
		}
		for(GoodsVo goods : goodsList) {
			redisService.set(GoodsKey.getMiaoshaGoodsStock, ""+goods.getId(), goods.getStockCount());   // 写入缓存
			localOverMap.put(goods.getId(), false);     // 更新本地卖超标志
		}
	}

	// 秒杀系统一键还原
	@RequestMapping(value="/reset", method=RequestMethod.GET)
    @ResponseBody
    public Result<Boolean> reset(Model model) {
		List<GoodsVo> goodsList = goodsService.listGoodsVo();       // 获取商品列表
		for(GoodsVo goods : goodsList) {
			goods.setStockCount(10);        // 重置商品库存
			redisService.set(GoodsKey.getMiaoshaGoodsStock, ""+goods.getId(), 10);      // 写入缓存
			localOverMap.put(goods.getId(), false);     // 更新本地卖超标志
		}
		redisService.delete(OrderKey.getMiaoshaOrderByUidGid);      // 删除订单
		redisService.delete(MiaoshaKey.isGoodsOver);                // 删除卖超Key
		miaoshaService.reset(goodsList);                            // 调用下层的Dao更新DB
		return Result.success(true);
	}
	

	// 稳定版-秒杀业务
    @RequestMapping(value="/{path}/do_miaosha", method=RequestMethod.POST)
    @ResponseBody
    public Result<Integer> miaosha(Model model, MiaoshaUser user,
    		@RequestParam("goodsId")long goodsId,
    		@PathVariable("path") String path) {
    	model.addAttribute("user", user);
    	if(user == null) {
    		return Result.error(CodeMsg.SESSION_ERROR);
    	}
    	// 验证传入的秒杀path是否合法
    	boolean check = miaoshaService.checkPath(user, goodsId, path);
    	if (!check) {
    		return Result.error(CodeMsg.REQUEST_ILLEGAL);
    	}
    	// 通过内存标记，减少redis访问，用于判断是否卖超
    	boolean over = localOverMap.get(goodsId);
    	if (over) {
    		return Result.error(CodeMsg.MIAO_SHA_OVER);
    	}
    	// 预减库存，更新缓存
    	long stock = redisService.decr(GoodsKey.getMiaoshaGoodsStock, ""+goodsId);//10
    	if (stock < 0) {
    	    localOverMap.put(goodsId, true);
    		return Result.error(CodeMsg.MIAO_SHA_OVER);
    	}
    	// 根据用户id以及商品id生成秒杀订单，订单存在与否可判断是否秒杀成功
    	MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
    	if(order != null) {
    		return Result.error(CodeMsg.REPEATE_MIAOSHA);
    	}
    	// 进入消息队列做进一步处理
    	MiaoshaMessage mm = new MiaoshaMessage();
    	mm.setUser(user);
    	mm.setGoodsId(goodsId);
    	sender.sendMiaoshaMessage(mm);
    	return Result.success(0);       // 排队中

    	/*
    	//判断库存
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);//10个商品，req1 req2
    	int stock = goods.getStockCount();
    	if(stock <= 0) {
    		return Result.error(CodeMsg.MIAO_SHA_OVER);
    	}
    	//判断是否已经秒杀到了
    	MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
    	if(order != null) {
    		return Result.error(CodeMsg.REPEATE_MIAOSHA);
    	}
    	//减库存 下订单 写入秒杀订单
    	OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
        return Result.success(orderInfo);
        */
    }

    // 测试版-秒杀业务
    @RequestMapping(value="/{path}/miaosha", method=RequestMethod.POST)
    @ResponseBody
    public Result<Integer> _miaosha(Model model,
                                    @RequestParam(value = "mobile") String mobile,
                                    @RequestParam(value = "goodsId")long goodsId,
                                    @PathVariable String path) {
	    MiaoshaUser user = miaoshaUserDao.getById(Long.parseLong(mobile));
        model.addAttribute("user", user);
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        // 验证传入的秒杀path是否合法
        boolean check = miaoshaService.checkPath(user, goodsId, path);
        if (!check) {
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }
        // 通过内存标记，减少redis访问，用于判断是否卖超
        boolean over = localOverMap.get(goodsId);
        if (over) {
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        // 预减库存，更新缓存
        long stock = redisService.decr(GoodsKey.getMiaoshaGoodsStock, ""+goodsId);//10
        if (stock < 0) {
            localOverMap.put(goodsId, true);
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        // 根据用户id以及商品id生成秒杀订单，订单存在与否可判断是否秒杀成功
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if(order != null) {
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }
        // 进入消息队列做进一步处理
        MiaoshaMessage mm = new MiaoshaMessage();
        mm.setUser(user);
        mm.setGoodsId(goodsId);
        sender.sendMiaoshaMessage(mm);
        return Result.success(0);       // 排队中
    }
    
    /**
     * orderId：成功
     * -1：秒杀失败
     * 0： 排队中
     * */
    // 稳定版-查询秒杀结果
    @RequestMapping(value="/result", method=RequestMethod.GET)
    @ResponseBody
    public Result<Long> miaoshaResult(Model model,MiaoshaUser user,
    		@RequestParam("goodsId")long goodsId) {
    	model.addAttribute("user", user);
    	if(user == null) {
    		return Result.error(CodeMsg.SESSION_ERROR);
    	}
    	long result = miaoshaService.getMiaoshaResult(user.getId(), goodsId);   // 查询订单是否存在
    	return Result.success(result);
    }

    // 测试版-查询秒杀结果
    @PostMapping(value="/getResult")
    @ResponseBody
    public Result<Long> _miaoshaResult(Model model,
                                        @RequestParam(value = "mobile") String mobile,
                                        @RequestParam(value = "goodsId") long goodsId) {
        MiaoshaUser user = miaoshaUserDao.getById(Long.parseLong(mobile));
        model.addAttribute("user", user);
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        long result = miaoshaService.getMiaoshaResult(user.getId(), goodsId);   // 查询订单是否存在
        return Result.success(result);
    }

    // 稳定版-获取秒杀路径(输入参数有一个验证码)
    @AccessLimit(seconds=5, maxCount=5, needLogin=true)
    @RequestMapping(value="/path", method=RequestMethod.GET)
    @ResponseBody
    public Result<String> getMiaoshaPath(HttpServletRequest request, MiaoshaUser user,
    		@RequestParam("goodsId")long goodsId,
    		@RequestParam(value="verifyCode", defaultValue="0")int verifyCode
    		) {
    	if(user == null) {
    		return Result.error(CodeMsg.SESSION_ERROR);
    	}
    	boolean check = miaoshaService.checkVerifyCode(user, goodsId, verifyCode);
    	if(!check) {
    		return Result.error(CodeMsg.REQUEST_ILLEGAL);
    	}
    	String path = miaoshaService.createMiaoshaPath(user, goodsId);      // 创建秒杀路径
    	System.out.println(path);
		return Result.success(path);
    }

    // 测试版-获取秒杀路径(输入参数有一个验证码)
    @AccessLimit(seconds=5, maxCount=5, needLogin=true)
    @PostMapping(value="/getPath")
    @ResponseBody
    public Result<String> getMiaoshaPath(@RequestParam(value = "mobile") String mobile,
                                         @RequestParam(value = "goodsId") long goodsId,
                                         @RequestParam(value="verifyCode", defaultValue="0")int verifyCode) {
        MiaoshaUser user = miaoshaUserDao.getById(Long.parseLong(mobile));
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        boolean check = miaoshaService.checkVerifyCode(user, goodsId, verifyCode);
        if(!check) {
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }
        String path = miaoshaService.createMiaoshaPath(user, goodsId);      // 创建秒杀路径
        System.out.println(path);
        return Result.success(path);
    }

    // 稳定版-获取用于秒杀的校验图片
    @RequestMapping(value="/verifyCode", method=RequestMethod.GET)
    @ResponseBody
    public Result<String> getMiaoshaVerifyCod(HttpServletResponse response, MiaoshaUser user,
    		@RequestParam("goodsId")long goodsId) {
    	if(user == null) {
    		return Result.error(CodeMsg.SESSION_ERROR);
    	}
    	try {
    		BufferedImage image  = miaoshaService.createVerifyCode(user, goodsId);
    		OutputStream out = response.getOutputStream();
    		ImageIO.write(image, "JPEG", out);
    		out.flush();
    		out.close();
    		return null;
    	}catch(Exception e) {
    		e.printStackTrace();
    		return Result.error(CodeMsg.MIAOSHA_FAIL);
    	}
    }

    // 测试版-获取用于秒杀的校验图片
    @PostMapping(value="/verifyImg")
    @ResponseBody
    public Result<String> getMiaoshaVerifyCode(HttpServletResponse response,
                                               @RequestParam(value = "mobile") String mobile,
                                               @RequestParam(value = "goodsId") long goodsId) {
        MiaoshaUser user = miaoshaUserDao.getById(Long.parseLong(mobile));
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        try {
            BufferedImage image  = miaoshaService.createVerifyCode(user, goodsId);
            OutputStream out = response.getOutputStream();
            ImageIO.write(image, "JPEG", out);
            out.flush();
            out.close();
            return null;
        }catch(Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.MIAOSHA_FAIL);
        }
    }
}
