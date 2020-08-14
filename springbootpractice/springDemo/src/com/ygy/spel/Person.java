package com.ygy.spel;

/**
 * @author guoc
 * @create 2019-08-05 10:11 AM
 **/
public class Person {

    private String name;

    //引用address bean的city属性
    private String city;

    //根据car的price确定info：car的price>=300000:金领；否自为白领
    private String info;

    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", info='" + info + '\'' +
                ", car=" + car +
                '}';
    }
}
