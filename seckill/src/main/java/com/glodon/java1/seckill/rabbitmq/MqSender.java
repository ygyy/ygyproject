package com.glodon.java1.seckill.rabbitmq;

import com.glodon.java1.seckill.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqSender {
    private static Logger log = LoggerFactory.getLogger(MqSender.class);

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendMessage(SeckillMessage mm){
        String msg = RedisService.beanToString(mm);
       // log.info("send message:"+msg);
        amqpTemplate.convertAndSend(RabbitMqConfig.SECKILL_QUEUE, msg);
    }
}
