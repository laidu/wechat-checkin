package com.laidu.bishe.wechat.exception;

import com.laidu.bishe.common.web.exception.WebBasicCodeEnum;
import com.laidu.bishe.utils.exception.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信考勤业务异常枚举
 * Created by laidu on 2017/5/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WechatExceptionEnum implements IErrorCode {


    private String code;
    private String message;
}
