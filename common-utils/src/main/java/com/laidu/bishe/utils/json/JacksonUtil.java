package com.laidu.bishe.utils.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * jackson 工具类
 * Created by laidu on 2017/5/7.
 */
@Slf4j
public class JacksonUtil {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        //使用驼峰转下划线
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }



}
