package com.ygy;

import java.util.Arrays;

public class ArrayTest {
    int a;
    public ArrayTest(int length){
        this.a=a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArrayTest arrayTest = (ArrayTest) o;
        return a == arrayTest.a;
    }

    public static void main(String[] args) {
        ArrayTest[] at = new ArrayTest[5];
        for (int i = 0; i < 5; i++) {
            at[i]=new ArrayTest(1);
        }
        ArrayTest[] bt = new ArrayTest[5];
        for (int i = 0; i < 5; i++) {
            bt[i]=new ArrayTest(1);
        }
        System.out.println(Arrays.equals(at,bt));
    }
}
