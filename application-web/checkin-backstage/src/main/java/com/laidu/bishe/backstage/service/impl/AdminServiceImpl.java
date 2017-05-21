package com.laidu.bishe.backstage.service.impl;

import com.laidu.bishe.backstage.config.SessionProperties;
import com.laidu.bishe.backstage.domain.*;
import com.laidu.bishe.backstage.enums.WeekEnum;
import com.laidu.bishe.backstage.mapper.SeqInfoMapper;
import com.laidu.bishe.backstage.mapper.SessionInfoMapper;
import com.laidu.bishe.backstage.mapper.StudentInfoMapper;
import com.laidu.bishe.backstage.mapper.TeacherInfoMapper;
import com.laidu.bishe.backstage.mapper.custom.CourseInfoCustMapper;
import com.laidu.bishe.backstage.mapper.custom.SessionInfoCustMapper;
import com.laidu.bishe.backstage.model.ResultMessage;
import com.laidu.bishe.backstage.service.AdminService;
import com.laidu.bishe.backstage.service.SessionService;
import com.laidu.bishe.utils.utils.CsvUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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

    @Lazy
    @Autowired(required = false)
    private SessionInfoCustMapper sessionInfoCustMapper;

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
            courseInfoCustMapper.insertSelective(courseInfo);
        });
        return null;
    }


    @Override
    public ResultMessage importSessionInfoByExcel(String fileName) {
        return null;
    }

    @Override
    public ResultMessage importCourseInfoByExcel(String fileName) {
        return null;
    }

    @Override
    public ResultMessage importSessionInfoByCsv(String fileName) {

        List<SessionInfo> sessionInfos = CsvUtil.readCsv2ObjestsList(SessionInfo.class,fileName);

        sessionInfos.forEach(sessionInfo -> {
            sessionInfoCustMapper.insertSelective(sessionInfo);
        });

        return null;
    }


    @Override
    public SeqInfo requestSeqInfo(Long teacherId) {

        /**
         * step 1、通过微信id和当前时间获取当前需要签到的课程信息和班级信息
         */
        CourseInfo courseInfo = null;

        //获取当前时间节次信息

        Calendar current = Calendar.getInstance();
//        int hour = current.get(Calendar.HOUR);
//        int min = current.get(Calendar.MINUTE);
        // TODO: 2017/5/22 测试用 
        int hour = 8;
        int min = 35;
        WeekEnum weekDay = WeekEnum.getEnum(current.get(Calendar.DAY_OF_WEEK));


        // TODO: 2017/5/22 如果找不到课程就抛出异常， 由全局的异常处理拦截
        SessionInfo sessionInfo = sessionInfoCustMapper.selectByTime(hour,min);

        courseInfo = courseInfoCustMapper.selectByTeaSesId(teacherId, sessionInfo.getSessionIndex(), weekDay.getName());

        /**
         * step 2、获取课程信息后新建一条考勤次序记录并返回考勤次序号
         */
        SeqInfo seqInfo = new SeqInfo();

        seqInfo.setTeacherId(teacherId);
        seqInfo.setStartTime(new Date());
        seqInfo.setCourseId(courseInfo.getCourseId());

        seqInfoMapper.insertSelective(seqInfo);

        return seqInfo;
    }
}
