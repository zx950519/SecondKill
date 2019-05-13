package com.zx.seckill.util;

import java.util.UUID;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 16:26
 * @description：生成UUID
 * @modified By：
 * @version: $
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
