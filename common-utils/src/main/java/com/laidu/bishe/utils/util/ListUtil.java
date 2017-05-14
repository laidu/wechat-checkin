package com.laidu.bishe.utils.util;

import java.util.Collection;

/**
 * Created by chenwen on 16/8/26.
 */
public class ListUtil {

    public static boolean isEmpty(Collection list){
        return list == null || list.size() == 0;
    }

    public static boolean isNotEmpty(Collection list){
        return !isEmpty(list);
    }
}
