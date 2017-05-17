package com.laidu.bishe.wechat.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 微信关注状态
 * Created by laidu on 2017/5/17.
 */
@AllArgsConstructor
public enum FollowStatusEnum {

    FOLLOW(1, "已关注"),
    UNFOLLOW(0, "未关注");

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;
}
