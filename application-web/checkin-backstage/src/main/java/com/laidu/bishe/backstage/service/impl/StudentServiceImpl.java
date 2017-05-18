package com.laidu.bishe.backstage.service.impl;

import com.laidu.bishe.backstage.domain.StudentInfo;
import com.laidu.bishe.backstage.mapper.StudentInfoMapper;
import com.laidu.bishe.backstage.mapper.WechatUserInfoMapper;
import com.laidu.bishe.backstage.model.ResultMessage;
import com.laidu.bishe.backstage.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;

/**
 * Created by laidu on 2017/5/17.
 */
@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Lazy
    @Autowired(required = false)
    private StudentInfoMapper studentInfoMapper;

    @Lazy
    @Autowired(required = false)
    private WechatUserInfoMapper userInfoMapper;

    @Override
    public ResultMessage stuCheckInByVoice(String stuWechatID, BufferedInputStream inputStream) {

        return null;
    }

    @Override
    public ResultMessage stuCheckInByFace(String stuWechatID, BufferedInputStream inputStream) {
        return null;
    }

    @Override
    public boolean stuLeave(String stuWechatID, BufferedInputStream inputStream) {
        return false;
    }

    @Override
    public boolean bindingWechatId(Long stuId, String wechatId) {

        StudentInfo studentInfo = studentInfoMapper.selectByPrimaryKey(stuId);

        if (studentInfo==null){
            log.error("该学生信息不存在！stuId={}",stuId);
            return false;
        }

        studentInfo.setWechatId(wechatId);

        try {
            // TODO: 2017/5/17 添加微信号是否在已关注表中判断
            studentInfoMapper.updateByPrimaryKeySelective(studentInfo);
        }catch (Exception e){
            log.error("更新绑定学生微信号失败! {}",studentInfo);
            return false;
        }

        return true;
    }

    @Override
    public boolean alterWechatId(Long stuId, String newWechatId) {

        StudentInfo studentInfo = studentInfoMapper.selectByPrimaryKey(stuId);

        if (studentInfo.getWechatId()==null){
            log.info("该学生尚未绑定微信号,请先绑定微信号{}",studentInfo);
            return false;
        }

        return bindingWechatId(stuId,newWechatId);
    }
}
