package com.laidu.bishe.backstage.exception;

import com.laidu.bishe.utils.exception.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by laidu on 2017/5/15.
 */
@AllArgsConstructor
public enum BackstageException implements IErrorCode {

    CHECKIN_IS_STARTED("B00000","考勤已经开始"),
    CHECKIN_START_SUCCEED("B00001","成功开启考勤"),
    CHECKIN_START_FAILD("B00002","由于未找到相关课程信息,当前不允许进行考勤"),
    CHECKIN_NOT_EXIST("B00003","尚未开启考勤或考勤已结束"),
    CHECKIN_STOP_HAND("B00004","成功结束当前考勤"),
    CURRENT_IS_NOT_ALLOWED_CHECKIN("B00005","当前时间不允许考勤"),
    TEACHER_INFO_NOT_EXIST("B00006","教师信息不存在"),
    STUDENT_INFO_NOT_EXIST("B00007","该学生信息不存在"),
    UPDATE_STUDENT_INFO_FAILD("B00008","更新绑定学生微信号失败"),
    ;

    @Setter@Getter
    private String code;

    @Setter@Getter
    private String message;
}
