package com.glodon.java1.seckill.service.seckillservice.impl;

import com.glodon.java1.seckill.dao.ProductMapper;
import com.glodon.java1.seckill.domain.Product;
import com.glodon.java1.seckill.domain.ProductExample;
import com.glodon.java1.seckill.service.seckillservice.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductMapper productMapper;

    @Override
    public List<Product> getAllgoodsMessage(){
        ProductExample productExample= new ProductExample();
        //ProductExample.Criteria criteria=productExample.createCriteria();
        return productMapper.selectByExample(productExample);
    }

}
