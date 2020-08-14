package com.ygy;

public class HelloWorld {
    private String userName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void hello(){
        System.out.println("Hello:"+userName);
    }
}
