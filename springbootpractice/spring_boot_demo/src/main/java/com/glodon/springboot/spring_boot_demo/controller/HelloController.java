package com.glodon.springboot.spring_boot_demo.controller;

import com.glodon.springboot.spring_boot_demo.property.BoyDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${aa}")
    private String aa;
    @Value("${age}")
    private int age;
    @Value("${context}")
    private String context;
    @Value("${boy.height}")
    private int height;
    @Autowired
    private BoyDomain boy;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String say(){
        return "Hello Spring Boot=="+aa+age+"context==="+context;
    }
    @RequestMapping(value = "/getboy",method = RequestMethod.GET)
    public String boy(){
        return "男孩的年龄"+boy.getAge()+".";
    }
    @RequestMapping(value = {"/one","two"},method = RequestMethod.GET)
    public String towUrl(){
        return "两个url访问同一个方法";
    }

}
