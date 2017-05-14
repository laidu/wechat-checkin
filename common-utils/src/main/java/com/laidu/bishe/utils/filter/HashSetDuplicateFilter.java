package com.laidu.bishe.utils.filter;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by chenwen on 16/9/20.
 */
public class HashSetDuplicateFilter implements IDuplicateFilter {
    private Set<String> keys = Sets.newConcurrentHashSet();

    @Override
    public boolean isDuplicate(String key) {
        return !keys.add(key);
    }

    @Override
    public long size() {
        return keys.size();
    }

    @Override
    public void reset() {
        keys.clear();
    }
}
