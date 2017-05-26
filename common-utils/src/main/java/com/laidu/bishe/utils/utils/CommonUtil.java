package com.laidu.bishe.utils.utils;

import org.apache.commons.lang.StringUtils;

public class CommonUtil {
    /**
     * 头峰式转下划线
     * @param name
     * @return
     */
    public static String to_(String name){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < name.length() ; ++i){
            char c = name.charAt(i);
            if (c >= 'A' && c <= 'Z'){
                stringBuilder.append("_").append(Character.toLowerCase(c));
            }else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    public static String toUpper(String s){
        if (StringUtils.isNotEmpty(s) && s.contains("_")){
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i < s.length(); ++i){
                if (s.charAt(i) == '_'){
                    if (i + 1 < s.length()) {
                        stringBuilder.append(Character.toUpperCase(s.charAt(++i)));
                    }
                }else {
                    stringBuilder.append(s.charAt(i));
                }
            }
            return stringBuilder.toString();
        }
        return s;
    }
}
