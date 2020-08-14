package com.glodon.springboot.spring_boot_demo.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
//@PropertySources("classpath:application.yml")
@ConfigurationProperties(prefix = "boy")
public class BoyDomain {

    private  String age;
    private  Integer height;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
