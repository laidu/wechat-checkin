package com.laidu.bishe.backstage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 周几
 * Created by laidu on 2017/5/21.
 */
@AllArgsConstructor
public enum WeekEnum {

    Monday("星期一"),
    Tuesday("星期二"),
    Wednesday("星期三"),
    Thursday("星期四"),
    Friday("星期五"),
    Saturday("星期六"),
    Sunday("星期日");

    @Getter@Setter
    public String name;
}
