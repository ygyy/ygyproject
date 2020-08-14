package com.ygy.collections;

import com.ygy.collections.Car;

import java.util.List;

public class Person {

    private String userName;
    private int age;
    private List<Car> carList;

    public Person(String userName, int age, List<Car> carList) {
        this.userName = userName;
        this.age = age;
        this.carList = carList;
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

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    @Override
    public String toString() {
        return this.userName+","+this.age+","+this.carList;
    }
}
