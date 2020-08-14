package com.ygy.spel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author guoc
 * @create 2019-08-05 9:36 AM
 **/
public class Main {
    public static void main(String[] args){
        //创建spring的ioc容器
        //ApplicationContext 代表容器
        //ClassPathXmlApplicationContext：是ApplicationContext接口的实现类，该实现类从类路径下来加载配置文件
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-spel.xml");


        Address address = (Address) ctx.getBean("address");
        System.out.println(address);

        Car car = (Car) ctx.getBean("car");
        System.out.println(car);

        Person person = (Person) ctx.getBean("preson");
        System.out.println(person);
    }
}
