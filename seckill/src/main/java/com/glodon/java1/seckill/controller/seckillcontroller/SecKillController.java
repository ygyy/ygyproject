package com.glodon.java1.seckill.controller.seckillcontroller;

import com.glodon.java1.seckill.domain.Product;
import com.glodon.java1.seckill.domain.User;
import com.glodon.java1.seckill.rabbitmq.MqSender;
import com.glodon.java1.seckill.rabbitmq.SeckillMessage;
import com.glodon.java1.seckill.redis.RedisService;
import com.glodon.java1.seckill.result.Meta;
import com.glodon.java1.seckill.result.Result;
import com.glodon.java1.seckill.service.seckillservice.ProductService;
import com.glodon.java1.seckill.service.seckillservice.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@RestController
public class SecKillController implements InitializingBean {

    @Resource
    ProductService productServiceImpl;

    @Autowired
    SecKillService secKillServiceImpl;

    @Resource
    RedisService jservice;

    @Resource
    MqSender mqSender;

    static HashMap<String,Boolean> overFlags = new HashMap<>();
    private HashMap<String, String> goodsmap =  new HashMap();
    private HashMap<String,String> stockMap =new HashMap<>();
    Logger logger = LoggerFactory.getLogger(SecKillController.class);
    /**
     *  初始化加载redis热点数据 商品信息、商品库存
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //查询所有热点商品信息为map集合 key为 商品id value为商品信息
        List<Product> productList= productServiceImpl.getAllgoodsMessage();
       // logger.info("begin of product: "+productList);
        ListIterator<Product> iterator=productList.listIterator();
        while (iterator.hasNext()){
            Product tempP=iterator.next();
            goodsmap.put(String.valueOf(tempP.getCode()),RedisService.beanToString(tempP));
            stockMap.put(String.valueOf(tempP.getCode()),String.valueOf(tempP.getCount()));
            overFlags.put(String.valueOf(tempP.getCode()),true);
        }
        //差入redis/
        jservice.addHashMap("goodsMessage",goodsmap);
        jservice.addHashMap("goodsStocks",stockMap);
    }

    /**
     *  秒杀业务
     *
     * @return
     */

    @RequestMapping(value ="/orders",method = RequestMethod.POST)
    public Result checkBeforSeckill(@RequestBody Map<String,Object> map){
        String code = (String) map.get("code");
        String userId = (String) map.get("userId");
        String phone = (String) map.get("phone");
        try{
            System.out.println(code);
            if(!overFlags.get(code)){
                return Result.error(Meta.TIME_OUT);
            }

            //检验秒杀强求合法性：
         //   logger.info("gooidID:"+code+",uid: "+userId+" , phone:"+phone);
            //获得当前要秒杀的商品
            Product product=jservice.getHVal("goodsMessage",code,new Product());
            //1.秒杀活动是否结束
            if((secKillServiceImpl.checkTime(product))){

                return Result.error(Meta.TIME_OUT);
            }
            //2.该用户是否参与过该商品的秒杀
            if(!secKillServiceImpl.checkBuyIf(userId,String.valueOf(product.getProductId()))) {

                return Result.error(Meta.REPEATE_MIAOSHA);
            }
            //3.商品是否还有库存
            if(!secKillServiceImpl.checkIfHave(product)){
                overFlags.put(code,false);
                return Result.error(Meta.MIAO_SHA_OVER);
            }
            //消息队列入队
            SeckillMessage message=new SeckillMessage();
            message.setPhone(phone);
            message.setUserId(userId);
            message.setProduct(product);
            mqSender.sendMessage(message);
            //返回秒杀中的状态
            User user=new User();
            user.setUserId(Integer.parseInt(userId));
            return  Result.success(user,Meta.SUCCESSING);
        }catch(Exception e){
            e.printStackTrace();
            overFlags.put(code,true);
            return Result.error(Meta.SERVER_ERROR);
        }



    }
}
