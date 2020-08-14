package com.ygy.autowire;

public class Person {
    private String name;

    public void setAddress(Address address) {
        this.address = address;
    }

    private Address address;
    private  Car car;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return this.name+","+this.address+","+this.car;
    }
}
