package com.laidu.bishe.utils.util;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.Set;

/**
 * Created by chenwen on 16/11/4.
 */
public class ReflectionUtil {
    /**
     * 获取某个类的所有子类
     * @param packagePath 包路径
     * @param type 类型
     * @param <T> 类型列表
     * @return
     */
    public static <T> Set<Class<? extends T>> getSubClass(String packagePath, Class<T> type){
        Reflections reflections = new Reflections(packagePath, new SubTypesScanner(true));
        return reflections.getSubTypesOf(type);
    }
}
