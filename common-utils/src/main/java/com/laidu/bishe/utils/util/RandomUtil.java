package com.laidu.bishe.utils.util;

import org.apache.commons.lang.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by cw on 15-12-16.
 */
public class RandomUtil {
    public final static String format_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

    public static String getRandomId(){
        String str = UUID.randomUUID().toString().toUpperCase().replace("-","") + UUID.randomUUID().toString().toUpperCase().replace("-","");
        if (str.length() > 64){
            str = str.substring(0,64);
        }
        return str;
    }

    public static String getRandomId(int length){
        String str = UUID.randomUUID().toString().toUpperCase().replace("-","") + UUID.randomUUID().toString().toUpperCase().replace("-","");
        if (str.length() > length){
            str = str.substring(0,length);
        }
        return str;
    }

    public static int getRandom(int length){
        return (int)(Math.random()*Math.pow(10,length)) + 1;
    }
    /**
     * get format date
     * @param format
     * @return
     */
    public static String getCurrentTimeAsStringByFormat(String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(getRandomId(32));
    }
}
