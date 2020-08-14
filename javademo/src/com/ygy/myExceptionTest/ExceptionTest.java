package com.ygy.myExceptionTest;

public class ExceptionTest {

    public void g() throws FooAException{
        throw new FooAException("a异常");
    }

    public void f(){
        try{
            g();
        }catch (FooAException a){
            throw new FooBException("f调用g后b的异常");
        }
    }
}
