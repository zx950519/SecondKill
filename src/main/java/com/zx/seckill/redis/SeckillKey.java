package com.zx.seckill.redis;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 17:27
 * @description：
 * @modified By：
 * @version: $
 */
public class SeckillKey extends BasePrefix {
    private SeckillKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }


    public static SeckillKey isGoodsOver = new SeckillKey(0, "go");
    public static SeckillKey getSeckillPath = new SeckillKey(60, "sp");
    public static SeckillKey getSeckillVerifyCode = new SeckillKey(300, "verifyCode");

}
