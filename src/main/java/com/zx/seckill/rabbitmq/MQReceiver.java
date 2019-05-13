package com.zx.seckill.rabbitmq;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 17:01
 * @description：消费者
 * @modified By：
 * @version: $
 */

import com.zx.seckill.domain.OrderInfo;
import com.zx.seckill.domain.SeckillOrder;
import com.zx.seckill.domain.SeckillUser;
import com.zx.seckill.redis.RedisService;
import com.zx.seckill.result.CodeMsg;
import com.zx.seckill.result.Result;
import com.zx.seckill.service.GoodsService;
import com.zx.seckill.service.OrderService;
import com.zx.seckill.service.SeckillService;
import com.zx.seckill.vo.GoodsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQReceiver {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SeckillService seckillService;


    @RabbitListener(queues = MQConfig.SECKILL_QUEUE)
    public void receive(String message) {
        log.info("receive message:" + message);
        SeckillMessage seckillMessage = RedisService.stringToBean(message, SeckillMessage.class);
        SeckillUser user = seckillMessage.getSeckillUser();
        long goodsId = seckillMessage.getGoodsId();

        //判断库存
        GoodsVO goods = goodsService.getGoodsVOById(goodsId);
        int stock = goods.getStockCount();
        if (stock < 1) {
            return;
        }

        //判断是否已经秒杀到了
        SeckillOrder order = orderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            return;
        }
    }
}
