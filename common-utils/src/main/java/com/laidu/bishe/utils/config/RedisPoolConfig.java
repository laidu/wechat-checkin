package com.laidu.bishe.utils.config;

import lombok.Data;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.net.URI;

/**
 * Created by xueyunlong on 16-12-17.
 */
@Data
public class RedisPoolConfig {

    private int maxTotal = 8;
    private int maxIdle = 8;
    private int minIdle = 0;
    private String uri = null;


    public static void main(String[] args) {
        RedisPoolConfig redisPoolConfig = new RedisPoolConfig();
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(500);
        genericObjectPoolConfig.setMaxTotal(500);
        genericObjectPoolConfig.setMinIdle(5);

        System.out.println(redisPoolConfig.toString());
    }
}
