package com.laidu.bishe.common.web.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.laidu.bishe.utils.annotation.IPropertyFilter;
import com.laidu.bishe.utils.annotation.JSONPropertyFilter;
import com.laidu.bishe.utils.exception.IErrorCode;
import com.laidu.bishe.utils.fastjson.DateValueFilter;
import com.laidu.bishe.utils.fastjson.FastJsonUpperCaseTo_;
import com.laidu.bishe.utils.utils.DateUtil;
import com.laidu.bishe.utils.utils.JacksonUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collection;
import java.util.Date;

/**
 * Created by cw on 15-11-20.
 */
public class JSONResult extends Result<JSONObject> {
    private JSONResult(IErrorCode iErrorCode, boolean success, JSONObject data) {
        this(iErrorCode.getCode(), iErrorCode.getMessage(), success, data);
    }

    private JSONResult(String code, String message, boolean success, JSONObject data) {
        super.setCode(code);
        super.setMessage(message);
        super.setSuccess(success);
        super.setData(data);
    }

    public static JSONResult build(String code, String message, boolean success, JSONObject data) {
        return new JSONResult(code, message, success, data);
    }

    public static JSONResult ok() {
        return ok(new JSONObject());
    }

    public static JSONResult ok(Object object, String dateFormat) {
        return ok(JSON.parseObject(toJSONString(object, null, dateFormat)));
    }

    public static JSONResult ok(Object object, SerializeConfig config) {
        return ok(JSON.parseObject(toJSONString(object, null, null, config)));
    }

    public static JSONResult ok(Object object) {
        return ok(JSON.parseObject(toJSONString(object, null, DateUtil.DEFAULT_FORMAT)));
    }

    public static JSONResult ok(Object object, IPropertyFilter propertyFilter) {
        return ok(JSON.parseObject(toJSONString(object, propertyFilter, DateUtil.DEFAULT_FORMAT)));
    }

    public static JSONResult ok(Object object, IPropertyFilter propertyFilter, String dateFormat) {
        return ok(JSON.parseObject(toJSONString(object, propertyFilter, dateFormat)));
    }

    public static JSONResult ok(JSONObject data) {
        return new JSONResult(IErrorCode.OK, true, JSON.parseObject(toJSONString(data, null, DateUtil.DEFAULT_FORMAT)));
    }

    public static JSONResult ok(JSONObject data, String dateFormat) {
        return new JSONResult(IErrorCode.OK, true, JSON.parseObject(toJSONString(data, null, dateFormat)));
    }

    //服务异常
    public static JSONResult error() {
        return error(IErrorCode.ERROR);
    }

    public static JSONResult error(IErrorCode iErrorCode) {
        return error(iErrorCode, new JSONObject());
    }

    public static JSONResult error(IErrorCode iErrorCode, JSONObject data) {
        return new JSONResult(iErrorCode, false, data);
    }

    public static JSONResult error(String code, String message) {
        return new JSONResult(code, message, false, new JSONObject());
    }

    public static JSONResult custom(String code, String message, boolean success, JSONObject data) {
        return new JSONResult(code, message, success, data);
    }

    public JSONResult put(String key, Object value) {
        if (super.getData() == null) {
            super.setData(new JSONObject());
        }
        super.getData().put(key, value);
        return this;
    }

    public JSONResult propertyFilter(IPropertyFilter propertyFilter) {
        if (getData() != null) {
            super.setData(JSON.parseObject(toJSONString(getData(), propertyFilter, DateUtil.DEFAULT_FORMAT)));
        }
        return this;
    }

    public ModelAndView buildModelAndView() {
        return new ModelAndView(new MappingJackson2JsonView(), JacksonUtil.jsonToMap(this.buildJsonString()));
    }

    public String buildJsonString() {
        return toJSONString(this, null, DateUtil.DEFAULT_FORMAT);
    }

    private static String toJSONString(Object object, IPropertyFilter propertyFilter, String dateFormat) {
        return toJSONString(object, propertyFilter, dateFormat, SerializeConfig.getGlobalInstance());
    }

    private static String toJSONString(Object object, IPropertyFilter propertyFilter, String dateFormat, SerializeConfig config) {
        SerializeFilter[] filters = null;
        if (propertyFilter == null) {
            filters = new SerializeFilter[]{new FastJsonUpperCaseTo_(), new DateValueFilter()};
        } else {
            filters = new SerializeFilter[]{new JSONPropertyFilter(propertyFilter), new FastJsonUpperCaseTo_(), new DateValueFilter()};
        }
        if (object == null) {
            return new JSONObject().toJSONString();
        } else if (object instanceof String || object instanceof Long || object instanceof Integer || object instanceof Double || object instanceof Date) {
            JSONObject result = new JSONObject();
            result.put("value", object);
            return JSON.toJSONString(result, config, filters, dateFormat, JSON.DEFAULT_GENERATE_FEATURE);
        } else if (object instanceof Collection) {
            JSONObject result = new JSONObject();
            result.put("list", object);
            return JSON.toJSONString(result, config, filters, dateFormat, JSON.DEFAULT_GENERATE_FEATURE);
        } else {
            return JSON.toJSONString(object, config, filters, dateFormat, JSON.DEFAULT_GENERATE_FEATURE);
        }
    }

    public static void main(String[] args) {
        JSONObject result = new JSONObject();
        result.put("test",5.00);
        System.out.println(result.toJSONString());
//        System.out.println(JSONResult.ok().put("test", new BigDecimal("12")).buildJsonString());
    }
}
