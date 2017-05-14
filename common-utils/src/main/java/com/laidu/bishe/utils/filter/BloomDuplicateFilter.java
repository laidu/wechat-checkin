package com.laidu.bishe.utils.filter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by chenwen on 16/9/20.
 */
public class BloomDuplicateFilter implements IDuplicateFilter {
    private final BloomFilter<CharSequence> bloomFilter;

    private long expectedInsertions;

    private double fpp;

    private AtomicLong counter;

    public BloomDuplicateFilter(int expectedInsertions){
        this(expectedInsertions, 0.01);
    }

    public BloomDuplicateFilter(int expectedInsertions, double fpp){
        this.expectedInsertions = expectedInsertions;
        this.fpp = fpp;
        this.bloomFilter = rebuildBloomFilter();
    }

    protected BloomFilter<CharSequence> rebuildBloomFilter() {
        counter = new AtomicLong(0);
        return BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), expectedInsertions, fpp);
    }

    @Override
    public boolean isDuplicate(String key) {
        boolean isDuplicate = bloomFilter.mightContain(key);
        if (!isDuplicate) {
            bloomFilter.put(key);
            counter.incrementAndGet();
        }
        return isDuplicate;
    }

    @Override
    public long size() {
        return counter.get();
    }

    @Override
    public void reset() {
        rebuildBloomFilter();
    }
}
