package com.zx.seckill.rabbitmq;

import com.zx.seckill.domain.MiaoshaUser;

/**
 * @Description:消息队列中需要传递的消息体
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */

public class MiaoshaMessage {

	private MiaoshaUser user;   // 秒杀用户
	private long goodsId;       // 秒杀商品id

	public MiaoshaUser getUser() {
		return user;
	}
	public void setUser(MiaoshaUser user) {
		this.user = user;
	}
	public long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}
}
