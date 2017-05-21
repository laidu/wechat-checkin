package com.laidu.bishe.backstage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by laidu on 2017/5/17.
 */
@EnableCaching
@SpringBootApplication
@MapperScan({"com.laidu.bishe.backstage.mapper","com.laidu.bishe.backstage.mapper.custom"})
@ComponentScan(
        basePackages = {"com.laidu.bishe"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.laidu.bishe.[^(common.web | wechat | backstage)].*")}
)
public class MainApp {
    public static void main(String[] args) throws Exception {

        SpringApplication.run(MainApp.class, args);
    }
}
