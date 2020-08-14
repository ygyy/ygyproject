package com.ygy.innertest;

public class A {

   public U funa(){
       return new U(){

           @Override
           public void funU1() {
               System.out.println("funU1");
           }

           @Override
           public void funU2() {
               System.out.println("funU2");
           }

           @Override
           public void funU3() {
               System.out.println("funU3");
           }
       };
   }
}
