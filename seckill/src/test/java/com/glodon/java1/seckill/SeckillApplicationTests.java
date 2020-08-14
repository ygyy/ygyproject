package com.glodon.java1.seckill;

import com.glodon.java1.seckill.dao.ProductMapper;
import com.glodon.java1.seckill.redis.RedisService;
import com.glodon.java1.seckill.service.seckillservice.impl.ProductServiceImpl;
import com.glodon.java1.seckill.service.seckillservice.impl.SecKillServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class SeckillApplicationTests {
    private static final String QUEUE_NAME = "test_simple_queue";
    @Autowired
    RedisService service;

    @Autowired
    ProductServiceImpl productServiceImpl;

    @Autowired
    SecKillServiceImpl secKillServiceImpl;

    @Resource
    ProductMapper productMapper;

    @Test
    void testMqSender() {

    }

    @Test
    public void testRedis() {

        System.out.println(
                service.addMapElement("goosStocks","glodon06","66")
        );
        System.out.println(service.getPVal("goosStocks","glodon06"));
    }

    @Test
    public void testResult() {
        System.out.println(  service.getPVal("goodsStocks","1"));
    }

    @Test void pushData() throws IOException {
//        File data=new File("C:\\Users\\zhangyx-v\\Desktop\\testjmater\\data.csv");
//        Integer phone=10086;
//        Integer uid=1;
//        String code="glodon01";
//        FileWriter writer=new FileWriter(data);
//        BufferedWriter writer1=new BufferedWriter(writer);
//        int times=10000;
//        while (times>0){
//            writer1.write(code+","+uid+","+phone);
//            writer1.newLine();
//            uid++;
//            phone++;
//            times--;
//        }

    }
}