package com.laidu.bishe.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chenwen on 16/9/6.
 */
public class Properties extends HashMap<String,Object> {

    private static final long serialVersionUID = 1L;

    public Properties(){}

    public String getString(String key,String defaultVal){
        try{
            Object v = get(key);

            if (v == null) return defaultVal;

            if (v instanceof Object[]){
                Object[] nv = (Object[]) v;
                return String.valueOf(nv[0]);
            }

            return String.valueOf(v);
        }catch (Throwable e){
        }
        return defaultVal;
    }

    public String getString(String key){
        return getString(key,null);
    }

    public Long getLong(String key,Long defaultVal){
        return parse(key, defaultVal, new IParse() {
            @Override
            public Long parse(String value) {
                return Long.parseLong(value);
            }
        });
    }

    public Long getLong(String key){
        return getLong(key,null);
    }

    public Integer getInt(String key,Integer defaultVal){
        return parse(key, defaultVal, new IParse() {
            @Override
            public Integer parse(String value) {
                return Integer.parseInt(value);
            }
        });
    }

    public Integer getInt(String key){
        return getInt(key,null);
    }

    public Double getDouble(String key,Double defaultVal){
        return parse(key, defaultVal, new IParse() {
            @Override
            public Double parse(String value) {
                return Double.parseDouble(value);
            }
        });
    }

    public Double getDouble(String key){
        return getDouble(key,null);
    }

    public Boolean getBoolean(String key, Boolean defaultVal){
        return parse(key, defaultVal, new IParse() {
            @Override
            public Boolean parse(String value) {
                return Boolean.parseBoolean(value);
            }
        });
    }

    public Boolean getBoolean(String key){
        return getBoolean(key,null);
    }

    public Float getFloat(String key, Float defaultVal) {
        return parse(key, defaultVal, new IParse() {
            public Float parse(String value) {
                return Float.parseFloat(value);
            }
        });
    }

    public Float getFloat(String key) {
        return getFloat(key,null);
    }

    public List<String> getListString(String key) {
        return getListString(key, null, ",");
    }

    public List<String> getListString(String key, String defaultVal, String split) {
        List<String> list = new ArrayList<String>();
        Object obj = get(key);
        if (obj != null) {
            //若本身就是List 或者 Array 类型，直接返回
            if (obj instanceof List) {
                for (Object v : (List<?>)obj){
                    list.add(String.valueOf(v));
                }
                return list;
            }

            if (obj instanceof Object[]) {
                for (Object v : (Object[])obj){
                    list.add(String.valueOf(v));
                }
                return list;
            }
        }

        //否则按给定的split进行分隔变成数组返回
        String[] arr = this.getString(key, defaultVal == null ? "" : defaultVal).split(split);
        for (String s : arr){
            list.add(s);
        }

        return list;
    }

    public <T> Collection<T> getCollection(String key){
        if (containsKey(key) && get(key) instanceof Collection){
            try{
                return (Collection<T>)get(key);
            }catch (Throwable e){
            }
        }
        return null;
    }


    public Class<?> getClass(String key, Class<?> defaultValue) {
        if (!this.containsKey(key)) {
            return defaultValue;
        }

        return (Class<?>)this.get(key);
    }

    private <T> T parse(String key, T defaultVal,IParse parse){
        try {
            return parse.parse(getString(key));
        } catch (Throwable e){
        }
        return defaultVal;
    }

    private interface IParse{
        <T> T parse(String value);
    }
}
