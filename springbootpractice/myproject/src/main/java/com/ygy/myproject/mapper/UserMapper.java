package com.ygy.myproject.mapper;

import com.ygy.myproject.model.User;

import java.util.List;

public interface UserMapper {

    User getUser(Integer id);//根据id获取信息
    boolean addUser(User user);//新增
    boolean modifyUser(User user);//修改
    boolean deleteUser(Integer id);//根据id删除
    List<User> getAll();//获取所有
}


