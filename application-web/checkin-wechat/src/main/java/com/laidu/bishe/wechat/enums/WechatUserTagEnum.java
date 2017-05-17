package com.laidu.bishe.wechat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 微信用户标签
 * Created by laidu on 2017/5/17.
 */
@AllArgsConstructor
public enum WechatUserTagEnum {

    UNREGISTER(0, "未注册"),
    STUDENT(1, "学生用户"),
    TEACHER(2,"教师用户");

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;
}
