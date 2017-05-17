package com.laidu.bishe.utils.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by chenwen on 16/5/16.
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection list){
        return !isNotEmpty(list);
    }

    public static boolean bothContains(Collection list1,Collection list2){
        if (ObjectUtil.isNull(list1, list2)) return true;
        if (ObjectUtil.isNotNull(list1,list2)) {
            return list1.containsAll(list2) && list2.containsAll(list1);
        }
        return false;
    }

    public static boolean equals(Collection list1,Collection list2){
        if (ObjectUtil.isNull(list1, list2)) return true;
        if (ObjectUtil.isNotNull(list1,list2)) {
            if (list1.size() != list2.size()) return false;
            return list1.containsAll(list2) && list2.containsAll(list1);
        }
        return false;
    }

    public static boolean isEmpty(Map map){
        return !isNotEmpty(map);
    }

    public static boolean isNotEmpty(Map map){
        return map != null && !map.isEmpty();
    }

    public static boolean isNotEmpty(Collection list){
        return list != null && !list.isEmpty();
    }


    public static void main(String[] args) {
        System.out.println(bothContains(new ArrayList<String>(){{
            add("111");
            add("222");
            add("222");
            add("222");
        }}, new ArrayList<String>(){{
            add("111");
            add("111");
            add("111");
            add("111");
            add("222");
        }}));
        System.out.println(bothContains(new ArrayList<String>(){{
            add("111");
        }}, new ArrayList<String>(){{
            add("111");
            add("222");
        }}));
    }
}
