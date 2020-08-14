package com.ygy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    /**
     * 输入用户名字name，返回hello name
     * @param name
     * @return
     */
    @GetMapping("/hello/{name}")
    public String helloWorld(@PathVariable String name){
        return "hello "+ name;
    }
}