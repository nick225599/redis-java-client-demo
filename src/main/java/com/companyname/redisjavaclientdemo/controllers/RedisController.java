package com.companyname.redisjavaclientdemo.controllers;

import com.companyname.redisjavaclientdemo.dto.common.Result;
import com.companyname.redisjavaclientdemo.dto.redis.RedisCommand;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public Result get(@PathVariable("key") String key) {
        if (Strings.isNullOrEmpty(key)) return Result.fail("key is null or empty");

        //TODO scs 20230505 支持从http请求中获取 key，温习了resp2协议，开始准备连接redis服务器

        log.info("key: {}", key);
        return Result.ok();
    }
}
