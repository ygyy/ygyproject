package com.glodon.java1.seckill.service.goodsmanagement;

import com.glodon.java1.seckill.domain.Order;
import com.glodon.java1.seckill.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodsService {

    /**
     * 添加秒杀商品信息
     * @auth:liual
     * @date:2020/8/7
     * @return
     */

   int addProduct(Product product) throws Exception;


    /**
     * 修改秒杀商品信息
     * @auth:ygy
     * @date:2020/8/7
     * @param product
     * @return
     */
     int updateProduct(Product product);

    /**
     * 根据商品Id获取商品信息
     * @param productId
     * @return
     */
    public Product getProductById(Integer productId);

    /**
     * 查询所有秒杀商品信息
     * @auth:liual
     * @date:2020/8/7
     * @return
     */

    List<Object> queryProduct(String code,String productName,String status, int pagenum, int pagesize) throws Exception;

    /**
     * 检查商品code是否重复
     * @auth:liual
     * @date:2020/8/7
     * @return
     */

    boolean checkProductCode(Product product) throws Exception;

  /**
   * @auth:ygy
   * @date:2020/8/7
   * 根据商品id查询商品当前库存
   * @param productId
   * @return
   */

   Integer getCurPruductCount(Integer productId);

    /**
     * @auth:ygy
     * @date:2020/8/7
     * 根据商品id修改上架下架状态
     * @param productId
     * @return
     */
   Integer updateProductsStatus(Integer productId,String status);

    /**
     * @auth:ygy
     * @date:2020/8/9
     * 根据商品id获取秒杀记录
     * @param productId
     * @return
     */
    List<Order> getOrderByProductId(Integer productId);
}
