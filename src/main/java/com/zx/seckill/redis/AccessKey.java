package com.zx.seckill.redis;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 17:22
 * @description：
 * @modified By：
 * @version: $
 */
public class AccessKey extends BasePrefix {
    private AccessKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }


    public static AccessKey access = new AccessKey(5, "access");

    public static AccessKey withExpire(int expireSeconds) {
        return new AccessKey(expireSeconds, "access");
    }
}
