package com.laidu.bishe.utils.filter;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;

/**
 * Created by chenwen on 16/9/20.
 */
public class RedisBloomDuplicateFilter implements IDuplicateFilter {
    private long expectedInsertions;

    private double fpp;

    private RedissonClient redisson;

    private String name;

    public RedisBloomDuplicateFilter(int expectedInsertions, String name, RedissonClient redisson){
        this(expectedInsertions, 0.01, name, redisson);
    }

    public RedisBloomDuplicateFilter(int expectedInsertions, double fpp,String name, RedissonClient redisson){
        this.expectedInsertions = expectedInsertions;
        this.fpp = fpp;
        this.name = name;
        this.redisson = redisson;
        rebuildBloomFilter();
    }

    protected void rebuildBloomFilter() {
        getRBloomFilter().tryInit(expectedInsertions,fpp);
    }

    @Override
    public boolean isDuplicate(String key) {
        boolean isDuplicate = getRBloomFilter().contains(key);
        if (!isDuplicate) {
            getRBloomFilter().add(key);
            getRAtomicLong().incrementAndGet();
        }
        return isDuplicate;
    }

    @Override
    public long size() {
        return getRAtomicLong().get();
    }

    @Override
    public void reset() {
        rebuildBloomFilter();
    }

    private RBloomFilter<CharSequence> getRBloomFilter(){
        return redisson.getBloomFilter(name);
    }

    private RAtomicLong getRAtomicLong(){
        return redisson.getAtomicLong(String.format("%s_count",name));
    }
}
