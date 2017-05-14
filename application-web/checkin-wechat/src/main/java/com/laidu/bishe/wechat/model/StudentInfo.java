package com.laidu.bishe.wechat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 学生信息
 * Created by laidu on 2017/5/7.
 */
@Data
@Builder
@AllArgsConstructor
public class StudentInfo {

    /**
     * 学号
     */
    private Long stuID;

    /**
     *
     */
    private String stuName;

    private String classID;

    private String wechatID;
}
