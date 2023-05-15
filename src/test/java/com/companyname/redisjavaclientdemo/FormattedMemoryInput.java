package com.companyname.redisjavaclientdemo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FormattedMemoryInput {
    public static void main(String[] args) throws IOException {
        byte[] bytes = "阳澄湖".getBytes("utf-32");
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        byte b;
        List<Byte> bitList = new ArrayList<>();

        while((b = (byte)dis.read())!= -1){
            System.out.print(b);
            System.out.print(", ");
            bitList.add(b);
        }
        System.out.println();
        Object[] objArr = bitList.toArray();
        byte[] intArr = new byte[objArr.length];
        for(int i1 = 0; i1 < objArr.length; i1++){
            intArr[i1] = (byte)objArr[i1];
        }

        System.out.println(new String(intArr,"utf-32"));
    }

    public static void main2(String[] args) throws FileNotFoundException {
        OutputStream os = new FileOutputStream("temp.txt");
        InputStream is = new FileInputStream("temp.txt");
//        is.read()

    }
}
