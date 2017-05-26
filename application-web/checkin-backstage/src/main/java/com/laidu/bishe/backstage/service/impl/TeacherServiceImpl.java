package com.laidu.bishe.backstage.service.impl;

import com.laidu.bishe.backstage.config.RedisPropertis;
import com.laidu.bishe.backstage.domain.*;
import com.laidu.bishe.backstage.enums.CheckInResultEnum;
import com.laidu.bishe.backstage.enums.CheckinResultCal;
import com.laidu.bishe.backstage.exception.BackstageException;
import com.laidu.bishe.backstage.mapper.CheckinCalculateResultMapper;
import com.laidu.bishe.backstage.mapper.WechatUserInfoMapper;
import com.laidu.bishe.backstage.mapper.custom.*;
import com.laidu.bishe.backstage.model.*;
import com.laidu.bishe.backstage.service.AdminService;
import com.laidu.bishe.backstage.service.TeacherService;
import com.laidu.bishe.backstage.utils.RandomUtil;
import com.laidu.bishe.common.web.exception.WebException;
import com.laidu.bishe.utils.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by laidu on 2017/5/17.
 */
@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {


    @Lazy
    @Autowired(required = false)
    private TeacherInfoCustMapper teacherInfoCustMapper;

    @Lazy
    @Autowired(required = false)
    private CourseInfoCustMapper courseInfoCustMapper;

    @Lazy
    @Autowired(required = false)
    private WechatUserInfoMapper wechatUserInfoMapper;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RedisPropertis redisPropertis;

    @Autowired(required = false)
    private StudentInfoCustMapper studentInfoCustMapper;

    @Autowired(required = false)
    private CheckinDetailCustInfoMapper checkinDetailCustInfoMapper;

    @Autowired(required = false)
    private CheckinCalculateResultCustMapper checkinCalculateResultCustMapper;


    @Override
    public ResultMessage startCheckin(String teacherWechatID) {

        RMap<Long, CheckinQueueInfo> rMap = adminService.getCheckinQueue();

        TeacherInfo teacherInfo = teacherInfoCustMapper.selectByWechatId(teacherWechatID);

        //判断教师是否已经在考勤队列
        if (rMap.containsKey(teacherInfo.getTeacherId())) {
            return ResultMessage.builder()
                    .code(BackstageException.CHECKIN_IS_STARTED.getCode())
                    .message(BackstageException.CHECKIN_IS_STARTED.getMessage() + rMap.get(teacherInfo.getTeacherId()))
                    .build();
        }


        //获取考勤次序信息
        SeqDetialInfo seqInfo = adminService.requestSeqInfo(teacherInfo.getTeacherId());

        if (seqInfo == null) {
            return ResultMessage.builder()
                    .code(BackstageException.CHECKIN_START_FAILD.getCode())
                    .message(BackstageException.CHECKIN_START_FAILD.getMessage())
                    .build();
        }

        CheckinQueueInfo queueInfo = CheckinQueueInfo.builder()
                .startTime(seqInfo.getSeqInfo().getStartTime())
                .courseName(seqInfo.getCourseName())
                .courseId(seqInfo.getSeqInfo().getCourseId())
                .classIds((JacksonUtil.jsonToObj(seqInfo.getSeqInfo().getClassIds(), List.class)))
                .seqId(seqInfo.getSeqInfo().getId())
                .teaWechatId(teacherWechatID)
                .build();
        //放入考勤队列
        rMap.expire(redisPropertis.getTimeWindows(), TimeUnit.MINUTES);
        rMap.put(teacherInfo.getTeacherId(), queueInfo);

        return ResultMessage.builder()
                .code(BackstageException.CHECKIN_START_SUCCEED.getCode())
                .message(BackstageException.CHECKIN_START_SUCCEED.getMessage() + queueInfo)
                .build();
    }

    @Override
    public List<StudentInfo> randomStudent(String teacherWechatID, int num) {

        /**
         * step 1、获取当前考勤信息并所有已签到学生列表
         */
        CheckinQueueInfo checkinQueueInfo = getCheckinQueueInfo(teacherWechatID);

        /**
         * step 2、从已签到学生列表中随机获取指定数量的学生
         */

        return null;
    }

    @Override
    public List<StudentInfo> randomCheckIn(String teacherWechatID, int centage) {

        TeacherInfo teacherInfo = teacherInfoCustMapper.selectByWechatId(teacherWechatID);

        RMap<Long, CheckinQueueInfo> checkinQueueInfoRMap = adminService.getCheckinQueue();

        //判断教师是否已经在考勤队列
        if (checkinQueueInfoRMap.isExists() && checkinQueueInfoRMap.containsKey(teacherInfo.getTeacherId())) {

            CheckinQueueInfo checkinQueueInfo = checkinQueueInfoRMap.get(teacherInfo.getTeacherId());

            List<StudentInfo> studentInfos = new ArrayList<>();

            checkinQueueInfo.getClassIds().forEach(classId -> {

                List<StudentInfo> studentInfosOneClass = studentInfoCustMapper.selectByClassId(classId);

                if (studentInfosOneClass != null) {
                    studentInfos.addAll(studentInfosOneClass);
                }
            });

            int size  = studentInfos.size()*100/centage;
            List<StudentInfo> studentInfosNews = RandomUtil.getRandomRes(studentInfos,size);


            return studentInfosNews;
        }

        return null;

    }


    @Override
    public ResultMessage stopCheckin(String teacherWechatID) {

        TeacherInfo teacherInfo = teacherInfoCustMapper.selectByWechatId(teacherWechatID);

        RMap<Long, CheckinQueueInfo> checkinQueueInfoRMap = adminService.getCheckinQueue();

        //判断教师是否已经在考勤队列
        if (checkinQueueInfoRMap.isExists() && checkinQueueInfoRMap.containsKey(teacherInfo.getTeacherId())) {

            CheckinQueueInfo checkinQueueInfo = checkinQueueInfoRMap.get(teacherInfo.getTeacherId());

            //统计本次考勤结果

            List<CheckinCalculateResult> checkinCalculateResults =
                    calcSum(teacherInfo.getTeacherId(), checkinQueueInfo.getCourseId(), checkinQueueInfo.getSeqId(),
                            checkinQueueInfo.getClassIds());

            if (checkinCalculateResults != null && checkinCalculateResults.size() != 0) {
                checkinCalculateResults.forEach(checkinCalculateResult -> {

                    checkinCalculateResultCustMapper.insertSelective(checkinCalculateResult);
                });
            }

            //删除当前考勤的学生列表
            checkinQueueInfo.getClassIds().forEach(classId -> {

                RMap<String, CheckinContentInfo> checkinContentInfoRMap = redissonClient.getMap(classId);
                checkinContentInfoRMap.delete();
            });

            //将该教师从考勤队列中删除
            CheckinQueueInfo queueInfo = checkinQueueInfoRMap.get(teacherInfo.getTeacherId());

            checkinQueueInfoRMap.remove(teacherInfo.getTeacherId());


            return ResultMessage.builder()
                    .code(BackstageException.CHECKIN_STOP_HAND.getCode())
                    .message(BackstageException.CHECKIN_STOP_HAND.getMessage() + queueInfo)
                    .build();
        }

        return ResultMessage.builder()
                .code(BackstageException.CHECKIN_NOT_EXIST.getCode())
                .message(BackstageException.CHECKIN_NOT_EXIST.getMessage())
                .build();


    }

    @Override
    public List<CheckinCalculateResult> calcSum(Long teacherID, Long courseID, Long seqNo, List<String> classIds) {


        List<CheckinCalculateResult> checkinCalculateResults = new ArrayList<>();

        /**
         * step1、获取所有班级学生（学号）信息
         */

        List<StudentInfo> studentInfos = new ArrayList<>();

        classIds.forEach(classId -> {

            List<StudentInfo> studentInfosOneClass = studentInfoCustMapper.selectByClassId(classId);

            if (studentInfosOneClass != null) {
                studentInfos.addAll(studentInfosOneClass);
            }
        });

        /**
         * step2、 遍历所有学号，在当前考勤次序号中获取每个学生的考勤枚举值之和，并根据枚举值之和判断学生考勤状态
         */

        studentInfos.forEach(studentInfo -> {

            CheckinResultCal checkInResult = CheckinResultCal.ABSENCE;
            List<CheckinDetailInfo> checkinDetailInfos =
                    checkinDetailCustInfoMapper.selectStudentByStudentIdAndSeqId(studentInfo.getStudentId(), courseID, seqNo);

            if (checkinDetailInfos != null && checkinDetailInfos.size() != 0) {

                checkInResult = CheckinResultCal.getEnum(CheckInResultEnum.getEnum(checkinDetailInfos.get(0).getCheckinResult()),
                        CheckInResultEnum.getEnum(checkinDetailInfos.get(0).getCheckinResult()));

                for (CheckinDetailInfo checkinDetailInfo : checkinDetailInfos) {

                    // TODO: 2017/5/25 校验规则
                    checkInResult = CheckinResultCal.getEnum(CheckInResultEnum.getEnum(checkInResult.getCode()),
                            CheckInResultEnum.getEnum(checkinDetailInfos.get(0).getCheckinResult()));
                }

            }

            CheckinCalculateResult checkinCalculateResult = new CheckinCalculateResult();
            checkinCalculateResult.setCheckinResult(checkInResult.getCode());
            checkinCalculateResult.setCourceId(courseID);
            checkinCalculateResult.setSeqId(seqNo);
            checkinCalculateResult.setStudentId(studentInfo.getStudentId());
            checkinCalculateResult.setTeacherId(teacherID);

            checkinCalculateResults.add(checkinCalculateResult);

        });

        return checkinCalculateResults;
    }

    @Override
    public void manualAlterCheckIn(String teacherWechatID) {

    }

    @Override
    public void manualAddCheckIn(String teacherWechatID) {

    }

    @Override
    public void manualLeaveCheck(String teacherWechatID) {

    }

    @Override
    public void gatherCheckIn(String teacherWechatID) {

    }

    @Override
    public ResultMessage viewCurrentCheckIn(String teacherWechatID) {


        TeacherInfo teacherInfo = teacherInfoCustMapper.selectByWechatId(teacherWechatID);

        RMap<Long, CheckinQueueInfo> checkinQueueInfoRMap = adminService.getCheckinQueue();

        //判断教师是否已经在考勤队列
        if (checkinQueueInfoRMap.isExists() && checkinQueueInfoRMap.containsKey(teacherInfo.getTeacherId())) {

            CheckinQueueInfo checkinQueueInfo = checkinQueueInfoRMap.get(teacherInfo.getTeacherId());

            //统计本次考勤结果
            List<CheckinCalculateResult> checkinCalculateResults =
                    calcSum(teacherInfo.getTeacherId(), checkinQueueInfo.getCourseId(), checkinQueueInfo.getSeqId(),
                            checkinQueueInfo.getClassIds());

            String result = adminService.parseCheckinResult(checkinCalculateResults);

            return ResultMessage.builder()
                    .code(BackstageException.OK.getCode())
                    .message(result)
                    .build();
        }

        return ResultMessage.builder()
                .code(BackstageException.CHECKIN_NOT_EXIST.getCode())
                .message(BackstageException.CHECKIN_NOT_EXIST.getMessage())
                .build();

    }

    @Override
    public boolean bindingWechatId(Long teacherId, String wechatId) {


        TeacherInfo teacherInfo = teacherInfoCustMapper.selectByPrimaryKey(teacherId);

        // TODO: 2017/5/22 如果已有教师注册则不允许注册
        if (teacherInfo == null) {
            throw new WebException(BackstageException.TEACHER_INFO_NOT_EXIST);
        }

        teacherInfo.setWechatId(wechatId);
        teacherInfo.setRegisterTime(new Date());
        try {
            teacherInfoCustMapper.updateByPrimaryKeySelective(teacherInfo);
        } catch (Exception e) {
            log.info("绑定微信号失败！teacher = {}", teacherInfo);
            return false;
        }

        return true;
    }

    @Override
    public boolean alterWechatId(Long teacherId, String oldWechatId, String newWechatId) {
        return false;
    }

    @Override
    @Cacheable(value = "get_my_tea_info_ccache",keyGenerator = "wiselyKeyGenerator")
    public TeacherDetialInfo getMyInfo(String teacherWechatID) {


        TeacherInfo teacherInfo = teacherInfoCustMapper.selectByWechatId(teacherWechatID);

        return TeacherDetialInfo.builder()
                .registerTime(teacherInfo.getRegisterTime())
                .teacherId(teacherInfo.getTeacherId())
                .teacherName(teacherInfo.getTeacherName())
                .build();
    }

    @Override
    public String getMyCourseInfo(String teacherWechatID) {

        TeacherInfo teacherInfo = teacherInfoCustMapper.selectByWechatId(teacherWechatID);

        List<CourseInfo> courseInfos = courseInfoCustMapper.selectByTeaId(teacherInfo.getTeacherId());

        StringBuffer courses = new StringBuffer("授课信息如下 ：\n\n");

        for (CourseInfo courseInfo : courseInfos) {

            courses.append(String.format(" %s\t%s\t%s\t\n", courseInfo.getCourseName(), courseInfo.getWeekDay(), courseInfo.getSessionId()));
        }

        return courses.toString();
    }

    public CheckinQueueInfo getCheckinQueueInfo(String teacherWechatID) {

        CheckinQueueInfo checkinQueueInfo = null;

        RMap<Long, CheckinQueueInfo> rMap = adminService.getCheckinQueue();

        TeacherInfo teacherInfo = teacherInfoCustMapper.selectByWechatId(teacherWechatID);

        //判断教师是否已经在考勤队列
        if (rMap.containsKey(teacherInfo.getTeacherId())) {

            // TODO: 2017/5/22 通过全局异常给用户发送消息
            throw new WebException(BackstageException.CHECKIN_NOT_EXIST);

        }

        return checkinQueueInfo;

    }


}
