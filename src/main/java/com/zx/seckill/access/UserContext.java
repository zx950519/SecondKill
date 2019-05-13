package com.zx.seckill.access;

import com.zx.seckill.domain.SeckillUser;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 18:35
 * @description：
 * @modified By：
 * @version: $
 */
public class UserContext {
    private static ThreadLocal<SeckillUser> userHolder = new ThreadLocal<>();

    public static void setUser(SeckillUser user) {
        userHolder.set(user);
    }

    public static SeckillUser getUser() {
        return userHolder.get();
    }
}
