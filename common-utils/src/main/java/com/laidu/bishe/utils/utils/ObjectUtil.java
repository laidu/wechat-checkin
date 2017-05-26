package com.laidu.bishe.utils.utils;

import java.util.Collection;
import java.util.Map;
public class ObjectUtil {

    /**
     * 两个对象是否同时为空,两个对象是否同时不为空
     * @param o1
     * @param o2
     * @return
     */
    public static boolean isNullOrNotNull(Object o1,Object o2){
        return isNull(o1,o2) || isNotNull(o1,o2);
    }

    /**
     * 两个对象是否同时为空
     * @param o1
     * @param o2
     * @return
     */
    public static boolean isNull(Object o1,Object o2){
        return o1 == null && o2 == null;
    }

    /**
     * 两个对象是否同时不为空
     * @param o1
     * @param o2
     * @return
     */
    public static boolean isNotNull(Object o1,Object o2){
        return o1 != null && o2 != null;
    }


    /**
     * 获取str值
     * @param object
     * @return
     */
    public static String getStrValue(Object object){
        return object == null ? null : String.valueOf(object);
    }


    /**
     * 获取value
     * @param params
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getValue(Map<String,Object> params, String key){
        if (params != null){
            if (params.containsKey(key)){
                return (T) params.get(key);
            }
        }
        return null;
    }


    /**
     * 添加不为空的值
     * @param json
     * @param key
     * @param o
     */
    public static void addIfNotNull(Map<String,Object> json, String key , Object o){
        if (o instanceof Collection){
            if (CollectionUtils.isNotEmpty((Collection) o)){
                json.put(key,o);
            }
        }else {
            if (o != null) {
                json.put(key, o);
            }
        }
    }
}
