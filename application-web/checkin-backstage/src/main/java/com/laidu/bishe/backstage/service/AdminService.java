package com.laidu.bishe.backstage.service;

import com.laidu.bishe.backstage.enums.ImportFileType;
import com.laidu.bishe.backstage.model.Message;

import java.io.BufferedInputStream;
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
    Message importTeacherInfoByCsv(String fileName);
    Message importTeacherInfoByExcel(String fileName);

    /**
     * 导入学生信息接口
     * @param fileName
     * @return
     */
    Message importStudentInfoByCsv(String fileName);
    Message importStudentInfoByExcel(String fileName);

    /**
     * 导入课程信息接口
     * @param fileName
     * @return
     */
    Message importCourseInfoByCsv(String fileName);
    Message importCourseInfoByExcel(String fileName);


}
