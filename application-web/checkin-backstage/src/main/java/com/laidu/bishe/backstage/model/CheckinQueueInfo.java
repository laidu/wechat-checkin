package com.laidu.bishe.backstage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
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

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 开启考勤的教师微信id
     */
    private String teaWechatId;

    /**
     * 考勤次序号
     */
    private Long seqId;

    /**
     * 开启考勤的时间
     */
    private Date startTime;

    /**
     * 考勤课程名
     */
    private String courseName;

    /**
     * 考勤课程id
     *
     */
    private Long courseId;

    /**
     * 当前节次信息
     */
    private String sessionId;

    /**
     * 周
     */
    private String weekDay;

    /**
     * 考勤班级ids
     */
    private List<String> classIds;

    @Override
    public String toString() {

        return String.format("\n\n当前考勤信息：\n " +
                "课程名：%s \n " +
                "考勤开启时间：%s \n " +
                "授课班级：%s \n"
                ,courseName, sdf.format(startTime), classIds);

    }
}
