package com.zx.seckill.vo;

import java.util.Date;

import com.zx.seckill.domain.Goods;

/**
 * @Description:秒杀商品页面(表示层)的VO对象
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */

public class GoodsVo extends Goods{

	private Double miaoshaPrice;    // 秒杀价
	private Integer stockCount;     // 库存量
	private Date startDate;         // 秒杀开始日期
	private Date endDate;           // 秒杀结束日期

	public Integer getStockCount() {
		return stockCount;
	}
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getMiaoshaPrice() {
		return miaoshaPrice;
	}
	public void setMiaoshaPrice(Double miaoshaPrice) {
		this.miaoshaPrice = miaoshaPrice;
	}
}
