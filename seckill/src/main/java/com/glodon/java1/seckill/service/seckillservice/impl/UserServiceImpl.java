package com.glodon.java1.seckill.service.seckillservice.impl;

import com.glodon.java1.seckill.dao.ProductMapper;
import com.glodon.java1.seckill.domain.Product;
import com.glodon.java1.seckill.domain.ProductExample;
import com.glodon.java1.seckill.service.seckillservice.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private ProductMapper productMapper;

    @Override
    public List<Product> viewSelkillProductsPage(String productStatus) {
        ProductExample productExample = new ProductExample();
        productExample.setOrderByClause("end_time asc");
        ProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andProductStatusEqualTo(productStatus);
        List<Product> seckillProductList = productMapper.selectByExample(productExample);
        return seckillProductList;
    }

    @Override
    public Product viewProductDetails(int id) {
        Product product = productMapper.selectByPrimaryKey(id);
        return product;
    }

}
