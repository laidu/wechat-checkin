package com.laidu.bishe.common.web.result;

import com.alibaba.fastjson.JSONObject;
import com.laidu.bishe.utils.exception.BaseException;

import java.io.Serializable;

/**
 * Created by cw on 16-1-7.
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
}
