package com.glodon.java1.seckill.controller.goodsmanagement;

import com.glodon.java1.seckill.domain.Order;
import com.glodon.java1.seckill.domain.Product;
import com.glodon.java1.seckill.redis.RedisService;
import com.glodon.java1.seckill.result.DetailResult;
import com.glodon.java1.seckill.result.Meta;
import com.glodon.java1.seckill.result.Result;
import com.glodon.java1.seckill.service.goodsmanagement.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RedisService redisService;

    /**
     * auth:liual
     * date:2020/8/7
     */
    @PostMapping("/products")
    @CrossOrigin
    public Result addProduct(@RequestBody Product product) {
        try {
            if(goodsService.checkProductCode(product)){
                return new Result(Meta.PRODUCT_CODE_ERROR);
            }
            int status = goodsService.addProduct(product);
            if (status == 1) {
                redisService.addMapElement("goodsStocks", product.getCode(), String.valueOf(product.getCount()));
                redisService.setOneObj("goodsMessage", product.getCode(), product);
                return new Result(Meta.SUCCESS);
            }
            return new Result(Meta.PRODUCT_ADD_ERROR);
        } catch (Exception e) {
            return new Result(Meta.SERVER_ERROR);
        }
    }

    /**
     * auth:liual
     * date:2020/8/7
     */
    @GetMapping("/products")
    @CrossOrigin
    public Result queryProduct(@RequestParam(required = false, value = "code") String code,
                               @RequestParam(required = false, value = "productName") String productName,
                               @RequestParam(required = false, value = "status") String status,
                               @RequestParam(defaultValue = "0") int pagenum,
                               @RequestParam(defaultValue = "10") int pagesize) {

        List<Object> objects = new ArrayList<>();
        try {
            objects = goodsService.queryProduct(code, productName, status, pagenum, pagesize);
            return new Result(Meta.SUCCESS, objects);
        } catch (Exception e) {

            return new Result(Meta.SERVER_ERROR, objects);
        }
    }

    /**
     * @auth:ygy
     * @date:2020/8/7
     * 根据商品id修改商品信息，返回状态
     * @param product
     * @return
     * @auth:ygy
     * @date:2020/8/7 根据商品id修改商品信息，返回状态
     */
    @PutMapping("products")
    @CrossOrigin
    public Result updateProduct(@RequestBody Product product){
        try {
            int flag = goodsService.updateProduct(product);
            if (flag == 1) {
                redisService.setOneObj("goodsMessage",product.getCode(),product);//redis改商品信息
                //redis改库存
                redisService.addMapElement("goodsStocks",product.getCode(),String.valueOf(product.getCount()));
                Meta meta = Meta.SUCCESS;
                meta.setMsg("商品信息修改成功！");
                return new Result(meta);
            }
            Meta meta = Meta.SUCCESS;
            meta.setMsg("商品信息修改失败！");
            return new Result(meta);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Meta.SERVER_ERROR);
        }
    }

    /**
     * @auth:ygy
     * @date:2020/8/7
     * 根据商品Id获取商品详情
     * @param productId
     * @return
     */
    @CrossOrigin
    @GetMapping("products/{productId}")
    public Result getProduct(@PathVariable Integer productId){
        try{
            //根据id查找商品信息
            Product product = goodsService.getProductById(productId);
            if(product!=null){
                //查库存
                Integer curCount = redisService.getPVal("goodsStocks", product.getCode());
                if(curCount!=null){//redis能查到库存
                    product.setCurCount(curCount);
                }else{//不能查到库存，去mysql数据库计算当前库存
                    product.setCurCount(goodsService.getCurPruductCount(productId));
                }
                List<String> phoneList = new ArrayList<>();//秒杀成功的手机号
                //查该商品秒杀成功的手机号
                List<Order> orderList = redisService.getAllobj("orderMessage", Order.class);
                if(orderList==null){//redis已过期或未更新，去mysql查
                    orderList = goodsService.getOrderByProductId(product.getProductId());
                }
                for (Order o:orderList) {//加入秒杀成功手机号
                    if(o.getBuyStatus().equals("1")){
                        phoneList.add(o.getPhone());
                    }
                }
                return new DetailResult(product,Meta.SUCCESS,phoneList);
            }
            Meta meta = Meta.SUCCESS;
            meta.setMsg("获取失败！");
            return Result.error(meta);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(Meta.SERVER_ERROR);
        }

    }

    /**
     * @auth:ygy
     * @date:2020/8/7
     * 批量上架/下架
     * @param status
     * @param ids
     * @return
     */
    @CrossOrigin
    @PutMapping("products/{status}")
    public Result updateProductsStatus(@PathVariable String status, @RequestBody List<Integer> ids){
        int flag;//成功或失败的状态标志
        try{
            for (Integer productId: ids) {
                Product product = goodsService.getProductById(productId);
                if(status.equals("1")){//上架
                    //判断是否符合上架条件
                    Integer curCount = redisService.getPVal("goodsStocks", product.getCode());
                    if((curCount!=null?curCount:goodsService.getCurPruductCount(productId))<=0){
                        Meta meta = Meta.SUCCESS;
                        meta.setMsg("编号为"+product.getCode()+"的商品库存为0，不能上架！请重新选择需要上架的商品。");
                        return new Result(meta);
                    }
                    if(!(product.getEndTime()).after(new Date(System.currentTimeMillis()))){
                        Meta meta = Meta.SUCCESS;
                        meta.setMsg("编号为"+product.getCode()+"的商品已过期，不能上架！请重新选择需要上架的商品。");
                        return new Result(meta);
                    }
                    //上架
                    product.setProductStatus(status);
                    flag = goodsService.updateProduct(product);
                    if (flag != 1) {//更新失败
                        return new Result(Meta.SERVER_ERROR);
                    }//数据库更新成功，更新到redis中
                    redisService.setOneObj("goodsMessage",product.getCode(),product);
                }else {//下架
                    //上架后的商品可以下架
                    if(product.getProductStatus().equals("1")){
                        product.setProductStatus(status);
                        flag = goodsService.updateProduct(product);
                        if (flag != 1) {//更新失败
                            return new Result(Meta.SERVER_ERROR);
                        }//数据库更新成功，更新到redis中
                        redisService.setOneObj("goodsMessage",product.getCode(),product);
                    }else {
                        Meta meta = Meta.SUCCESS;
                        meta.setMsg("编号为"+product.getCode()+"的商品还未上架，不能下架！请重新选择需要下架的商品。");
                        return new Result(meta);
                    }

                }

            }
            if(status.equals("1")){
                Meta meta = Meta.SUCCESS;
                meta.setMsg("上架成功！");
                return new Result(meta);
            }else{
                Meta meta = Meta.SUCCESS;
                meta.setMsg("下架成功！");
                return new Result(meta);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Meta.SERVER_ERROR);
        }
    }

}



