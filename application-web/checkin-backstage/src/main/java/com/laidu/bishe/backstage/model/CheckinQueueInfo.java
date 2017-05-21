package com.laidu.bishe.backstage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 考勤消息对列信息
 * Created by laidu on 2017/5/19.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckinQueueInfo {

    /**
     * 开启考勤的教师微信id
     */
    private String wechatId;

    /**
     * 开启考勤的时间
     */
    private Date startTime;

    /**
     * 考勤课程名
     */
    private String courseName;

    /**
     * 考勤班级ids
     */
    private List<String> classIds;

}
