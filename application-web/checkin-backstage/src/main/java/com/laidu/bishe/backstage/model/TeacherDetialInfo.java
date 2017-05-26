package com.laidu.bishe.backstage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 教师详细信息
 * Created by laidu on 2017/5/22.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDetialInfo {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 教师工号
     */
    private Long teacherId;

    /**
     * 教师姓名
     */
    private String teacherName;

    /**
     * 教师注册时间
     */
    private Date registerTime;


    @Override
    public String toString() {
        return String.format("我的信息：\n\n " +
                "教师姓名：%s \n " +
                "教师工号：%s \n " +
                "注册日期：%s",teacherName,teacherId,sdf.format(registerTime));
    }
}
