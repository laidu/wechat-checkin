package com.laidu.bishe.backstage.dto;

import com.laidu.bishe.backstage.model.FeatureInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private Long studentId;

    /**
     *学生姓名
     */
    private String studentName;

    /**
     *学生微信id
     */
    private String wechatId;


    /**
     * 学生特征信息
     */
    private List<FeatureInfo> featureInfos;

}
