package com.companyname.redisjavaclientdemo.threadspool;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2,
                new CustomizableThreadFactory("demo-thread-pool-"));
        es.execute(() -> System.out.println("Runnable.run() done"));
    }
}
