package com.laidu.bishe.backstage.service.impl;

import com.laidu.bishe.backstage.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.util.List;

/**
 * Created by laidu on 2017/5/17.
 */
@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {



    @Override
    public void startCheckin(String teacherWechatID, String courseID) {

    }

    @Override
    public List<String> randomStudent(String teacherWechatID, int num) {
        return null;
    }

    @Override
    public void randomCheckIn(String studentWechatID, BufferedInputStream inputStream) {

    }

    @Override
    public boolean stopCheckin(String teacherWechatID) {
        return false;
    }

    @Override
    public void calcSum(String teacherID, String courseID, int seqNo) {

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
    public boolean bindingWechatId(String teacherId, String wechatId) {
        return false;
    }

    @Override
    public boolean alterWechatId(String teacherId, String oldWechatId, String newWechatId) {
        return false;
    }
}
