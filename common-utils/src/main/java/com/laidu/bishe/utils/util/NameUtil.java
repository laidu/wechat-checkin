package com.laidu.bishe.utils.util;

/**
 * Created by xueyunlong on 16-12-15.
 */
public class NameUtil {
    public static boolean isLegalName(String name){
        return name.matches("^([\\u4e00-\\u9fa5]+|([a-zA-Z]+\\s?)+)$") ? true : false;
    }

    public static void main(String[] args) {
        System.out.println(NameUtil.isLegalName("薛云龙哈哈"));
    }
}
