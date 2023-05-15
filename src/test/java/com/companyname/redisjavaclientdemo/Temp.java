package com.companyname.redisjavaclientdemo;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Temp {
    public static void main(String[] args) throws InterruptedException, IOException {
        char c  = '中';
        System.out.println("中转换成 int");
        System.out.println((int )c);
        System.out.println();
        System.out.println("中转换成 int 再转换成二进制");
        System.out.println(Integer.toBinaryString((int )c));
//          01001110 00101101

//        11111111111111111111111111100100
//        11111111111111111111111110111000
//        11111111111111111111111110101101
        System.out.println();
        System.out.println("中转换字节");
        byte[] bytes = Character.valueOf(c).toString().getBytes();
        for(byte b: bytes)
            System.out.println(Integer.toBinaryString(b));

        String tmp ="";
        OutputStream os = System.out;
        Writer writer = new OutputStreamWriter(os);

    }
}
