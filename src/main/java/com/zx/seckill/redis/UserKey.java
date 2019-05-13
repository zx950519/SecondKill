package com.zx.seckill.redis;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 17:28
 * @description：
 * @modified By：
 * @version: $
 */

public class UserKey extends BasePrefix {
    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");

}
