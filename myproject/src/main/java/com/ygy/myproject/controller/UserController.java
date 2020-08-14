package com.ygy.myproject.controller;

import com.ygy.myproject.model.User;
import com.ygy.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Resource
    DataSource dataSource;//数据源信息

    /**
     * 获取默认的数据源信息
     * @return
     * @throws SQLException
     */
    @ResponseBody
    @GetMapping("/dataSource/get")
    public Map<String,Object> getDefaultDataSource() throws SQLException {
        Map<String,Object> map = new HashMap<>();
        map.put("dataSource",dataSource.getClass());
        map.put("jdbcURL",dataSource.getConnection().getMetaData().getURL());
        System.out.println("默认的数据源为："+dataSource.getClass());
        System.out.println("连接地址："+dataSource.getConnection().getMetaData().getURL());
        return map;
    }

    /**
     * 根据id获取信息
     * @param id
     * @return
     * @throws SQLException
     */
    @ResponseBody
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Integer id){
        return userService.getUser(id);
    }

    /**
     * 获取所有信息
     * @return
     */
    @ResponseBody
    @GetMapping("/users")
    public List<User> getAll(){
        return userService.getAll();
    }

    /**
     * 新增信息
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/users")
    public boolean addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    /**
     * 修改信息
     * @param user
     * @return
     */
    @ResponseBody
    @PutMapping("/users")
    public boolean modifyUser(@RequestBody User user){
        return userService.modifyUser(user);
    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/users/{id}")
    public boolean deleteUser(@PathVariable("id") Integer id){
        return userService.deleteUser(id);
    }
}
