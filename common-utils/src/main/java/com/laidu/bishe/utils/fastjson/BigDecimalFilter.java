package com.laidu.bishe.utils.fastjson;

import com.alibaba.fastjson.serializer.ValueFilter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by huangpin on 17/4/1.
 */
@Slf4j
public class BigDecimalFilter implements ValueFilter {
    @Override
    public Object process(Object o, String s, Object o1) {

        if (null != o1 && o1 instanceof BigDecimal) {
            try {
                return new BigDecimal(o1.toString()).setScale(2, RoundingMode.HALF_UP).toString();
            } catch (Exception e) {
                log.error("BigDecimal字段解析错误", e);
            }
        }
        return o1;
    }
}
