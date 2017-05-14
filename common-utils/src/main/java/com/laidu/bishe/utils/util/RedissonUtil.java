package com.laidu.bishe.utils.util;

import com.alibaba.fastjson.JSONObject;
import com.laidu.bishe.utils.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;

import java.io.IOException;

/**
 * Created by chenwen on 16/5/23.
 */
@Slf4j
public class RedissonUtil {
    private static RedissonUtil instance;

    private RedissonClient redisson;

    public RedissonUtil() {
        init();
    }

    public static RedissonUtil getInstance() {
        if (instance == null) {
            synchronized (RedissonUtil.class) {
                if (instance == null) {
                    instance = new RedissonUtil();
                }
            }
        }
        return instance;
    }

    private void init() {
        String jsonConfig = FileUtil.getFromFile("/conf/redission.json");
        try {
            org.redisson.config.Config config = org.redisson.config.Config.fromJSON(JSONObject.parseObject(jsonConfig).getJSONObject(Config.getActiveConfig()).toJSONString());
            redisson = Redisson.create(config);
        } catch (IOException e) {
            log.error("error to read config for redission", e);
        }
    }

    public RedissonClient getRedisson() {
        return instance.redisson;
    }

    //初始化proxyQueue
    public void initBlockingQueue() {
        RedissonClient redisson = RedissonUtil.getInstance().getRedisson();
        RBlockingQueue<Object> queue = redisson.getBlockingQueue(Config.getString("proxyQueue"));
        if (queue == null || queue.size() == 0) {
            boolean b = queue.offer("11.11.11.11:8080");
            log.info("queue.size" + b);
            log.info("queue.size" + queue.size());
            log.info("");
        }
    }
}


