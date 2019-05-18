package com.zx.seckill.redis;

/**
 * @Description:秒杀Key的定义
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */
public class MiaoshaKey extends BasePrefix{

	private MiaoshaKey( int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}
	public static MiaoshaKey isGoodsOver = new MiaoshaKey(0, "go");				// 生成前缀为go的秒杀键
	public static MiaoshaKey getMiaoshaPath = new MiaoshaKey(60, "mp");			// 生成前缀为mp的秒杀键
	public static MiaoshaKey getMiaoshaVerifyCode = new MiaoshaKey(300, "vc");	// 生成前缀为vc的秒杀键
}
