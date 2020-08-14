package com.glodon.java1.seckill.rabbitmq;

import com.glodon.java1.seckill.domain.Order;
import com.glodon.java1.seckill.domain.Product;
import com.glodon.java1.seckill.redis.RedisService;
import com.glodon.java1.seckill.service.seckillservice.OrderService;
import com.glodon.java1.seckill.service.seckillservice.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MqService {
    @Autowired
    OrderService orderService;

    @Autowired
    RedisService redisService;

    @Autowired
    SecKillService secKillServiceImpl;

    /**
     * 秒杀事务
     * @param product 商品对象
     * @param uid 用户id
     * @param phone 手机号
     */
    @Transactional
    public void  miaosha(Product product,String uid,String phone){
        Long sum= redisService.hdecr("goodsStocks",product.getCode());
        if(sum<0){
            return;
        }
        Order order= secKillServiceImpl.insertOneBuyMessageToRedis(Integer.parseInt(uid),product.getProductId(),phone);
        orderService.insert(order);
    }
}
