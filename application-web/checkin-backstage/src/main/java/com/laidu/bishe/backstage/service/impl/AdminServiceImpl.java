package com.laidu.bishe.backstage.service.impl;

import com.laidu.bishe.backstage.domain.CourseInfo;
import com.laidu.bishe.backstage.domain.StudentInfo;
import com.laidu.bishe.backstage.domain.TeacherInfo;
import com.laidu.bishe.backstage.mapper.CourseInfoMapper;
import com.laidu.bishe.backstage.mapper.StudentInfoMapper;
import com.laidu.bishe.backstage.mapper.TeacherInfoMapper;
import com.laidu.bishe.backstage.model.ResultMessage;
import com.laidu.bishe.backstage.service.AdminService;
import com.laidu.bishe.utils.utils.CsvUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员服务接口实现
 * Created by laidu on 2017/5/16.
 */
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Lazy
    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Lazy
    @Autowired
    private TeacherInfoMapper teacherInfoMapper;

    @Lazy
    @Autowired
    private CourseInfoMapper courseInfoMapper;


    @Override
    public ResultMessage importTeacherInfoByCsv(String fileName) {


        List<TeacherInfo> teacherInfos =  CsvUtil.readCsv2ObjestsList(TeacherInfo.class,fileName);

        teacherInfos.forEach(teacherInfo -> {

            teacherInfoMapper.insert(teacherInfo);
        });

        return null;
    }

    @Override
    public ResultMessage importTeacherInfoByExcel(String fileName) {
        return null;
    }

    @Override
    public ResultMessage importStudentInfoByCsv(String fileName) {

        List<StudentInfo> studentInfos =  CsvUtil.readCsv2ObjestsList(StudentInfo.class,fileName);

        studentInfos.forEach(studentInfo -> {

            studentInfoMapper.insert(studentInfo);
        });

        return null;
    }

    @Override
    public ResultMessage importStudentInfoByExcel(String fileName) {
        return null;
    }

    @Override
    public ResultMessage importCourseInfoByCsv(String fileName) {

        List<CourseInfo> courseInfos = CsvUtil.readCsv2ObjestsList(CourseInfo.class,fileName);

        courseInfos.forEach(courseInfo -> {
            courseInfoMapper.insert(courseInfo);
        });
        return null;
    }

    @Override
    public ResultMessage importCourseInfoByExcel(String fileName) {
        return null;
    }
}
