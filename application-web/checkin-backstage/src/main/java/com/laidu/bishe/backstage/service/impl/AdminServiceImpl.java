package com.laidu.bishe.backstage.service.impl;

import com.laidu.bishe.backstage.config.SessionProperties;
import com.laidu.bishe.backstage.domain.CourseInfo;
import com.laidu.bishe.backstage.domain.SeqInfo;
import com.laidu.bishe.backstage.domain.StudentInfo;
import com.laidu.bishe.backstage.domain.TeacherInfo;
import com.laidu.bishe.backstage.mapper.SeqInfoMapper;
import com.laidu.bishe.backstage.mapper.StudentInfoMapper;
import com.laidu.bishe.backstage.mapper.TeacherInfoMapper;
import com.laidu.bishe.backstage.mapper.custom.CourseInfoCustMapper;
import com.laidu.bishe.backstage.model.ResultMessage;
import com.laidu.bishe.backstage.model.SessionInfo;
import com.laidu.bishe.backstage.service.AdminService;
import com.laidu.bishe.backstage.service.SessionService;
import com.laidu.bishe.utils.utils.CsvUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 管理员服务接口实现
 * Created by laidu on 2017/5/16.
 */
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Lazy
    @Autowired(required = false)
    private StudentInfoMapper studentInfoMapper;

    @Lazy
    @Autowired(required = false)
    private TeacherInfoMapper teacherInfoMapper;

    @Lazy
    @Autowired(required = false)
    private CourseInfoCustMapper courseInfoCustMapper;

    @Lazy
    @Autowired(required = false)
    private SeqInfoMapper seqInfoMapper;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionProperties sessionProperties;






    @Override
    public ResultMessage importTeacherInfoByCsv(String fileName) {

        List<TeacherInfo> teacherInfos = CsvUtil.readCsv2ObjestsList(TeacherInfo.class, fileName);
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

        List<StudentInfo> studentInfos = CsvUtil.readCsv2ObjestsList(StudentInfo.class, fileName);

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

        List<CourseInfo> courseInfos = CsvUtil.readCsv2ObjestsList(CourseInfo.class, fileName);

        courseInfos.forEach(courseInfo -> {
            courseInfoCustMapper.insert(courseInfo);
        });
        return null;
    }

    @Override
    public ResultMessage importCourseInfoByExcel(String fileName) {
        return null;
    }

    @Override
    public SeqInfo requestSeqInfo(Long teacherId) {

        /**
         * step 1、通过微信id和当前时间获取当前需要签到的课程信息和班级信息
         */
        CourseInfo courseInfo = null;

        //获取当前时间节次信息
        SessionInfo sessionId = sessionService.getSessionInfo(new Date(),sessionProperties.getBeforeMin());

        courseInfo = courseInfoCustMapper.selectByTeaSesId(teacherId,sessionId.getSessionId().getName(),sessionId.getDay().getName());

        /**
         * step 2、获取课程信息后新建一条考勤次序记录并返回考勤次序号
         */
        SeqInfo seqInfo = new SeqInfo();

        seqInfo.setTeacherId(teacherId);
        seqInfo.setStartTime(new Date());
        seqInfo.setCourseId(courseInfo.getCourseId());

        seqInfoMapper.insert(seqInfo);

        return null;
    }
}
