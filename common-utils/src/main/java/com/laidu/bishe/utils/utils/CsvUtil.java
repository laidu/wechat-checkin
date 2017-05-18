package com.laidu.bishe.utils.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * scv文件工具类
 * Created by laidu on 2017/5/16.
 */
@Slf4j
public class CsvUtil {

    /**
     * 从csv文件中获取对象列表
     * <p>
     * 利用json处理对象转换
     *
     * @param fileName
     * @return
     */
    public static <T> List<T> readCsv2ObjestsList(Class clazz, String fileName) {

        List<T> results = new ArrayList<>();

        Iterable<CSVRecord> records = null;
        try {
            records = CSVFormat.EXCEL
                    .withSkipHeaderRecord(true)
                    .withTrim(true)
                    .withAllowMissingColumnNames(true)
                    .withIgnoreEmptyLines(true)
                    .withFirstRecordAsHeader()
                    .parse(new FileReader(fileName));
        } catch (Exception e) {
            log.info("========{}文件打开失败========",fileName);
            return null;

        }

        records.forEach(record -> {
            JSONObject json = new JSONObject();

            for (Field field : clazz.getDeclaredFields()) {

                try {
                    json.put(field.getName(), record.get(field.getName()));
                } catch (Exception e) {
                    log.info("============获取{}字段是出错！==========", field.getName());
//                    break;
                }
            }
            try {
                results.add(JSON.parseObject(json.toJSONString(), (Class<T>) clazz));
            } catch (Exception e) {
                log.info("==========json类型转换异常{}=============", json);
            }
        });

        return results;
    }

    /**
     * 保存对象列表到Csv文件（支持追加）
     *
     * @param objects
     * @param fileName
     * @param <T>
     */
    public static <T> void writeObjests2Csv(List<T> objects, String fileName) {

        if (objects.size()>0){

            List<T> newObjects = new ArrayList<>();
            List<T> oldObjects = readCsv2ObjestsList(objects.get(0).getClass(),fileName);
            if (oldObjects!=null){
                newObjects.addAll(oldObjects);
            }
            newObjects.addAll(objects);

            List<String> resultHeaders = new ArrayList<>();

            for (Field field:objects.get(0).getClass().getDeclaredFields()){
                resultHeaders.add(field.getName());
            }

            CSVFormat format = CSVFormat.EXCEL.withHeader(resultHeaders.toArray(new String[]{})).withSkipHeaderRecord();
            try(Writer out = new FileWriter(fileName);
                CSVPrinter printer = new CSVPrinter(out, format)) {
                for (Object oj : newObjects) {
                    List<String> records = new ArrayList<>();

                    resultHeaders.forEach(header->{

                        try {
                            Field field = oj.getClass().getDeclaredField(header);
                            field.setAccessible(true);

                            Object fieldValue =  field.get(oj);
                            if (fieldValue!=null)
                                records.add(fieldValue.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    printer.printRecord(records);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }
}
