package com.laidu.bishe.utils.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hp on 15-11-16.
 */
public class Md5Util {
    /**
     *
     * @param str
     *            明文
     * @return 32位密文
     */
    public static String toMd5(String str) {
        String re_md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }
}
