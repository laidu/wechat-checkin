package com.laidu.bishe.backstage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 学生详细信息
 * Created by laidu on 2017/5/23.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetialInfo {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 学号
     */
    private Long studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学生班级名
     */
    private String classId;

    /**
     * 教师注册时间
     */
    private Date registerTime;

    @Override
    public String toString() {
        return String.format("我的信息：\n\n " +
                "学生学号：%s \n " +
                "学生姓名：%s \n " +
                "所在班级：%s \n " +
                "注册日期：%s", studentId, studentName, classId ,sdf.format(registerTime));
    }

}
