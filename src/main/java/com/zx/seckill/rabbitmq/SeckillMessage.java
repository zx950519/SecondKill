package com.zx.seckill.rabbitmq;

import com.zx.seckill.domain.SeckillUser;
import lombok.Data;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 17:02
 * @description：秒杀消息
 * @modified By：
 * @version: $
 */

@Data
public class SeckillMessage {

    private SeckillUser seckillUser;
    private long goodsId;

    @Override
    public String toString() {
        return "SeckillMessage{" +
                "seckillUser=" + seckillUser +
                ", goodsId=" + goodsId +
                '}';
    }
}
