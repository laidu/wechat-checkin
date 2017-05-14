package com.laidu.bishe.utils.util;

import java.util.regex.Pattern;

/**
 * Created by laidu on 16/11/30.
 */
public class CustIdUtil {
    public static boolean isCustId(String custId){
//        return custId != null && Pattern.compile("^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$").matcher(custId).find();
        return true;
    }
}
