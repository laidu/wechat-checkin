package com.laidu.bishe.backstage.service.impl;

import com.laidu.bishe.backstage.config.RedisPropertis;
import com.laidu.bishe.backstage.domain.SeqInfo;
import com.laidu.bishe.backstage.domain.TeacherInfo;
import com.laidu.bishe.backstage.exception.ExceptionCode;
import com.laidu.bishe.backstage.mapper.TeacherInfoMapper;
import com.laidu.bishe.backstage.mapper.custom.TeacherInfoCustMapper;
import com.laidu.bishe.backstage.model.CheckinQueueInfo;
import com.laidu.bishe.backstage.model.ResultMessage;
import com.laidu.bishe.backstage.service.AdminService;
import com.laidu.bishe.backstage.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by laidu on 2017/5/17.
 */
@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisPropertis redisPropertis;

    @Lazy
    @Autowired(required = false)
    private TeacherInfoCustMapper teacherInfoCustMapper;

    @Autowired
    private AdminService adminService;

    @Override
    public ResultMessage startCheckin(String teacherWechatID) {

        RMap<Long,CheckinQueueInfo> rMap = redissonClient
                .getMap(redisPropertis.getCkeckinQueueKey());

        //如果考勤队列不存在，则需要设置对列超时时间
        if (!rMap.isExists()){
            rMap.expire(redisPropertis.getTimeWindows(), TimeUnit.MINUTES);
        }

        //判断教师是否已经在考勤队列
        if (rMap.containsKey(teacherWechatID)){
            return ResultMessage.builder()
                    .code(ExceptionCode.CHECKIN_IS_STARTED.getCode())
                    .message(ExceptionCode.CHECKIN_IS_STARTED.getMessage())
                    .build();
        }

        TeacherInfo teacherInfo = teacherInfoCustMapper.selectByWechatId(teacherWechatID);

        //获取考勤次序信息
        SeqInfo seqInfo = adminService.requestSeqInfo(teacherInfo.getTeacherId());

        if (seqInfo==null){
            return ResultMessage.builder()
                    .code(ExceptionCode.CHECKIN_START_FAILD.getCode())
                    .message(ExceptionCode.CHECKIN_START_FAILD.getMessage())
                    .build();
        }

        CheckinQueueInfo queueInfo = CheckinQueueInfo.builder()
                .startTime(new Date())
                .wechatId(teacherWechatID)
                .build();
        //放入考勤队列
        rMap.put(teacherInfo.getTeacherId(),queueInfo);

        return ResultMessage.builder()
                .code(ExceptionCode.CHECKIN_START_SUCCEED.getCode())
                .message(ExceptionCode.CHECKIN_START_SUCCEED.getMessage())
                .build();
    }

    @Override
    public List<String> randomStudent(String teacherWechatID, int num) {
        return null;
    }

    @Override
    public List<String> randomCheckIn(String teacherWechatID, int centage) {
        return null;
    }


    @Override
    public ResultMessage stopCheckin(String teacherWechatID) {
        return null;
    }

    @Override
    public void calcSum(Long teacherID, String courseID, int seqNo) {

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
    public boolean bindingWechatId(Long teacherId, String wechatId) {

        TeacherInfo teacherInfo = getMyInfo(teacherId);

        if (teacherId == null) {
            return false;
        }
        teacherInfo.setWechatId(wechatId);
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
    public TeacherInfo getMyInfo(Long teacherId) {

        TeacherInfo teacherInfo = teacherInfoCustMapper.selectByPrimaryKey(teacherId);

        return teacherInfo;
    }
}
