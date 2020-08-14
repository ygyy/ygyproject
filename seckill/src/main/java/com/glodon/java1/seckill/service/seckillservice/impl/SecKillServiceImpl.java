package com.glodon.java1.seckill.service.seckillservice.impl;

import com.glodon.java1.seckill.dao.ProductMapper;
import com.glodon.java1.seckill.domain.Order;
import com.glodon.java1.seckill.domain.Product;
import com.glodon.java1.seckill.redis.RedisService;
import com.glodon.java1.seckill.service.seckillservice.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

@Service
public class SecKillServiceImpl implements SecKillService {


    @Resource
    ProductMapper productMapper;


    @Resource
    RedisService redisService;

    Logger logger=LoggerFactory.getLogger(SecKillServiceImpl.class);

    /**
     *
     * @param product 目标秒杀商品
     * @return  若当前瑞日期在在秒杀活动之后为false 反之为true并下架该商品
     *
     */

    @Override
    public boolean checkTime(Product product){
        logger.info("check: product: "+product.toString());
        Calendar calendar=Calendar.getInstance();
       boolean timeFlag= calendar.after(product.getEndTime());
       if(timeFlag){
           updateGoodsStatus(product);
       }
       return timeFlag;
    }

    /**
     *
     * @param uid 用户id
     * @return 若参与过该商品的秒杀返回false反之为true
     */
    @Override
    public boolean checkBuyIf(String uid,String productId){
        if(redisService.getOneBuyMessage(uid,productId)!=null){
            return false;
        }else {
            return true;
        }
    }

    /**
     *
     * @param product 商品id
     * @return  若有库存返回true 反之下架该商品返回false
     */
    @Override
    public boolean checkIfHave(Product product){
        if(redisService.getPVal("goodsStocks",product.getCode())>0){
            return true;
        }else {
            updateGoodsStatus(product);
            return false;
        }
    }

    /**
     *差入一条秒杀记录
     */

    @Override
    public  Order  insertOneBuyMessageToRedis(int userId, int productId, String phone){
        Order order=new Order();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setPhone(phone);
        order.setBuyStatus("1");
        order.setBuyTime(new Date(System.currentTimeMillis()));
        redisService.addOneBuyMessage(String.valueOf(userId),String.valueOf(productId),order);
        return order;
    }

    /**
     * 商品下架操作
     * @param product 商品
     */
    @Transactional
    public void updateGoodsStatus(Product product){
        logger.info("check stock: product:"+product);
        if(!product.getProductStatus().equals("2")){
            product.setProductStatus("2");
            redisService.setOneObj("goodsMessage",product.getCode(),product);
            productMapper.updateByPrimaryKeySelective(product);
    }
    }
}
