package com.laidu.bishe.utils.excel;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;


/**
 * 基于POI的excel工具类
 * Created by laidu on 2017/5/7.
 */
@Slf4j
public class ExcelUtil {

    /**
     * 读取excel为对象列表，每一行代表一个对象
     * @param clazz 对象类型
     * @param in excel文件流
     * @return
     */
    public static List<Object> readExcel2ObjectList(InputStream in,Class clazz){

        return null;
    }

    /**
     * 将对象列表写入excel
     * @param out
     * @return
     */
    public static boolean writeObjectList2Excel(List<Object> objects,OutputStream out){

        return true;
    }
}
