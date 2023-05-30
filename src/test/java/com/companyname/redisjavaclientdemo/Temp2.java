package com.companyname.redisjavaclientdemo;

import org.redisson.Redisson;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.concurrent.TimeUnit;

public class Temp2 {
    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.setTransportMode(TransportMode.EPOLL);
        config.useClusterServers()
                // use "rediss://" for SSL connection
                .addNodeAddress("perredis://127.0.0.1:7181");

        RedissonClient redisson = Redisson.create(config);
        RSemaphore semaphore = redisson.getSemaphore("mySemaphore");

// acquire single permit
        semaphore.acquire();

// or acquire 10 permits
        semaphore.acquire(10);
        boolean res;
// or try to acquire permit
         res = semaphore.tryAcquire();

// or try to acquire permit or wait up to 15 seconds
         res = semaphore.tryAcquire(15, TimeUnit.SECONDS);

// or try to acquire 10 permit
         res = semaphore.tryAcquire(10);

// or try to acquire 10 permits or wait up to 15 seconds
         res = semaphore.tryAcquire(10, 15, TimeUnit.SECONDS);
        if (res) {
            try {
     // ...
            } finally {
                semaphore.release();
            }
        }
    }
}
