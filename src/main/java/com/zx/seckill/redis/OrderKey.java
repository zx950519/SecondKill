package com.zx.seckill.redis;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 17:24
 * @description：
 * @modified By：
 * @version: $
 */
public class OrderKey extends BasePrefix {
    private OrderKey(String prefix) {
        super(prefix);
    }

    public static OrderKey getSeckillOrderByUidGid = new OrderKey("soug");

}