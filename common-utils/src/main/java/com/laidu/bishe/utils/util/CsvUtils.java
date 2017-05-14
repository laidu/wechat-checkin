package com.laidu.bishe.utils.util;

import com.laidu.bishe.utils.filter.RedisBloomDuplicateFilter;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by xueyunlong on 16-12-16.
 */
@Slf4j
public class CsvUtils {
    /*
    * 从csv文件中，读取单列数据并存入redis-set中，进行去重处理
    * */
    public static boolean csvToRedisSet(String filePath,String redistemp,String redismemory) {
        try {
            BufferedReader reader = null;
            RedissonClient redissonClient = RedissonUtil.getInstance().getRedisson();
            RSet nametemp = redissonClient.getSet(redistemp);
            RSet namememory = redissonClient.getSet(redismemory);
            if (isCsv(filePath)) {
                reader = new BufferedReader(new FileReader(filePath));
                reader.readLine();//第一行信息，为标题信息，不用，如果需要，注释掉
                String line = null;
                while ((line=reader.readLine())!= null) { //逐行读入除表头的数据
                    Boolean b = namememory.add(line) == true ? nametemp.add(line) : false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean csvToRedisBloom(String filePath,String redisBloom){
        BufferedReader reader = null;
        RedissonClient redissonClient = RedissonUtil.getInstance().getRedisson();
        RedisBloomDuplicateFilter namebloomfilter = new RedisBloomDuplicateFilter(50000000,redisBloom,redissonClient);
        try {

            if (isCsv(filePath)) {
                reader = new BufferedReader(new FileReader(filePath));
                String line = null;
                while ((line=reader.readLine())!= null) {
                    log.info("line is {}" , line);
                    namebloomfilter.isDuplicate(line);
                }
                return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean csvToRedisBloom(String filePath,String redistemp,String redisBloom){
        BufferedReader reader = null;
        RedissonClient redissonClient = RedissonUtil.getInstance().getRedisson();
        RSet nametemp = redissonClient.getSet(redistemp);
        RedisBloomDuplicateFilter namebloomfilter = new RedisBloomDuplicateFilter(50000000,redisBloom,redissonClient);
        try {

            if (isCsv(filePath)) {
                reader = new BufferedReader(new FileReader(filePath));
            String line = null;
            int i = 0;
            while ((line=reader.readLine())!= null) {
                log.info("line is {}", line);
                boolean f = namebloomfilter.isDuplicate(line) == false ? nametemp.add(line) : false;
//                nametemp.add(line);
            }
            return true;
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    //判断是否是csv文件
    private static boolean isCsv(String fileName) {
        return fileName.matches("^.+\\.(?i)(csv)$");
    }

}
