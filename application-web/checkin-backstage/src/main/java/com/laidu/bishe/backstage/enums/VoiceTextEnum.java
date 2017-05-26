package com.laidu.bishe.backstage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 朗读文本消息
 * Created by laidu on 2017/5/24.
 */
@AllArgsConstructor
public enum VoiceTextEnum {

    WOAIXUEXI("我爱学习"),
    WOAISHANGKE("我爱上课"),
    WOCONGBUTAOKE("我从不逃课"),
    ;

    @Getter
    @Setter
    public String name;
}
