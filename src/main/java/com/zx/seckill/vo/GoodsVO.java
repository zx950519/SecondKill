package com.zx.seckill.vo;



/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 16:48
 * @description：
 * @modified By：
 * @version: $
 */

import lombok.Data;
import java.util.Date;
import com.zx.seckill.domain.Goods;

@Data
public class GoodsVO extends Goods{

    private Double seckillPrice;
    private Integer stockCount;
    private Date startTime;
    private Date endTime;

}
