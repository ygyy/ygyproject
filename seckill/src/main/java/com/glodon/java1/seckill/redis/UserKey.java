package com.glodon.java1.seckill.redis;

public class UserKey implements KeyPrefix  {

    @Override
    public  String getPrefix(Integer userId) {
        return "user_"+userId;
    }
}
