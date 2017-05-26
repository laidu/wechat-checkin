package com.laidu.bishe.backstage.service;

import com.laidu.bishe.backstage.domain.CheckinCalculateResult;
import com.laidu.bishe.backstage.domain.StudentInfo;
import com.laidu.bishe.backstage.domain.TeacherInfo;
import com.laidu.bishe.backstage.model.ResultMessage;
import com.laidu.bishe.backstage.model.TeacherDetialInfo;

import java.io.BufferedInputStream;
import java.util.List;

/**
 * 教师服务接口设计
 * Created by laidu on 2017/5/15.
 */
public interface TeacherService {

    /**
     * 开始自助考勤
     * 不需要传入课程号，根据课表和当前时间查找考勤的课程
     * @param teacherWechatID
     */
    ResultMessage startCheckin(String teacherWechatID);

    /**
     * 随机抽取学生（在已完成考勤的学生中）
     * @param teacherWechatID
     * @return
     */
    List<StudentInfo> randomStudent(String teacherWechatID, int centage);

    /**
     * 抽点考勤
     * @param teacherWechatID
     * @param centage
     * @return
     */
    List<StudentInfo> randomCheckIn(String teacherWechatID, int centage);

    /**
     * 结束考勤
     * @param teacherWechatID
     */
    ResultMessage stopCheckin(String teacherWechatID);

    /**
     * 统计考勤结果
     * @param teacherID
     * @param courseID
     * @param seqNo
     */
    List<CheckinCalculateResult> calcSum(Long teacherID, Long courseID, Long seqNo, List<String> classIds);

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
     * 统计当前考勤记录
     * @param teacherWechatID
     */
    ResultMessage viewCurrentCheckIn(String teacherWechatID);

    /**
     * 绑定微信号
     * @param wechatId
     * @return
     */
    boolean bindingWechatId(Long teacherId,String wechatId);


    /**
     * 修改绑定的微信号
     * @param oldWechatId
     * @param newWechatId
     * @return
     */
    boolean alterWechatId(Long teacherId,String oldWechatId,String newWechatId);

    /**
     *教师信息
     */
    TeacherDetialInfo getMyInfo(String teacherWechatID);

    /**
     * 我的课表信息
     * @param teacherWechatID
     * @return
     */
    String getMyCourseInfo(String teacherWechatID);
}
