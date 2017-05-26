package com.laidu.bishe.backstage.service;

import com.laidu.bishe.backstage.domain.CheckinCalculateResult;
import com.laidu.bishe.backstage.model.CheckinQueueInfo;
import com.laidu.bishe.backstage.model.ResultMessage;
import com.laidu.bishe.backstage.model.SeqDetialInfo;
import org.redisson.api.RMap;

import java.util.List;

/**
 * 微信考勤后台service接口
 * Created by laidu on 2017/5/15.
 */
public interface AdminService {

    /**
     * 导入教师信息接口
     * @param fileName 教师信息文件
     * @return
     */
    ResultMessage importTeacherInfoByCsv(String fileName);
    ResultMessage importTeacherInfoByExcel(String fileName);

    /**
     * 导入学生信息接口
     * @param fileName
     * @return
     */
    ResultMessage importStudentInfoByCsv(String fileName);
    ResultMessage importStudentInfoByExcel(String fileName);

    /**
     * 导入课程信息接口
     * @param fileName
     * @return
     */
    ResultMessage importCourseInfoByCsv(String fileName);
    ResultMessage importCourseInfoByExcel(String fileName);

    /**
     * 导入节次信息接口
     * @param fileName
     * @return
     */
    ResultMessage importSessionInfoByCsv(String fileName);
    ResultMessage importSessionInfoByExcel(String fileName);

    /**
     * 通过教师id请求考勤次序号，只有正确拿到考勤次序号教师才能开启考勤
     * @param teacherId
     * @return
     */
    SeqDetialInfo requestSeqInfo(Long teacherId);

    /**
     * 获取考勤队列
     * @return
     */
    RMap<Long,CheckinQueueInfo> getCheckinQueue();

    /**
     * 统计考勤结果
     * @param checkinCalculateResults
     * @return
     */
    String parseCheckinResult(List<CheckinCalculateResult> checkinCalculateResults);
}
