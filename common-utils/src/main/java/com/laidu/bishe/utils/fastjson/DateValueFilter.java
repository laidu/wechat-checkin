package com.laidu.bishe.utils.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.laidu.bishe.utils.utils.CommonUtil;
import com.laidu.bishe.utils.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * Created by chenwen on 17/3/9.
 */
@Slf4j
public class DateValueFilter implements ValueFilter {
    @Override
    public Object process(Object o, String s, Object o1) {
        if (o1 != null && o1 instanceof Date){
            try {
                JSONField jsonField = o.getClass().getDeclaredField(CommonUtil.toUpper(s)).getAnnotation(JSONField.class);
                if (jsonField != null && StringUtils.isNotEmpty(jsonField.format())) {
                    return DateUtil.getDate(jsonField.format(), (Date) o1);
                }else {
                    return DateUtil.getDate(DateUtil.DEFAULT_FORMAT, (Date) o1);
                }
            } catch (Exception e) {
                log.error("时间字段错误解析" ,e);
            }
        }
        return o1;
    }

    public static void main(String[] args) {
        System.out.println(CommonUtil.toUpper("create_time"));
    }
}
