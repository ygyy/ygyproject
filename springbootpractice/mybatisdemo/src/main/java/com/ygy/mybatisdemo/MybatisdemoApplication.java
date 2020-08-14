package com.ygy.mybatisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.ygy.mybatisdemo.dao")
@EnableSwagger2//开启Swagger2功能，方便测试RestApi
public class MybatisdemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(MybatisdemoApplication.class, args);
    }

}
