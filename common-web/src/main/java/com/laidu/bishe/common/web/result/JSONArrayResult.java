package com.laidu.bishe.common.web.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.laidu.bishe.utils.exception.IErrorCode;
import com.laidu.bishe.utils.utils.JacksonUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Created by cw on 15-11-20.
 */
public class JSONArrayResult extends Result<JSONArray> {
    private JSONArrayResult(IErrorCode iErrorCode, boolean success, JSONArray data) {
        this(iErrorCode.getCode(), iErrorCode.getMessage(),success,data);
    }

    private JSONArrayResult(String code, String message, boolean success, JSONArray data) {
        super.setCode(code);
        super.setMessage(message);
        super.setSuccess(success);
        super.setData(data);
    }

    public static JSONArrayResult build(String code, String message, boolean success, JSONArray data){
        return new JSONArrayResult(code,message,success,data);
    }

    public static JSONArrayResult ok() {
        return ok(new JSONArray());
    }

    public static JSONArrayResult ok(JSONArray data) {
        return new JSONArrayResult(IErrorCode.OK, true, data);
    }

    public static JSONArrayResult ok(Object object){
        if (object == null){
            return ok();
        }else if (object instanceof JSONArray){
            return ok(object);
        }else {
            return ok(JSON.parseArray(JSON.toJSONString(object)));
        }
    }

    //服务异常
    public static JSONArrayResult error() {
        return error(IErrorCode.ERROR);
    }

    public static JSONArrayResult error(IErrorCode iErrorCode) {
        return error(iErrorCode, new JSONArray());
    }

    public static JSONArrayResult error(IErrorCode iErrorCode, JSONArray data) {
        return new JSONArrayResult(iErrorCode, false, data);
    }

    public static JSONArrayResult error(String code, String message) {
        return new JSONArrayResult(code, message, false, new JSONArray());
    }

    public static JSONArrayResult custom(String code, String message, boolean success, JSONArray data){
        return new JSONArrayResult(code,message,success,data);
    }

    public ModelAndView buildModelAndView() {
        return new ModelAndView(new MappingJackson2JsonView(), JacksonUtil.jsonToMap(this.buildJsonString()));
    }

    private String buildJsonString() {
        return JacksonUtil.objToJson(this);
    }
}
