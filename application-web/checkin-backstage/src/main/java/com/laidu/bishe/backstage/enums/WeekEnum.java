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

    Sunday(1,"星期日"),
    Monday(2,"星期一"),
    Tuesday(3,"星期二"),
    Wednesday(4,"星期三"),
    Thursday(5,"星期四"),
    Friday(6,"星期五"),
    Saturday(7,"星期六"),
    ;

    /**
     * 以周日为第一天
     */
    @Getter@Setter
    private int indexOfWeek;

    @Getter@Setter
    public String name;

    @Override
    public String toString() {
        return this.getName();
    }

    public static WeekEnum getEnum(int indexOfWeek){
        for (WeekEnum weekEnum:values()){
            if (indexOfWeek == weekEnum.indexOfWeek){
                return weekEnum;
            }
        }
        throw new IllegalArgumentException();
    }
}
