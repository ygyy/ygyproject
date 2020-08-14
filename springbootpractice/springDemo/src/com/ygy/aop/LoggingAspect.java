package com.ygy.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(2)
public class LoggingAspect {

    /**
     * 定义一个方法，用于声明切入点表达式，一般的，该方法中不需要添加其他代码
     */
    @Pointcut("execution(public int com.ygy.aop.ArithmeticCalculator.*(int, int))")
    public void declareJointPointExpression(){}

    @Before("declareJointPointExpression()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("The method " + methodName + " 前置begins with " + Arrays.asList(args));
    }

    @After("declareJointPointExpression()")
    public  void afterMethod(JoinPoint joinPoint){
        String methodName=joinPoint.getSignature().getName();
        System.out.println("The method 最终"+methodName+ " ends");
    }

    /**
     * 在方法正常结束后执行的代码
     * 返回通知是可以访问到方法的返回值的
     * @param joinPoint
     */
    @AfterReturning(value="execution(* com.ygy.aop.ArithmeticCalculator.*(int,int)))",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method 返回成功" + methodName + " ends with "+result);
    }

    @AfterThrowing(value="execution(* com.ygy.aop.ArithmeticCalculator.*(int,int)))",throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint,Exception ex){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " occurs excetion: "+ex);
    }

    /**
     * 环绕通知需要携带ProceedingJoinPoint 类型的参数
     * 必须有返回值，返回值为目标方法的返回值
     * @param pjd
     */

    //@Around("execution(* com.ygy.aop.ArithmeticCalculator.*(int,int)))")
    public Object aroundMethod(ProceedingJoinPoint pjd){
        String methodName = pjd.getSignature().getName();
        Object result= null;
        try {
            System.out.println("The method "+ methodName +" begins with " + Arrays.asList(pjd.getArgs()));
            result = pjd.proceed();
            System.out.println("The method ends with "+ result);
        } catch (Throwable throwable) {
            throwable.printStackTrace();

        }
        System.out.println("The aroundMethod " + methodName + " ");
        return result;
    }
}
