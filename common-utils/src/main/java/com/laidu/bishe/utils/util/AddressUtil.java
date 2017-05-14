package com.laidu.bishe.utils.util;

import java.util.regex.Pattern;

/**
 * Created by huangwd on 16-12-3.
 */
public class AddressUtil {
    public static boolean isAddress(String address){
//        return custId != null && Pattern.compile("^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$").matcher(custId).find();
//        验证IP地址和经纬度（纬度在前，中间逗号）
        return address != null && (Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$").matcher(address).find() || Pattern.compile("^[\\-\\+]?[0-9]?\\d{0,1}.\\d+,[\\-\\+]?0?\\d{1,3}\\.\\d+$").matcher(address).find());
    }
}
