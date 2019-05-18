package com.zx.seckill.util;

import java.util.UUID;

/**
 * @Description:UUID生成工具
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */
public class UUIDUtil {
	/*
		UUID 的目的，是让分布式系统中的所有元素，都能有唯一的辨识资讯，而不需要透过中央控制端来做辨识资讯的指定。
		UUID保证对在同一时空中的所有机器都是唯一的。
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
