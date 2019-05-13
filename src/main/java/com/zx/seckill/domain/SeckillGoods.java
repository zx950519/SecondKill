package com.zx.seckill.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 10:50
 * @description：秒杀商品信息
 * @modified By：
 * @version: $
 */
@Data
public class SeckillGoods {
    private Long id;                // id
    private Long goodsId;           // 秒杀货物id
    private Double seckillPrice;    // 秒杀价
    private Integer stockCount;     // 库存
    private Date startTime;         // 秒杀开始时间
    private Date endTime;           // 秒杀结束时间

    @Override
    public String toString() {
        return "SeckillGoods{" +
                "id=" + id +
                ", goodsid=" + goodsId +
                ", stockCount=" + stockCount +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
