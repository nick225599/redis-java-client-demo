package com.companyname.redisjavaclientdemo.commons.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkService implements Runnable {
    private final ServerSocket serverSocket;
    private final ExecutorService pool;

    public NetworkService(int port, int poolSize) throws IOException {
        serverSocket = new ServerSocket(port);
        pool = Executors.newFixedThreadPool(poolSize, new CustomizableThreadFactory("http-thread-"));
    }

    public void run() {
        // run the service
        try {
            for (; ; ) {
                pool.execute(new EchoHandler(serverSocket.accept()));
            }
        } catch (IOException ex) {
            pool.shutdown();
        }
    }

    public static void main(String[] args) throws IOException {
        NetworkService service = new NetworkService(8081, 200);
        new Thread(service).start();
    }
}


@Slf4j
class EchoHandler implements Runnable {
    private final Socket socket;

    EchoHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        InputStream is = null;
        OutputStream os = null;
        try {
            // read and service request on socket
            is = socket.getInputStream();
            os = socket.getOutputStream();

            List<Byte> content = new ArrayList<>(1024);
            int tempI;
            // http 链接会保持 socket 链接，但是无内容写入，服务器端要通过 content-length 来读
            while((tempI = is.read()) != -1){
                content.add((byte)tempI);
            }

            byte[] bytes = new byte[content.size()];
            int i = 0;
            for(byte b : content){
                bytes[i++] = b;
            }
            log.info("request: " + new String(bytes));
            os.write(bytes);
            os.flush();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }

            } catch (IOException var2) {
                log.error(var2.getMessage(), var2);

            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException var2) {
                log.error(var2.getMessage(), var2);


            }
        }
    }
}
