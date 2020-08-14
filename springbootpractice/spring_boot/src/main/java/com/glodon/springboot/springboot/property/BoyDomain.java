package com.glodon.springboot.springboot.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/3/26 0026.
 * 现在是配置了两个配置文件，这个ConfigurationProperties必须去application.yml中找数据，只适合一个
 */
@Component
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "boy")
public class BoyDomain {
    private String age;

    private Integer height;

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
