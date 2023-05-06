package com.companyname.redisjavaclientdemo.controllers;

import com.companyname.redisjavaclientdemo.commons.RedisStringConstants;
import com.companyname.redisjavaclientdemo.dto.common.Result;
import com.companyname.redisjavaclientdemo.dto.redis.RedisCommand;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


@RestController
@Slf4j
public class RedisController {

    @PostMapping("/set")
    public Result set(@RequestBody RedisCommand redisCommand) {
        String key = redisCommand.getKey();
        String value = redisCommand.getValue();
        if (Strings.isNullOrEmpty(key)) return Result.fail("key is null or empty");
        if (Strings.isNullOrEmpty(value)) return Result.fail("value is null or empty");

        log.info("key: {}, value: {}", key, value);
        return Result.ok();
    }

    @GetMapping("/get/{key}")
    public Result get(@PathVariable("key") String key) throws Exception {
        if (Strings.isNullOrEmpty(key)) return Result.fail("key is null or empty");

        //TODO scs 20230505 支持从http请求中获取 key，温习了resp2协议，开始准备连接redis服务器
        String temp = "*2\r\n"
                + "$3\r\nGET\r\n"
                + "$4\r\nname\r\n";

        Socket s = new Socket();
        s.connect(new InetSocketAddress(RedisStringConstants.REDIS_SERVICE_IP,
                RedisStringConstants.REDIS_SERVICE_PORT));
        log.info("连接成功");

        OutputStream outputStream = s.getOutputStream();
        outputStream.write(temp.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        log.info("flush");
        log.info("os class name: {}", outputStream.getClass().getName());

        InputStream inputStream = s.getInputStream();
        log.info("in class name: {}", inputStream.getClass().getName());

        log.info("start read");
        int i;
        int index = 0;
        byte[] buff = new byte[100];
        do{
            i = inputStream.read();
            buff[index++] = (byte) i;
        }
        while(i != '\r' && i!='\n');
        log.info("index: {}", index);

        log.info("buff: {}" , new String(buff,0, index, StandardCharsets.UTF_8));


        log.info("key: {}", key);
        return Result.ok();
    }
}
