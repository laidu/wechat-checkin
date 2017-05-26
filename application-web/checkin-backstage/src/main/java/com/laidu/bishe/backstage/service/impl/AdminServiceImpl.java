package com.laidu.bishe.backstage.service.impl;

import com.laidu.bishe.backstage.config.RedisPropertis;
import com.laidu.bishe.backstage.config.SessionProperties;
import com.laidu.bishe.backstage.domain.*;
import com.laidu.bishe.backstage.enums.CheckInResultEnum;
import com.laidu.bishe.backstage.enums.WeekEnum;
import com.laidu.bishe.backstage.exception.BackstageException;
import com.laidu.bishe.backstage.mapper.SeqInfoMapper;
import com.laidu.bishe.backstage.mapper.StudentInfoMapper;
import com.laidu.bishe.backstage.mapper.TeacherInfoMapper;
import com.laidu.bishe.backstage.mapper.custom.CourseInfoCustMapper;
import com.laidu.bishe.backstage.mapper.custom.SeqInfoCustMapper;
import com.laidu.bishe.backstage.mapper.custom.SessionInfoCustMapper;
import com.laidu.bishe.backstage.model.CheckinQueueInfo;
import com.laidu.bishe.backstage.model.ResultMessage;
import com.laidu.bishe.backstage.model.SeqDetialInfo;
import com.laidu.bishe.backstage.service.AdminService;
import com.laidu.bishe.backstage.service.SessionService;
import com.laidu.bishe.common.web.exception.WebException;
import com.laidu.bishe.utils.utils.CsvUtil;
import com.laidu.bishe.utils.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 管理员服务接口实现
 * Created by laidu on 2017/5/16.
 */
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisPropertis redisPropertis;


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
    private SeqInfoCustMapper seqInfoCustMapper;

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
    public SeqDetialInfo requestSeqInfo(Long teacherId) {

        /**
         * step 1、通过微信id和当前时间获取当前需要签到的课程信息和班级信息
         */
        List<CourseInfo> courseInfos = null;

        //获取当前时间节次信息

        Calendar current = Calendar.getInstance();
//        int hour = current.get(Calendar.HOUR);
//        int min = current.get(Calendar.MINUTE);
        // TODO: 2017/5/22 测试用 
        int hour = 8;
        int min = 35;
        WeekEnum weekDay = WeekEnum.getEnum(current.get(Calendar.DAY_OF_WEEK));


        //如果找不到课程就抛出异常， 由全局的异常处理拦截
        SessionInfo sessionInfo = sessionInfoCustMapper.selectByTime(hour,min);
        if (sessionInfo==null){
            throw new WebException(BackstageException.CURRENT_IS_NOT_ALLOWED_CHECKIN);
        }

        courseInfos = courseInfoCustMapper.selectByTeaSesId(teacherId, sessionInfo.getSessionIndex(), weekDay.getName());

        List<String> classIds = new ArrayList<>();

        courseInfos.forEach(courseInfo -> {
            classIds.add(courseInfo.getClassName());
        });


        if (courseInfos.size()==0){
            return null;
        }

        /**
         * step 2、获取课程信息后新建一条考勤次序记录并返回考勤次序号
         */
        SeqInfo seqInfo = new SeqInfo();

        seqInfo.setTeacherId(teacherId);
        seqInfo.setStartTime(new Date());
        seqInfo.setCourseId(courseInfos.get(0).getCourseId());
        seqInfo.setClassIds(JacksonUtil.listToJson(classIds));

        seqInfoCustMapper.insertReKey(seqInfo);

        return SeqDetialInfo.builder().seqInfo(seqInfo).courseName(courseInfos.get(0).getCourseName()).build();

    }

    /**
     * 获取当前考勤队列
     * @return
     */
    @Override
    public RMap<Long,CheckinQueueInfo> getCheckinQueue(){


        RMap<Long, CheckinQueueInfo> rMap = redissonClient
                .getMap(redisPropertis.getCkeckinQueueKey());

        //如果考勤队列不存在，则需要设置对列超时时间
        if (!rMap.isExists()) {
            rMap.expire(redisPropertis.getTimeWindows(), TimeUnit.MINUTES);
        }


        return rMap;

    }

    /**
     * 解析考勤结果
     * @param checkinCalculateResults
     * @return
     */
    @Override
    public String parseCheckinResult(List<CheckinCalculateResult> checkinCalculateResults) {
        String format = "考勤统计信息：\n\n " +
                "总数：%d \n " +
                "正常：%d \n " +
                "缺勤：%d \n " +
                "请假：%d \n " +
                "迟到：%d \n " +
                "早退：%d";

        int sum = 0, normal = 0, absence = 0, leave = 0, tardy = 0, late = 0;

        if (checkinCalculateResults != null && checkinCalculateResults.size() != 0) {

            for (CheckinCalculateResult checkinCalculateResult : checkinCalculateResults) {

                CheckInResultEnum checkInResultEnum = null;

                try {
                    checkInResultEnum = CheckInResultEnum.getEnum(checkinCalculateResult.getCheckinResult());
                } catch (Exception e) {
                    log.info("获取考勤状态异常");
                }
                if (checkInResultEnum != null) {

                    switch (checkInResultEnum) {
                        case NORMAL:
                            normal++;
                            break;
                        case LATE:
                            late++;
                            break;
                        case TARDY:
                            tardy++;
                            break;
                        case LEAVE:
                            leave++;
                            break;
                        case ABSENCE:
                            absence++;
                            break;
                    }
                }
            }

        }

        return String.format(format, checkinCalculateResults.size(), normal, absence, leave, late, tardy);
    }
}
