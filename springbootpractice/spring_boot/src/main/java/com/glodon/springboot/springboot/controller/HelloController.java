package com.glodon.springboot.springboot.controller;

import com.glodon.springboot.springboot.property.BoyDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guoc
 * @create 2020-08-04 2:05 下午
 **/
@RestController
public class HelloController {

    /**
     *通过注解把配置文件的值赋在变量上
     */
    @Value("${aaa}")
    private String aa;

    @Value("${age}")
    private int age;

    @Value("${boy.height}")
    private int height;

    @Value("${context}")
    private String context;

    @Autowired
    private BoyDomain boyDomain;

    @RequestMapping(value="/hello",method= RequestMethod.GET)
    public String say(){
        return "Hello Spring Boot=="+aa+age+"context==="+context+"======height:"+height;
    }

    @RequestMapping(value = "getboy",method = RequestMethod.GET)
    public String getBoy(){
        return "男孩的年龄是："+boyDomain.getAge();
    }

    @RequestMapping(value = {"/one","/two"},method = RequestMethod.GET)
    public String towUrl(){
        return "两个url访问同一个方法";
    }

}
