package com.ygy.myproject.service;

import com.ygy.myproject.mapper.UserMapper;
import com.ygy.myproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User getUser(Integer id){
        return userMapper.getUser(id);
    }

    public boolean addUser(User user){
        return userMapper.addUser(user);
    }

    public boolean modifyUser(User user){
        return userMapper.modifyUser(user);
    }

    public boolean deleteUser(Integer id){
        return userMapper.deleteUser(id);
    }

    public List<User> getAll(){
        return userMapper.getAll();
    }
}
