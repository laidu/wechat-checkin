package com.laidu.bishe.common.web.result;

import java.io.Serializable;

/**
 * Created by cw on 15-11-20.
 */
public class Result<T> implements Serializable {
    /**
     * 状态码
     */
    private String code;

    /**
     * 错误消息
     */
    private String message;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 数据
     */
    private T data;

    public String getCode() {
        return code;
    }

    protected void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    protected void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    protected void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    protected void setData(T data) {
        this.data = data;
    }
}
