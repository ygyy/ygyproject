package com.ygy.practicetwo;

import java.io.*;

public class CopyFileTest {
    public static void main(String[] args) {
        try {
            long a = copyOne();
            long b = copyTwo();
            System.out.println("buffer拷贝文件比普通拷贝快："+(a-b)+"秒");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static long copyOne() throws IOException {

        long start = System.currentTimeMillis();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File src = new File("src/com/ygy/practicetwo/hello.txt");
            File desc = new File("src/com/ygy/practicetwo/hellocopy1.txt");
            fis = new FileInputStream(src);
            fos = new FileOutputStream(desc);
            byte[] b = new byte[1024 * 1024];
            int len = -1;
            while ((len = fis.read(b)) != -1) {
                fos.write(b, 0, len);
                fos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            fis.close();
            fos.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("普通拷贝共用时：" + (end - start) + "秒");
        return end - start;
    }

    public static long copyTwo() throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try{
            File inFile = new File("src/com/ygy/practicetwo/hello.txt");
            File outFile = new File("src/com/ygy/practicetwo/hellocopy2.txt");
            fileInputStream = new FileInputStream(inFile);
            fileOutputStream = new FileOutputStream(outFile);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            int content = 0;

            while((content = bufferedInputStream.read())!=-1){
                bufferedOutputStream.write(content);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭资源
            bufferedOutputStream.close();
            bufferedInputStream.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("Buffer拷贝共用时：" + (end - start) + "秒");
        return end - start;
    }

}
