package com.ygy.aop.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/ygy/aop/xml/applicationContext-xml.xml");
        ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) ctx.getBean("arithmeticCalculator");

        int result = arithmeticCalculator.add(11,12);
        System.out.println("result:"+result);

        result = arithmeticCalculator.div(21,3);
        System.out.println("result:"+result);
    }

}
