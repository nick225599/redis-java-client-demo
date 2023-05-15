package com.companyname.redisjavaclientdemo;

import java.io.*;

public class MemoryInput {
    public static void main(String[] args) throws IOException, InterruptedException {
//        StringReader in = new StringReader("hello风雨📖欲来花满楼\u0419FDم");
//        int c;
//        while((c = in.read()) != -1){
//            System.out.print((char)c);
//        }

        // char 与 int 是通过 unicode 字符集强转的
        Character c  = 'م';
        System.out.println(Integer.toHexString((int)(c)));
        System.out.println("\u0645");

        // 字节流到字符流的转换也需要制定编码格式,📖 不能转成一个字符，所以还是会乱码
        String charsetName = "utf-8";
        InputStream in2 = new ByteArrayInputStream(
                "hello风雨📖欲来花满楼\u0419FDم".getBytes(charsetName));
        Reader reader = new InputStreamReader(in2, charsetName);
        int i2;
        while((i2 = reader.read()) != -1){
            System.out.print((char)i2);
            System.out.print(", ");
        }
        System.out.println();
        System.out.println("----");

        // 直接用字节流使用同样的字符集写入写出，还原成字符串就可以保留 📖 完好无损
        // 所以字符串里存储的是 byte[] 而不是 char[]
        in2 = new ByteArrayInputStream(
                "hello风雨📖欲来花满楼\u0419FDم".getBytes(charsetName));
        byte b;
        byte[] bytes = new byte[in2.available()];
        int i = 0;
        while((b = (byte)in2.read()) != -1){
            bytes[i++] = b;
            System.out.print(b);
            System.out.print(", ");
        }
        System.out.println();
        System.out.println(new String(bytes, charsetName));
    }
}
