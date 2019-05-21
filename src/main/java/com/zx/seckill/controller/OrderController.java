package com.zx.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.seckill.domain.MiaoshaUser;
import com.zx.seckill.domain.OrderInfo;
import com.zx.seckill.redis.RedisService;
import com.zx.seckill.result.CodeMsg;
import com.zx.seckill.result.Result;
import com.zx.seckill.service.GoodsService;
import com.zx.seckill.service.MiaoshaUserService;
import com.zx.seckill.service.OrderService;
import com.zx.seckill.vo.GoodsVo;
import com.zx.seckill.vo.OrderDetailVo;

/**
 * @Description:订单控制器
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	MiaoshaUserService userService;
	@Autowired
	RedisService redisService;
	@Autowired
	OrderService orderService;
	@Autowired
	GoodsService goodsService;

	// 根据输入的用户以及订单号获取订单信息
    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model,MiaoshaUser user,
    		@RequestParam("orderId") long orderId) {
    	if(user == null) {
    		return Result.error(CodeMsg.SESSION_ERROR);
    	}
    	OrderInfo order = orderService.getOrderById(orderId);   // 获取订单号对应的订单实体
    	if(order == null) {
    		return Result.error(CodeMsg.ORDER_NOT_EXIST);
    	}
    	long goodsId = order.getGoodsId();      // 获取订单中的商品id
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);      // 获取商品id对应的商品实体
    	OrderDetailVo vo = new OrderDetailVo();     // 构造商品详情用于返回
    	vo.setOrder(order);
    	vo.setGoods(goods);
    	return Result.success(vo);
    }
    
}
