package com.laidu.bishe.utils.fastjson;

import com.alibaba.fastjson.serializer.NameFilter;
import com.laidu.bishe.utils.utils.CommonUtil;

/**
 * Created by chenwen on 17/1/5.
 */
public class FastJsonUpperCaseTo_ implements NameFilter {
    @Override
    public String process(Object o, String s, Object o1) {
        return CommonUtil.to_(s);
    }
}
