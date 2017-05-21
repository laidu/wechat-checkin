package com.laidu.bishe.backstage.service.impl;

import com.laidu.bishe.backstage.MainApp;
import com.laidu.bishe.backstage.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by laidu on 2017/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes=MainApp.class)
@MapperScan("com.laidu.bishe.backstage.mapper")
public class AdminServiceImplTest {

    @Autowired(required = false)
    AdminService adminService;

    @Test
    public void importTeacherInfoByCsv() throws Exception {

//        adminService.importStudentInfoByCsv("/Users/laidu/IdeaProjects/wechat-checkin/application-web/checkin-backstage/src/test/resources/studentInfo.csv");
//        adminService.importTeacherInfoByCsv("/Users/laidu/IdeaProjects/wechat-checkin/application-web/checkin-backstage/src/test/resources/teacherInfo.csv");
//        adminService.importCourseInfoByCsv("/Users/laidu/IdeaProjects/wechat-checkin/application-web/checkin-backstage/src/test/resources/courseInfo.csv");
        adminService.importSessionInfoByCsv("/Users/laidu/IdeaProjects/wechat-checkin/application-web/checkin-backstage/src/test/resources/sessionInfo.csv");
    }

}