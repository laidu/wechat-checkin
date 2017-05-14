package com.laidu.bishe.utils.filter;

import org.redisson.api.RedissonClient;

/**
 * Created by xueyunlong on 16-12-6.
 */
public class ProxyIpDuplicateFilter implements IDuplicateFilter {
    private String name;

    public RedissonClient getRedisson() {
        return redisson;
    }

    private RedissonClient redisson;

    public ProxyIpDuplicateFilter(String name, RedissonClient redisson){
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
