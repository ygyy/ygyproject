package com.glodon.java1.seckill.controller.seckillcontroller;

import com.glodon.java1.seckill.domain.Order;
import com.glodon.java1.seckill.domain.Product;
import com.glodon.java1.seckill.redis.RedisService;
import com.glodon.java1.seckill.result.Meta;
import com.glodon.java1.seckill.result.Result;
import com.glodon.java1.seckill.service.goodsmanagement.GoodsService;
import com.glodon.java1.seckill.service.seckillservice.OrderService;
import com.glodon.java1.seckill.vo.OrderDetail;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private RedisService redisService;

    @Resource
    private GoodsService goodsService;

    @CrossOrigin
    @GetMapping("/{userId}")
    public Result viewMyOrder(@PathVariable(required = true) int userId) {
        try {

            List<Order> orderList = redisService.getAllobj(String.valueOf(userId), Order.class);
            if(orderList == null){

                orderList = orderList = orderService.getOrderByUserId(userId);
            }

            List<OrderDetail> results = new ArrayList<>();
            for(Order order:orderList) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setUserId(order.getUserId());
                orderDetail.setBuyStatus(order.getBuyStatus());
                orderDetail.setBuyTime(order.getBuyTime());
                Product product = goodsService.getProductById(order.getProductId());
                orderDetail.setPrice(product.getPrice());
                orderDetail.setProductName(product.getProductName());
                results.add(orderDetail);
            }

            return Result.success(results, Meta.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(Meta.SERVER_ERROR);
        }
    }

}
