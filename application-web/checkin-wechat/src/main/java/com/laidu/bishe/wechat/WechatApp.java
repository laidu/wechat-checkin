package com.laidu.bishe.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Created by laidu on 2017/5/8.
 */
@EnableCaching
@SpringBootApplication
@MapperScan("com.laidu.bishe.backstage.mapper")
@ComponentScan(
        basePackages = {"com.laidu.bishe"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.laidu.bishe.[^(common.web | wechat | backstage)].*")}
)

public class WechatApp {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(WechatApp.class, args);
    }
}
