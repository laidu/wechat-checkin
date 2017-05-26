package com.laidu.bishe.backstage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 考勤结果以枚举值表示
 * Created by laidu on 2017/5/15.
 */
@AllArgsConstructor
public enum CheckInResultEnum {

    ABSENCE(1,"缺勤"),
    LEAVE(2,"请假"),
    TARDY(4,"早退"),
    LATE(8,"迟到"),
    NORMAL(16,"正常"),
    ;

    @Getter @Setter
    private int code;

    @Getter @Setter
    private String message;

    @Override
    public String toString() {
        return this.getMessage();
    }

    public static CheckInResultEnum getEnum(String var){
        for (CheckInResultEnum checkInResultEnum:values()){
            if (checkInResultEnum.getMessage().equalsIgnoreCase(var)){
                return checkInResultEnum;
            }
        }
        throw new IllegalArgumentException();
    }

    public static CheckInResultEnum getEnum(int var){
        for (CheckInResultEnum checkInResultEnum:values()){
            if (checkInResultEnum.getCode()==var){
                return checkInResultEnum;
            }
        }
        throw new IllegalArgumentException();
    }

}
