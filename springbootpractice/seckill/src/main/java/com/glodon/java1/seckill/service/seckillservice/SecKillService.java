package com.glodon.java1.seckill.service.seckillservice;

import com.glodon.java1.seckill.domain.Order;
import com.glodon.java1.seckill.domain.Product;

public interface SecKillService {

    public boolean checkTime(Product product);
    public boolean checkBuyIf(String uid,String productId);
    public boolean checkIfHave(Product product);
    public Order insertOneBuyMessageToRedis(int userId, int productId, String phone);
}
