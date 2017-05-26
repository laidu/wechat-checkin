package com.laidu.bishe.backstage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 特征信息
 * Created by laidu on 2017/5/22.
 */
@AllArgsConstructor
public enum FeatureTypeEnum {

    FACE(0, "面部信息"),
    VOICE(1, "声纹信息"),
    LEAVE(2,"假条信息");

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    public String name;
}
