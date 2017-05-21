package com.laidu.bishe.backstage.service;

import com.laidu.bishe.backstage.model.ResultMessage;

import java.io.BufferedInputStream;

/**
 * 学生服务接口设计
 * Created by laidu on 2017/5/15.
 */
public interface StudentService {

    /**
     * 学生自助考勤-声纹考勤
     * @param stuWechatID
     * @param inputStream
     * @return
     */
    ResultMessage stuCheckInByVoice(String stuWechatID , BufferedInputStream inputStream);

    /**
     * 学生自助考勤-人脸考勤
     * @param stuWechatID
     * @param inputStream
     * @return
     */
    ResultMessage stuCheckInByFace(String stuWechatID , BufferedInputStream inputStream);

    /**
     * 学生请假
     * @param stuWechatID
     * @param inputStream
     * @return
     */
    boolean stuLeave(String stuWechatID , BufferedInputStream  inputStream);

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

}
