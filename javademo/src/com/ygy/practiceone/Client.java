package com.ygy.practiceone;

public class Client {

    public static void main(String[] args) {
        Person p = new Person();
        p.setName("ygy");
        p.setAge(25);
        Result<Person> A = new Result<Person>(p);
        byte[] b = A.getTSer(p);
        Result<Person> B = new Result(b,p);
        System.out.println(A.getT().getName().equals(B.getT().getName()));
        System.out.println(A.getT().getAge()==B.getT().getAge());
    }
}
