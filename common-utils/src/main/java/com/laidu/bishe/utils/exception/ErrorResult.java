package com.laidu.bishe.utils.exception;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by chenwen on 16/3/14.
 */
public class ErrorResult implements Serializable {
    private String code;
    private String message;
    private JSONObject result;

    private ErrorResult(BaseException e) {
        code = e.getCode();
        message = e.getMessage();
    }

    public static ErrorResult build(BaseException e) {
        return new ErrorResult(e);
    }

    public JSONObject getJSONResult() {
        JSONObject error = new JSONObject();
        error.put("code",code);
        error.put("customer",message);
        return result = result == null ? error : result;
    }

    @Override
    public String toString(){
        JSONObject error = new JSONObject();
        error.put("code",code);
        error.put("customer",message);
        return error.toJSONString();
    }
}

