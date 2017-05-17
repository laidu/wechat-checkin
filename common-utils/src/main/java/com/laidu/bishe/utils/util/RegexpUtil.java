package com.laidu.bishe.utils.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangpin on 16/7/25.
 */
public class RegexpUtil {
    public static String getRegexp(String regexp,String text){
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()){
            return matcher.group();
        } else {
            return null;
        }
    }
}
