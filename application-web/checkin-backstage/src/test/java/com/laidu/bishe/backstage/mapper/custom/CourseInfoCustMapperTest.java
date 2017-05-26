package com.laidu.bishe.backstage.mapper.custom;

import com.laidu.bishe.backstage.MainApp;
import com.laidu.bishe.backstage.domain.CourseInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by laidu on 2017/5/22.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(MainApp.class)
public class CourseInfoCustMapperTest {

    @Autowired(required = false)
    private CourseInfoCustMapper courseInfoCustMapper;

    @Test
    public void selectByTeaSesId() throws Exception {

        List<CourseInfo> courseInfos = courseInfoCustMapper.selectByTeaSesId(2004633L,"第一节","星期一");

        log.info("{}",courseInfos);
    }

}