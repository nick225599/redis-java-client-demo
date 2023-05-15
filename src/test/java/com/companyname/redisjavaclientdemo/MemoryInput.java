package com.companyname.redisjavaclientdemo;

import java.io.IOException;
import java.io.StringReader;

public class MemoryInput {
    public static void main(String[] args) throws IOException {
        StringReader in = new StringReader("hello风雨📖欲来花满楼\u0419FDم");
        int c;
        while((c = in.read()) != -1){
            System.out.print((char)c);
        }
    }
}
