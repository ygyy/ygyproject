package com.glodon.java1.seckill.controller.seckillcontroller;

import com.glodon.java1.seckill.domain.Product;
import com.glodon.java1.seckill.redis.RedisService;
import com.glodon.java1.seckill.result.Meta;
import com.glodon.java1.seckill.result.Result;
import com.glodon.java1.seckill.service.goodsmanagement.GoodsService;
import com.glodon.java1.seckill.service.seckillservice.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private RedisService redisService;

    @Resource
    private UserService userService;

    @GetMapping("/products")
    @CrossOrigin
    public Result viewSeckillProducts() {
        try {
            List<Product> seckillProductList1 = userService.viewSelkillProductsPage("1");
            List<Product> seckillProductList2 = userService.viewSelkillProductsPage("2");
            seckillProductList1.addAll(seckillProductList2);

            for (Product product : seckillProductList1) {
                //先从Redis中查询，如果Redis中查询不到再从数据库中查询
                Integer currNum = redisService.getPVal("goodsStocks", product.getCode());
                if (currNum != null) {
                    product.setCurCount(currNum);
                } else {
                    Integer curPruductCount = goodsService.getCurPruductCount(product.getProductId());
                    product.setCurCount(curPruductCount);
                }
                //获取当前商品的最新状态
                if (product.getProductStatus().equals("1")) {
                    Calendar calendar = Calendar.getInstance();
                    if (product.getCurCount() <= 0 || calendar.after(product.getEndTime())) {
                        product.setProductStatus("2");
                    }
                }
                //同步mysql和redis
                int flag = goodsService.updateProduct(product);
                if (flag == 1) {
                    redisService.setOneObj("goodsMessage", product.getCode(), product);
                }
            }
            return Result.success(seckillProductList1, Meta.SUCCESS);
        } catch (Exception e) {            e.printStackTrace();

            return Result.error(Meta.SERVER_ERROR);
        }

    }

    @GetMapping("/products/{id}")
    @CrossOrigin
    public Result viewPruductDetails(@PathVariable(required = true) int id) {
        try {
            Product product = userService.viewProductDetails(id);
            Integer currNum = redisService.getPVal("goodsStocks", product.getCode());
            if (currNum != null) {
                product.setCurCount(currNum);
            } else {
                Integer curProductCount = goodsService.getCurPruductCount(product.getProductId());
                product.setCurCount(curProductCount);
            }
            //获取当前商品的最新状态
            if (product.getProductStatus().equals("1")) {
                Calendar calendar = Calendar.getInstance();
                if (product.getCurCount() <= 0 || calendar.after(product.getEndTime())) {
                    product.setProductStatus("2");
                }
            }
            //同步mysql和redis
            int flag = goodsService.updateProduct(product);
            if (flag == 1) {
                redisService.setOneObj("goodsMessage", product.getCode(), product);
            }
            return Result.success(product, Meta.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(Meta.SERVER_ERROR);
        }
    }
}
