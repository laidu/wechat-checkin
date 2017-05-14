package com.laidu.bishe.wechat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 教师信息
 * Created by laidu on 2017/5/7.
 */
@Data
@Builder
@AllArgsConstructor
public class TeacherInfo {

    /**
     * 教师工号
     */
    private Long teaID;

    /**
     * 教师姓名
      */
    private String teaName;

    /**
     * 教师微信号
     */
    private String teaWechatID;
}
