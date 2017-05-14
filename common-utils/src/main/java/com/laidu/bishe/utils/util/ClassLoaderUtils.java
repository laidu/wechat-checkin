package com.laidu.bishe.utils.util;

/**
 * Created by chenwen on 16/9/18.
 */
public class ClassLoaderUtils {

    /**
     * get class instance by default constructor
     * @param className
     * @param <T>
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static  <T> T getClassInstance(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<T> tClass = (Class<T>) Class.forName(className);
        return tClass.newInstance();
    }
}
