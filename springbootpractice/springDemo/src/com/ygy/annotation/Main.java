package com.ygy.annotation;

import com.ygy.annotation.controller.UserController;
import com.ygy.annotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        //1.创建Spring的IOC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-annotation.xml");
        UserController userController = (UserController) ctx.getBean("userController");
        userController.add();
    }


}
