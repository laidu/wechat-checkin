package com.laidu.bishe.backstage.service.impl;

import com.laidu.bishe.backstage.domain.TeacherInfo;
import com.laidu.bishe.backstage.mapper.TeacherInfoMapper;
import com.laidu.bishe.backstage.model.ResultMessage;
import com.laidu.bishe.backstage.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.util.List;

/**
 * Created by laidu on 2017/5/17.
 */
@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    @Lazy
    @Autowired(required = false)
    private TeacherInfoMapper teacherInfoMapper;

    @Override
    public ResultMessage startCheckin(String teacherWechatID) {
        return null;
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
    public boolean stopCheckin(String teacherWechatID) {
        return false;
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
        teacherInfo.setWechatId(wechatId);

        try {
            teacherInfoMapper.updateByPrimaryKeySelective(teacherInfo);
        }catch (Exception e){
            log.info("绑定微信号失败！teacher = {}",teacherInfo);
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

        TeacherInfo teacherInfo = teacherInfoMapper.selectByPrimaryKey(teacherId);

        return teacherInfo;
    }
}
