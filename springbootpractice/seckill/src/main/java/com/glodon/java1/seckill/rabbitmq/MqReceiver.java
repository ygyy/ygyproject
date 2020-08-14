package com.glodon.java1.seckill.rabbitmq;

import com.glodon.java1.seckill.domain.Product;
import com.glodon.java1.seckill.redis.RedisService;
import com.glodon.java1.seckill.service.seckillservice.OrderService;
import com.glodon.java1.seckill.service.seckillservice.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqReceiver {

    private static Logger log = LoggerFactory.getLogger(MqReceiver.class);
    // private HashMap<String,Boolean> overFlags = new HashMap<>();
    @Autowired
    OrderService orderService;

    @Autowired
    RedisService redisService;

    @Autowired
    SecKillService secKillServiceImpl;

    @Autowired
    MqService mqService;

    @RabbitListener(queues = RabbitMqConfig.SECKILL_QUEUE)
    public void receive(String message) {
        //   log.info("receive message: " + message);
        SeckillMessage mm = RedisService.stringToBean(message, SeckillMessage.class);
        Product product = mm.getProduct();
        String phone = mm.getPhone();
        String uid = mm.getUserId();
        if ((secKillServiceImpl.checkTime(product))) {
            return;
        } //1.秒杀活动是否结束
        if (!secKillServiceImpl.checkBuyIf(String.valueOf(uid), String.valueOf(product.getProductId()))) {
            return;
        }//2.该用户是否参与过该商品的秒杀
        if (!secKillServiceImpl.checkIfHave(product)) {
            return;
        } //3.商品是否还有库存
        mqService.miaosha(product, uid, phone);
    }
}
