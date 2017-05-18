package com.laidu.bishe.utils.exception;

import lombok.AllArgsConstructor;

/**
 * Created by chenwen on 16/12/19.
 */
@AllArgsConstructor
public enum BasicErrorCodeEnum implements IErrorCode {
    NOT_UPDATE_ERROR("数据未更新"),
    FILE_ERROR("文件操作错误"),
    CONF_NOT_EXISTS("配置不存在"),
    VIOLATION_OP("违规操作"),
    USERNAME_EXISTS("用户已经存在"),
    USER_EXISTS_ERROR("用户已存在，请直接登录"),
    AGENT_NOT_EXISTS("账号不存在，请先注册"),
    ;

    private final String message;

    @Override
    public String getCode() {
        return getCode(BasicCodeStartCode,ordinal());
    }

    @Override
    public String getMessage() {
        return message;
    }
}
