package com.ygy;

import com.ygy.autowire.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        //1.创建Spring的IOC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
      /*  //2.从容器中获取Bean
        HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
        helloWorld.setUserName("ygy");
        //3.调用方法
        helloWorld.hello();

        Person person = (Person) ctx.getBean("person");
        System.out.println(person.toString());

        com.ygy.collections.Person personList= (com.ygy.collections.Person) ctx.getBean("person1");
        System.out.println(personList.toString());*/

        Person personAutowire = (Person) ctx.getBean("person");
        System.out.println(personAutowire.toString());
    }

}
