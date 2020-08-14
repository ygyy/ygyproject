package com.ygy.autowire;

public class Car {
    private  String brand;
    private  double price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Car() {
        System.out.println("初始化");;
    }

    @Override
    public String toString() {
        return this.brand+","+this.price;
    }
}
