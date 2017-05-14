package com.laidu.bishe.utils.util;

import java.util.regex.Pattern;

/**
 * Created by chenwen on 16/10/9.
 */
public class MobileUtil {
    public static boolean isMobile(String mobile){
        return mobile != null && Pattern.compile("^1[3,5,7,4,8]\\d{9}$").matcher(mobile).find();
    }
}
