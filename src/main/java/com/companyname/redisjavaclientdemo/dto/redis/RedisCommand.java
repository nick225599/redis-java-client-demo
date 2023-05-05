package com.companyname.redisjavaclientdemo.dto.redis;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisCommand {
    private String key;
    private String value;
}
