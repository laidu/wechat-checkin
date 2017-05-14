package com.laidu.bishe.utils.filter;

/**
 * Created by chenwen on 16/9/20.
 */
public interface IDuplicateFilter {
    /**
     * isDuplicate
     * @param key key
     * @return is or not Duplicate, if is Duplicate, return true.
     */
    boolean isDuplicate(final String key);

    /**
     * get key's size
     * @return size
     */
    long size();

    /**
     * clear
     */
    void reset();
}
