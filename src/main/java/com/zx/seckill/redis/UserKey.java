package com.zx.seckill.redis;

/**
 * @Description:用户Key的定义
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */
public class UserKey extends BasePrefix{

	private UserKey(String prefix) {
		super(prefix);
	}
	public static UserKey getById = new UserKey("id");      // 生成前缀为id的用户键
	public static UserKey getByName = new UserKey("name");  // 生成前缀为name的用户键
}
