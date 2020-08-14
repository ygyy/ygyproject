package com.glodon.java1.seckill.service.goodsmanagement.impl;

import com.glodon.java1.seckill.dao.UserMapper;
import com.glodon.java1.seckill.domain.User;
import com.glodon.java1.seckill.domain.UserExample;
import com.glodon.java1.seckill.service.goodsmanagement.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean CheckUser(User user) throws Exception {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            return false;
        }
        User user1 = userMapper.selectByPrimaryKey(queryIdByUsername(user));
        if (user1 != null) {
            if (user1.getUsername() != null && user1.getPassword() != null) {
                if (user1.getUsername().equals(user.getUsername()) && user1.getPassword().equals(user.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Integer queryIdByUsername(User user) throws Exception {
        if (user != null && user.getUsername() != null) {
            UserExample userExample = new UserExample();
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andUserNameEqualTo(user.getUsername());
            List<User> users = userMapper.selectByExample(userExample);
            User user1 = users.get(0);
            return user1.getUserId();
        }
        return null;
    }
}
