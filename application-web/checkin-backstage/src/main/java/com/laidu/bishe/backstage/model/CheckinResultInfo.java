package com.laidu.bishe.backstage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 签到结果类
 * Created by laidu on 2017/5/24.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckinResultInfo {

    /**
     * 学生学号
     */
    private Long studentId;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 签到时间
     */
    private Date checkinTime;

    /**
     * 签到类型
     */
    private String checkinType;

    /**
     * 签到结果
     */
    private String checkinResult;

}
