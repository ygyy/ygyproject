package com.ygy;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Comparator;

public class IntegerTest {

    public static void main(String[] args) {
        Integer[] arr = new Integer[10];
        for(int i=0;i<10;i++){
            arr[i]=(int)(Math.random()*100);
        }
        //未排序
        System.out.println("排序前："+Arrays.toString(arr));
        //升序
        Arrays.sort(arr);
        System.out.println("升序："+Arrays.toString(arr));
        //降序
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b-a;
            }
        });
        System.out.println("降序："+Arrays.toString(arr));
    }
}
