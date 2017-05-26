package com.laidu.bishe.backstage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by laidu on 2017/5/25.
 */
@AllArgsConstructor
public enum CheckinResultCal {

    ABSENCE(1,"缺勤"),
    LEAVE(2,"请假"),
    TARDY(4,"早退"),
    LATE(8,"迟到"),
    NORMAL(16,"正常"),
    ;

    @Getter
    @Setter
    private int code;

    @Getter @Setter
    private String message;

    @Override
    public String toString() {
        return this.getMessage();
    }

    public static CheckinResultCal getEnum(CheckInResultEnum resultEnum, CheckInResultEnum resultEnum1){

        for (CheckinResultCal checkResultCal:values()){

            if (checkResultCal.getCode()==(resultEnum.getCode()&resultEnum1.getCode())){
                return checkResultCal;
            }
        }
        throw new IllegalArgumentException();
    }

}
