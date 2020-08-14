package com.glodon.java1.seckill.service.goodsmanagement.impl;

import com.glodon.java1.seckill.dao.OrderMapper;
import com.glodon.java1.seckill.dao.ProductMapper;
import com.glodon.java1.seckill.domain.Order;
import com.glodon.java1.seckill.domain.OrderExample;
import com.glodon.java1.seckill.domain.Product;
import com.glodon.java1.seckill.domain.ProductExample;
import com.glodon.java1.seckill.redis.RedisService;
import com.glodon.java1.seckill.service.goodsmanagement.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class GoodsServiceImpl implements GoodsService {


    @Resource
    private ProductMapper productMapper;
    @Resource
    private RedisService redisService;
    @Resource
    private OrderMapper orderMapper;

    @Override
    public int addProduct(Product product) throws Exception {
        if (product != null && product.getCode() != null && product.getProductName() != null && product.getPrice() != null && product.getPriceSpike() != null && product.getCount() > 0 && product.getPictureSrc() != null && product.getDescription() != null) {
            return productMapper.insertSelective(product);
        }
        return 0;
    }

    @Override
    public int updateProduct(Product product) {
        return productMapper.updateByPrimaryKey(product);

    }

    @Override
    public List<Object> queryProduct(String code, String productName, String status, int pagenum, int pagesize) throws Exception {

        ProductExample productExample = new ProductExample();
        ProductExample.Criteria criteria = productExample.createCriteria();

        if (code != null) {
            criteria.andCodeEqualTo(code);
        }


        if (productName != null) {
            //商品名模糊查询
            criteria.andProductNameLike("%"+productName+"%");
        }


        if (status != null) {
            criteria.andProductStatusEqualTo(status);
        }

        //分页
        productExample.setLimitSize(pagesize);
        productExample.setLimitStart(pagenum * pagesize);
        //这里查询的是数据库，这里并没有查到当前库存
        List<Product> products = productMapper.selectByExample(productExample);

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            //去redis查询当前库存
            if (redisService.getPVal("goodsStocks", product.getCode()) != null) {
                product.setCurCount(redisService.getPVal("goodsStocks", product.getCode()));
            }
            //如果redis查不到当前库存,即redis已经超时
            else {
                product.setCurCount(getCurPruductCount(product.getProductId()));
            }
            //这里查到的是数据库中商品的状态，但不一定是商品最新的状态，当前库存可能已经为0或过期，但是数据库中的商品状态还没改变
            if (product.getProductStatus().equals("1")) {
                Calendar calendar = Calendar.getInstance();

                if (product.getCurCount() <= 0 || calendar.after(product.getEndTime())) {
                    product.setProductStatus("2");
                }
            }
            //把更改了商品状态的product顺便同步到redis和mysql
            int flag = productMapper.updateByPrimaryKeySelective(product);
            if (flag == 1) {
                redisService.setOneObj("goodsMessage", product.getCode(), product);
            }

        }
        List<Object> result = new ArrayList<>();
        result.add(products);
        Map<String, String> size = new HashMap<>();
        size.put("total", String.valueOf(products.size()));
        result.add(size);
        return result;

    }

    @Override
    public boolean checkProductCode(Product product) throws Exception {
        if (product != null && product.getCode() != null) {
            ProductExample productExample = new ProductExample();
            ProductExample.Criteria criteria = productExample.createCriteria();
            criteria.andCodeEqualTo(product.getCode());
            List<Product> products = productMapper.selectByExample(productExample);
            if (products != null && products.size() > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Product getProductById(Integer productId) {

        return productMapper.selectByPrimaryKey(productId);
    }

    @Override
    public Integer getCurPruductCount(Integer productId) {
        Product product = productMapper.selectByPrimaryKey(productId);
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andBuyStatusEqualTo("1");
        criteria.andProductIdEqualTo(productId);
        int count = (int) orderMapper.countByExample(orderExample);
        return product.getCount() - count;
    }

    @Override
    public Integer updateProductsStatus(Integer productId, String status) {
        Product product = new Product();
        product.setProductId(productId);
        product.setProductStatus(status);
        return productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public List<Order> getOrderByProductId(Integer productId) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andProductIdEqualTo(productId);
        criteria.andBuyStatusEqualTo("1");
        return orderMapper.selectByExample(orderExample);
    }
}

