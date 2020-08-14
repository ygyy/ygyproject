package com.ygy.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

public class LoggingAspect {

    /**
     * 定义一个方法，用于声明切入点表达式，一般的，该方法中不需要添加其他代码
     */
    public void declareJointPointExpression(){}

    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("The method " + methodName + " 前置begins with " + Arrays.asList(args));
    }

    public  void afterMethod(JoinPoint joinPoint){
        String methodName=joinPoint.getSignature().getName();
        System.out.println("The method 最终"+methodName+ " ends");
    }
}
