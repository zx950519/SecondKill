package com.zx.seckill.redis;

/**
 * @Description:Redis相关操作接口
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */
public interface KeyPrefix {
		
	public int expireSeconds();     // 返回过期时间
	
	public String getPrefix();      // 获取Key的前缀
	
}
