package com.laidu.bishe.utils.json;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * fastjson工具类
 * Created by laidu on 2017/5/7.
 */
@Slf4j
public class FastjsonUtil {

    private static JSON json;

    static {

        try {
            json = JSON.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
