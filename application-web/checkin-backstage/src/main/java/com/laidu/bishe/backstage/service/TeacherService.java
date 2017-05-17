package com.laidu.bishe.backstage.service;

import java.io.BufferedInputStream;
import java.util.List;

/**
 * 教师服务接口设计
 * Created by laidu on 2017/5/15.
 */
public interface TeacherService {

    /**
     * 开始自助考勤
     * @param teacherWechatID
     * @param courseID
     */
    void startCheckin(String teacherWechatID,String courseID);

    /**
     * 随机抽取学生
     * @param teacherWechatID
     * @param num
     * @return
     */
    List<String> randomStudent(String teacherWechatID, int num);

    /**
     * 抽点考勤
     * @param studentWechatID
     * @param inputStream
     */
    void randomCheckIn(String studentWechatID, BufferedInputStream inputStream);

    /**
     * 结束考勤
     * @param teacherWechatID
     */
    boolean stopCheckin(String teacherWechatID);

    /**
     * 统计考勤结果
     * @param teacherID
     * @param courseID
     * @param seqNo
     */
    void calcSum(String teacherID,String courseID,int seqNo);

    /**
     * 手动修改考勤
     * @param teacherWechatID
     */
    void manualAlterCheckIn(String teacherWechatID);

    /**
     * 手动添加考勤
     * @param teacherWechatID
     */
    void manualAddCheckIn(String teacherWechatID);

    /**
     * 请假检查
     * @param teacherWechatID
     */
    void manualLeaveCheck(String teacherWechatID);

    /**
     * 生成详细的考勤记录
     * @param teacherWechatID
     */
    void gatherCheckIn(String teacherWechatID);

    /**
     * 绑定微信号
     * @param wechatId
     * @return
     */
    boolean bindingWechatId(String teacherId,String wechatId);


    /**
     * 修改绑定的微信号
     * @param oldWechatId
     * @param newWechatId
     * @return
     */
    boolean alterWechatId(String teacherId,String oldWechatId,String newWechatId);
}
