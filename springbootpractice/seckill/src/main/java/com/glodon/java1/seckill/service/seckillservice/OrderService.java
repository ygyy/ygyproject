package com.glodon.java1.seckill.service.seckillservice;

import com.glodon.java1.seckill.domain.Order;
import com.glodon.java1.seckill.domain.Product;
import com.glodon.java1.seckill.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Order getOrderByUserIdProductId(int productId, int userId);

    List<Order> getOrderByUserId(int userId);

    void insert(Order order);

    int getSeckillResult(int useId, int productId);

}
