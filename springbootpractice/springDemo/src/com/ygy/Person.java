package com.ygy;

public class Person {

    private String userName;
    private int age;
    private Car car;

    public Person(String userName, int age, Car car) {
        this.userName = userName;
        this.age = age;
        this.car = car;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return this.userName+","+this.age+","+this.car.getName();
    }
}
