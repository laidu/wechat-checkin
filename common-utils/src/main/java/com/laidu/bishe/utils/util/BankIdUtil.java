package com.laidu.bishe.utils.util;

import java.util.regex.Pattern;

/**
 * Created by huangwd on 16-12-8.
 */
public class BankIdUtil {
    public static boolean isBankId(String bankId){
        return bankId != null && Pattern.compile("^[0-9]{14,19}$").matcher(bankId).find();
    }
}
