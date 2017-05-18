package com.laidu.bishe.wechat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户类型枚举
 * Created by laidu on 2017/5/18.
 */
@AllArgsConstructor
public enum UserTypeEnum {

    STUDENT("student"),
    TEACHER("teacher");

    @Getter
    @Setter
    private String name;

}
