package com.companyname.redisjavaclientdemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class SocketWriterTest {


    public static void main(String[] args) throws IOException, InterruptedException {

        Socket s = new Socket();
        s.connect(new InetSocketAddress("127.0.0.1", 6379));
        OutputStream os = s.getOutputStream();
        InputStream in = s.getInputStream();
        Charset charset = StandardCharsets.UTF_16;

        new Thread(() -> {
            try {
                InputStreamReader reader = new InputStreamReader(in);
                int i;
                while ((i = reader.read()) != -1) {
                    System.out.print((char) i);
                }
                System.out.println("---- over ----");
                System.out.println();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        String auth =
                "*2\r\n"
                        + "$4\r\n"
                        + "AUTH\r\n"
                        + "$8\r\n"
                        + "foobared\r\n"
                ;
        os.write(auth.getBytes());
        os.flush();
        String temp;

        String name = "千里共婵娟";
        temp =
                "*3\r\n"
                + "$3\r\n"
                + "SET\r\n"
                + "$4\r\n"
                + "NAME\r\n"
                + "$"+name.getBytes().length+"\r\n"
                + name + "\r\n"
                ;
        os.write(temp.getBytes());
        os.flush();

        temp = "*2\r\n"
                + "$3\r\nGET\r\n"
                + "$4\r\nNAME\r\n";
        os.write(temp.getBytes());
        os.flush();

        TimeUnit.SECONDS.sleep(10);
        os.close();
    }
}
