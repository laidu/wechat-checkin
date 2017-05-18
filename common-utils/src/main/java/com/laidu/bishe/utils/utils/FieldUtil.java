package com.laidu.bishe.utils.utils;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by chenwen on 16/10/28.
 */
public class FieldUtil {
    public enum Field{
        NAME,
        MOBILE,
        IDCARD,
        EMAIL,
        USERNAME
        ;
    }

    public static Field getFieldByValue(String value){
        if (StringUtils.isEmpty(value)){
            return null;
        }
        if (isMobile(value)){
            return Field.MOBILE;
        }else if (isIdcard(value)){
            return Field.IDCARD;
        }else if (isEmail(value)){
            return Field.EMAIL;
        }else if (isName(value)){
            return Field.NAME;
        }else if (isUsername(value)){
            return Field.USERNAME;
        }
        return null;
    }

    public static boolean isUsername(String username){
        return regex(username,"[0-9\\._A-Za-z]{5,50}");
    }

    public static boolean isName(String value){
        return regex(value,"[\\u4E00-\\u9FA5·a-zA-Z]{1,40}");
    }

    public static boolean isMobile(String value){
        return regex(value,"^1[3|4|5|7|8][0-9]{9}$");
    }

    public static boolean isIdcard(String value){
        return IdcardUtils.validateCard(value);
    }

    public static boolean isEmail(String value){
        return regex(value,"^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    }

    public static  boolean isInsuranceEmail(String value){
        return regex(value,"^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\\.[com|com\\.cn|cn|net|net\\.cn]+$");
    }

    public static  boolean isVin(String value){
        return regex(value,"^[a-zA-Z0-9]{17}$");
    }
    public static  boolean isEngineNo(String value){
        return regex(value,"^[0-9A-Z]+$");
    }

    public static  boolean isOriganizeNum(String value){
        return regex(value,"^([A-Za-z0-9]{9}|[A-Za-z0-9]{18})$");
    }
    public static boolean isNum(String value){
        return regex(value,"^[0-9]+$");
    }

    public static boolean regex(String value,String regex){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(value).find();
    }

    public static void main(String[] args) {
        System.out.println(regex("1234567891234567811",""));
    }
}
