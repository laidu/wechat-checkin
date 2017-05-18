package com.laidu.bishe.common.web.exception;

import com.laidu.bishe.utils.exception.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by chenwen on 16/12/19.
 */
@AllArgsConstructor
public enum WebBasicCodeEnum implements IErrorCode {
    ILLEGAL_ARGUMENT_ERROR("请求参数不合法,请核对参数"),
    ARGUMENT_PARSE_ERROR("参数解析异常"),
    NOT_SUPPORTED_METHOD_ERROR("不支持的请求方式"),
    PERMISSION_ERROR("权限不足"),
    USERNAME_OR_PASSWORD_ERROR("用户名或密码错误"),
    PASSWORD_LIMIT_ERROR("密码错误已经输错%s次,连续输错%s次账号将被锁定"),
    PASSWORD_LIMIT_MESSAGE_ERROR("密码错误次数过多,账号已被锁定"),
    UPLOAD_ERROR("上传文件异常")
    ;

    @Getter
    private final String message;

    @Override
    public String getCode() {
        return getCode(WebBasicCodeStartCode,ordinal());
    }
}
