package com.companyname.redisjavaclientdemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketReaderTest {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        Socket s;
        while ((s = ss.accept()) != null) {
            final Socket s1 = s;
            new Thread(() -> {
                try {
                    InputStream in = s1.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
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
        }
    }
}
