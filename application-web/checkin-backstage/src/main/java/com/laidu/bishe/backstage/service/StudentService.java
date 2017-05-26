package com.laidu.bishe.backstage.service;

import com.laidu.bishe.backstage.domain.StudentInfo;
import com.laidu.bishe.backstage.enums.CheckInResultEnum;
import com.laidu.bishe.backstage.model.CheckinContentInfo;
import com.laidu.bishe.backstage.model.ResultMessage;
import com.laidu.bishe.backstage.model.StudentDetialInfo;

import java.io.BufferedInputStream;
import java.io.File;
import java.util.List;

/**
 * 学生服务接口设计
 * Created by laidu on 2017/5/15.
 */
public interface StudentService {


    CheckinContentInfo requestCheckin(String wechatId);

    /**
     * 学生自助考勤-声纹考勤
     * @param stuWechatID
     * @return
     */
    ResultMessage stuCheckInByVoice(String stuWechatID , File voiceFile);

    /**
     * 学生自助考勤-人脸考勤
     * @param stuWechatID
     * @return
     */
    ResultMessage stuCheckInByFace(String stuWechatID , File pictureFile);

    /**
     * 学生自助请假
     * @param stuWechatID
     * @return
     */
    ResultMessage stuLeave(String stuWechatID , File pictureFile);

    /**
     * 绑定人脸图片信息md5
     * @param wechatId
     * @param picMd5Sum
     * @return
     */
    boolean bingPicMd5Sum(String wechatId, String picMd5Sum);

    /**
     * 学生请假
     * @param stuWechatID
     * @return
     */
    CheckinContentInfo stuRequestLeave(String stuWechatID);

    /**
     * 绑定微信号
     * @param wechatId
     * @return
     */
    boolean bindingWechatId(Long stuId,String wechatId);


    /**
     * 修改绑定的微信号
     * @param newWechatId
     * @return
     */
    boolean alterWechatId(Long stuId,String newWechatId);

    /**
     * 获取某次考勤的某个结果的学生列表
     * @param seqId
     * @param result
     * @return
     */
    List<StudentInfo> getStudentsBySeqId(Long seqId, CheckInResultEnum result);

    /**
     * 获取学生信息
     * @param wechatId
     * @return
     */
    StudentDetialInfo getMyInfo(String wechatId);

    /**
     * 获取学生的考勤信息
     * @param wechatId
     * @return
     */
    ResultMessage getMycheckinRec(String wechatId);

}
