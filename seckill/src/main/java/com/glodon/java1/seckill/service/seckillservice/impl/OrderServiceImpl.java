package com.glodon.java1.seckill.service.seckillservice.impl;

import com.glodon.java1.seckill.dao.OrderMapper;
import com.glodon.java1.seckill.domain.Order;
import com.glodon.java1.seckill.domain.OrderExample;
import com.glodon.java1.seckill.service.seckillservice.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public Order getOrderByUserIdProductId(int productId, int userId) {
        return null;
    }

    @Override
    public List<Order> getOrderByUserId(int userId) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<Order> orders = orderMapper.selectByExample(orderExample);
        return orders;
    }

    @Override
    public void insert(Order order) {
        orderMapper.insert(order);

    }

    @Override
    public int getSeckillResult(int useId, int productId) {
        return 0;
    }
}
