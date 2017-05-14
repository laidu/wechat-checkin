package com.laidu.bishe.utils.filter;

import org.redisson.api.RedissonClient;

/**
 * Created by chenwen on 16/9/20.
 */
public class RedisHashSetDuplicateFilter implements IDuplicateFilter {
    private String name;

    private RedissonClient redisson;

    public RedisHashSetDuplicateFilter(String name, RedissonClient redisson){
        this.name = name;
        this.redisson = redisson;
    }

    @Override
    public boolean isDuplicate(String key) {
        return !redisson.getSet(name).add(key);
    }

    @Override
    public long size() {
        return redisson.getSet(name).size();
    }

    @Override
    public void reset() {
        redisson.getSet(name).clear();
    }
}
