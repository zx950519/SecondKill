package com.zx.seckill.vo;

import com.zx.seckill.domain.OrderInfo;
import lombok.Data;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 16:51
 * @description：订单细节
 * @modified By：
 * @version: $
 */

@Data
public class OrderDetailVO {
    private GoodsVO goods;
    private OrderInfo order;
}
