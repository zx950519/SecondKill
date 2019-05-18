package com.zx.seckill.vo;

import com.zx.seckill.domain.OrderInfo;

/**
 * @Description:秒杀订单页面(表示层)的VO对象
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */
public class OrderDetailVo {

	private GoodsVo goods;		// 秒杀商品
	private OrderInfo order;	// 订单信息

	public GoodsVo getGoods() {
		return goods;
	}
	public void setGoods(GoodsVo goods) {
		this.goods = goods;
	}
	public OrderInfo getOrder() {
		return order;
	}
	public void setOrder(OrderInfo order) {
		this.order = order;
	}
}
