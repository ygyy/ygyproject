package com.glodon.springboot.spring_boot_demo.controller;

import com.glodon.springboot.spring_boot_demo.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @RequestMapping("demo1")
    public String demo1(Model model){
        model.addAttribute("message","hello Thymeleaf");
        return "demo1";
    }

    @RequestMapping("/demo2")
    public String demo2(HttpServletRequest request , Model model){
        //变量输出
        model.addAttribute("name","张三");
        //条件判断
        model.addAttribute("gender", "女");
        model.addAttribute("grade",3);//等级
        //迭代遍历
        List<User> list = new ArrayList<User>();
        list.add(new User(1,"eric",20));
        list.add(new User(2,"jack",22));
        list.add(new User(3,"rose",24));
        model.addAttribute("list",list);
        //域对象的使用
        //request
        request.setAttribute("request", "request's data");
        //session
        request.getSession().setAttribute("session", "session's data");
        //application
        request.getSession().getServletContext().setAttribute("application","application's data");
        return "demo2";
    }

}
