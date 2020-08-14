package com.glodon.java1.seckill.dao;

import com.glodon.java1.seckill.domain.Order;
import com.glodon.java1.seckill.domain.OrderExample;
import com.glodon.java1.seckill.domain.OrderKey;

import java.sql.Date;
import java.text.spi.DateFormatSymbolsProvider;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(OrderKey key);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(OrderKey key);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);


}