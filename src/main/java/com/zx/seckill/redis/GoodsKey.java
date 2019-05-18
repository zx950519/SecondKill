package com.zx.seckill.redis;

/**
 * @Description:商品Key的定义
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */
public class GoodsKey extends BasePrefix{

	private GoodsKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}
	public static GoodsKey getGoodsList = new GoodsKey(60, "gl");       // 生成前缀为gl的商品键
	public static GoodsKey getGoodsDetail = new GoodsKey(60, "gd");     // 生成前缀为gd的商品键
	public static GoodsKey getMiaoshaGoodsStock= new GoodsKey(0, "gs"); // 生成前缀为gs的商品键
}
