package com.zx.seckill.vo;

import com.zx.seckill.domain.MiaoshaUser;

/**
 * @Description:秒杀商品详情(表示层)的VO对象
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */

public class GoodsDetailVo {

	private int miaoshaStatus = 0;		// 秒杀状态
	private int remainSeconds = 0;		// 距离秒杀开始的时间
	private GoodsVo goods ;				// 秒杀商品
	private MiaoshaUser user;			// 秒杀用户


	public int getMiaoshaStatus() {
		return miaoshaStatus;
	}
	public void setMiaoshaStatus(int miaoshaStatus) {
		this.miaoshaStatus = miaoshaStatus;
	}
	public int getRemainSeconds() {
		return remainSeconds;
	}
	public void setRemainSeconds(int remainSeconds) {
		this.remainSeconds = remainSeconds;
	}
	public GoodsVo getGoods() {
		return goods;
	}
	public void setGoods(GoodsVo goods) {
		this.goods = goods;
	}
	public MiaoshaUser getUser() {
		return user;
	}
	public void setUser(MiaoshaUser user) {
		this.user = user;
	}
}
