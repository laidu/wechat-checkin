package com.laidu.bishe.backstage.model;

import com.laidu.bishe.backstage.enums.FeatureTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by laidu on 2017/5/25.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecognitionInfo implements Serializable{


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 签到类型／多媒体文件类型
     */
    private FeatureTypeEnum typeEnum;

    /**
     * 学生微信id
     */
    private String wechatId;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 学生学号
     */
    private Long studentId;

    /**
     * 特征信息路径
     */
    private String filePath;
}
