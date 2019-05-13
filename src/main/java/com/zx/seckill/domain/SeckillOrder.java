package com.zx.seckill.domain;

import lombok.Data;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 10:57
 * @description：秒杀订单
 * @modified By：
 * @version: $
 */
@Data
public class SeckillOrder {
    private Long id;        // id
    private Long userId;    // 秒杀用户id
    private Long orderId;   // 秒杀物品id
    private Long goodsId;   // 秒杀货物id

    @Override
    public String toString() {
        return "SeckillOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", goodsId=" + goodsId +
                '}';
    }
}
