package com.zx.seckill.redis;

/**
 * @Description:秒杀用户Key的定义
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */
public class MiaoshaUserKey extends BasePrefix{

	public static final int TOKEN_EXPIRE = 3600*24 * 2;     // 过期时间
	private MiaoshaUserKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}
	public static MiaoshaUserKey token = new MiaoshaUserKey(TOKEN_EXPIRE, "tk");        // 生成前缀为tk的秒杀用户键
	public static MiaoshaUserKey getById = new MiaoshaUserKey(0, "id");    // 生成前缀为id的秒杀用户键
}
