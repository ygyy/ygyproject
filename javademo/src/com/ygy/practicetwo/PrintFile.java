package com.ygy.practicetwo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class PrintFile {
    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        List<String> list = null;
        try {
            br = new BufferedReader(new FileReader("src/com/ygy/practicetwo/hello.txt"));
            list = new LinkedList<String>();
            String str = "";
            while((str = br.readLine()) != null){
                list.add(str);
            }
            ListIterator it = list.listIterator();
            while(it.hasNext()){
                it.next();//指针移到最后一个元素后面
            }
            while(it.hasPrevious()){
                System.out.println(it.previous().toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            br.close();
        }
    }

}
