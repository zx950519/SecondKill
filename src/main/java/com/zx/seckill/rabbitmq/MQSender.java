package com.zx.seckill.rabbitmq;



/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 17:01
 * @description：生产者
 * @modified By：
 * @version: $
 */

//import com.zx.seckill.redis.RedisService;
import com.zx.seckill.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQSender {

    @Autowired
    AmqpTemplate amqpTemplate;  // 消息队列模板

    public void sendSeckillMessage(SeckillMessage message) {
        String msg = RedisService.beanToString(message);
        log.info("send message:" + msg);
        amqpTemplate.convertAndSend(MQConfig.SECKILL_QUEUE, msg);
    }
}
