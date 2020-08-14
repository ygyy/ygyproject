package com.glodon.java1.seckill.service.seckillservice;

import com.glodon.java1.seckill.domain.Product;

import java.util.List;

public interface UserService {
    List<Product> viewSelkillProductsPage(String productStatus);

    Product viewProductDetails(int id);

}
