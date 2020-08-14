package com.ygy.innertest;

public class Client {

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        U[] u=b.save(a.funa());
        b.funeach(u);
    }


}
