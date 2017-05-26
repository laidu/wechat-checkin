package com.laidu.bishe.common.web.exception;

import com.laidu.bishe.utils.exception.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum WebBasicCodeEnum implements IErrorCode {
    ILLEGAL_ARGUMENT_ERROR("请求参数不合法,请核对参数"),
    ARGUMENT_PARSE_ERROR("参数解析异常"),
    NOT_SUPPORTED_METHOD_ERROR("不支持的请求方式"),
    PERMISSION_ERROR("权限不足"),
    USERNAME_OR_PASSWORD_ERROR("用户名或密码错误"),
    UPLOAD_ERROR("上传文件异常")
    ;

    @Getter
    private final String message;

    @Override
    public String getCode() {
        return getCode(WebBasicCodeStartCode,ordinal());
    }
}
