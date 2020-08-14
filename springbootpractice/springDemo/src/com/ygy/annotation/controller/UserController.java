package com.ygy.annotation.controller;

import com.ygy.annotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public void add(){
        System.out.println("controller add...");
        userService.add();
    }
}
