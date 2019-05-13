package com.zx.seckill.vo;

import com.zx.seckill.domain.SeckillUser;
import lombok.Data;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 16:43
 * @description：商品细节
 * @modified By：
 * @version: $
 */

@Data
public class GoodsDetailVO {

    private int seckillStatus = 0;
    private int remainSeconds = 0;
    private GoodsVO goods;
    private SeckillUser seckillUser;

}
