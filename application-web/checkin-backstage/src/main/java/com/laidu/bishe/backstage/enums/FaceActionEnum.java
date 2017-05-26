package com.laidu.bishe.backstage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 刷脸动作
 * Created by laidu on 2017/5/24.
 */
@AllArgsConstructor
public enum FaceActionEnum {

    JIANDAOSHOU("剪刀手"),
    XIAOLIAN("笑脸"),
    ;

    @Getter
    @Setter
    public String name;
}
