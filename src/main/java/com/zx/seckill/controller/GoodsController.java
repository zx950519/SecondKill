package com.zx.seckill.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.seckill.dao.MiaoshaUserDao;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import com.zx.seckill.domain.MiaoshaUser;
import com.zx.seckill.redis.GoodsKey;
import com.zx.seckill.redis.RedisService;
import com.zx.seckill.result.Result;
import com.zx.seckill.service.GoodsService;
import com.zx.seckill.service.MiaoshaUserService;
import com.zx.seckill.vo.GoodsDetailVo;
import com.zx.seckill.vo.GoodsVo;

/**
 * @Description:商品控制器
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	MiaoshaUserService userService;
	@Autowired
	RedisService redisService;
	@Autowired
	GoodsService goodsService;
	@Autowired
	ThymeleafViewResolver thymeleafViewResolver;
	@Autowired
	ApplicationContext applicationContext;

	@Autowired
    MiaoshaUserDao miaoshaUserDao;

    // 测试版查询商品列表页
    @PostMapping(value="/list")
    @ResponseBody
    public List<GoodsVo> listAllOfObject(HttpServletRequest request, HttpServletResponse response) {
//        model.addAttribute("user", user);
        List<GoodsVo> goodsList = goodsService.listGoodsVo();   // 获取商品列表
//        model.addAttribute("goodsList", goodsList);          // 加入返回数据中
        return goodsList;
    }

	// 稳定版查询商品列表页(String类型)
    @RequestMapping(value="/to_list", produces="text/html")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response,
					   Model model, MiaoshaUser user) {
    	model.addAttribute("user", user);
    	//取缓存
//    	String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
//    	if(!StringUtils.isEmpty(html)) {
//    		return html;
//    	}
    	List<GoodsVo> goodsList = goodsService.listGoodsVo();   // 获取商品列表
    	model.addAttribute("goodsList", goodsList);          // 加入返回数据中
//    	 return "goods_list";
    	SpringWebContext ctx = new SpringWebContext(request,response,
    			request.getServletContext(),
                request.getLocale(),
                model.asMap(),
                applicationContext );
    	// 手动渲染页面
    	String html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
    	if(!StringUtils.isEmpty(html)) {
    		redisService.set(GoodsKey.getGoodsList, "", html);
    	}
    	return html;
    }

    // 根据秒杀用户实体与商品号查询秒杀商品页(String类型)
    @RequestMapping(value="/to_detail2/{goodsId}",produces="text/html")
    @ResponseBody
    public String detail2(HttpServletRequest request, HttpServletResponse response, Model model, MiaoshaUser user,
    		@PathVariable("goodsId")long goodsId) {
    	model.addAttribute("user", user);
    	// 取缓存
    	String html = redisService.get(GoodsKey.getGoodsDetail, ""+goodsId, String.class);
    	if(!StringUtils.isEmpty(html)) {
    		return html;
    	}
    	// 手动渲染
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	model.addAttribute("goods", goods);     // 添加商品实体
    	long startAt = goods.getStartDate().getTime();
    	long endAt = goods.getEndDate().getTime();
    	long now = System.currentTimeMillis();
    	int miaoshaStatus = 0;
    	int remainSeconds = 0;
    	if(now < startAt ) {    // 秒杀还没开始，倒计时
    		miaoshaStatus = 0;
    		remainSeconds = (int)((startAt - now )/1000);
    	}else  if(now > endAt){ // 秒杀已经结束
    		miaoshaStatus = 2;
    		remainSeconds = -1;
    	}else {//秒杀进行中
    		miaoshaStatus = 1;
    		remainSeconds = 0;
    	}
    	model.addAttribute("miaoshaStatus", miaoshaStatus);     // 添加秒杀状态
    	model.addAttribute("remainSeconds", remainSeconds);     // 添加秒杀倒计时
    	
    	SpringWebContext ctx = new SpringWebContext(request,response,
    			request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
    	html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", ctx);
    	if(!StringUtils.isEmpty(html)) {
    		redisService.set(GoodsKey.getGoodsDetail, ""+goodsId, html);
    	}
    	return html;
    }

    // 稳定版—查询商品细节
    @RequestMapping(value="/detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> detail(HttpServletRequest request, HttpServletResponse response, Model model,MiaoshaUser user,
    		@PathVariable("goodsId")long goodsId) {
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	long startAt = goods.getStartDate().getTime();
    	long endAt = goods.getEndDate().getTime();
    	long now = System.currentTimeMillis();
    	int miaoshaStatus = 0;
    	int remainSeconds = 0;
    	if(now < startAt ) {//秒杀还没开始，倒计时
    		miaoshaStatus = 0;
    		remainSeconds = (int)((startAt - now )/1000);
    	}else  if(now > endAt){//秒杀已经结束
    		miaoshaStatus = 2;
    		remainSeconds = -1;
    	}else {//秒杀进行中
    		miaoshaStatus = 1;
    		remainSeconds = 0;
    	}
    	GoodsDetailVo vo = new GoodsDetailVo();
    	vo.setGoods(goods);
    	vo.setUser(user);
    	vo.setRemainSeconds(remainSeconds);
    	vo.setMiaoshaStatus(miaoshaStatus);
    	return Result.success(vo);
    }
    // 测试版—查询商品细节
    @PostMapping(value="/mydetail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> midetail(@RequestParam(value = "goodsId")long goodsId,
                                          @RequestParam(value = "mobile") String mobile) {
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if(now < startAt ) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int)((startAt - now )/1000);
        }else  if(now > endAt){//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        GoodsDetailVo vo = new GoodsDetailVo();
        vo.setGoods(goods);
        MiaoshaUser user = miaoshaUserDao.getById(Long.parseLong(mobile));
        vo.setUser(user);
        vo.setRemainSeconds(remainSeconds);
        vo.setMiaoshaStatus(miaoshaStatus);
        return Result.success(vo);
    }

}
