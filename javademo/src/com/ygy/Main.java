package com.ygy;

public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println("hello world!");
        System.out.println(fun());
    }

    public static  int fun() throws  Exception{
        try {
            System.out.println(1111111111);
            return 1;
        }finally {
            System.out.println(22222);
        }
    }
}
