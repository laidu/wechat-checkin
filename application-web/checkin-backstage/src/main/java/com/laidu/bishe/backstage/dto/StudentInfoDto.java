package com.laidu.bishe.backstage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生信息 Dto
 * Created by laidu on 2017/5/18.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfoDto {

    /**
     * 学号
     */
    private Long stuId;

    /**
     *学生姓名
     */
    private String stuName;

    /**
     *学生微信id
     */
    private String wechatId;


    /**
     *
     */
    private String featurePath;

    /**
     *
     */
    private Short featureType;
}
