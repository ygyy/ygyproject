package com.ygy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HelloController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }
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