package com.laidu.bishe.backstage.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * redis keys 配置
 *
 * Created by laidu on 2017/5/19.
 */
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class RedisPropertis {

    /**
     * 考勤对列key
     */
    @Value("${redis.key.checkin_queue_key:checkin_queue}")
    private String ckeckinQueueKey;

    /**
     * 考勤时间窗口
     * 单位为min
     */
    @Value("${redis.time_windows:1}")
    private int timeWindows;


}
