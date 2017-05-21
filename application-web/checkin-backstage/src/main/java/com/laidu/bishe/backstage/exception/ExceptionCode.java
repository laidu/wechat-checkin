package com.laidu.bishe.backstage.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by laidu on 2017/5/15.
 */
@AllArgsConstructor
public enum ExceptionCode {

    CHECKIN_IS_STARTED("M00000","考勤已经开始"),
    CHECKIN_START_SUCCEED("M00001","成功开启考勤"),
    CHECKIN_START_FAILD("M00002","当前不允许进行考勤")
    ;

    @Setter@Getter
    private String code;

    @Setter@Getter
    private String message;
}
