package com.laidu.bishe.common.web.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.laidu.bishe.common.web.exception.WebBasicCodeEnum;
import com.laidu.bishe.common.web.exception.WebException;
import com.laidu.bishe.utils.exception.BaseException;
import com.google.gson.Gson;
import com.laidu.bishe.utils.utils.HttpMethod;
import com.laidu.bishe.utils.utils.JAXBUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Http utils
 */
public class HttpUtils {
    /**
     * Logger based on slf4j
     */

    private static Validator validator = Validation
            .buildDefaultValidatorFactory().getValidator();
    private static Logger logger = Logger.getLogger(HttpUtils.class);

    public static String getRealIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-UserLoginDTO-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-UserLoginDTO-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        logger.info("ip is " + ip);
        return ip;
    }

    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    public static String getRequestBodyAsJson(HttpServletRequest request) {
        try {
            return IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
        } catch (IOException e) {
            throw new WebException(WebBasicCodeEnum.ARGUMENT_PARSE_ERROR,e);
        }
    }

    public static String getQueryStringAsJson(HttpServletRequest request) {
        try {
            String queryStr = request.getQueryString();
            if (StringUtils.isNotEmpty(queryStr)) {
                return getRequestBody(URLDecoder.decode(request.getQueryString(), request.getCharacterEncoding()));
            } else {
                return "{}";
            }
        } catch (UnsupportedEncodingException e) {
            throw new WebException(WebBasicCodeEnum.ARGUMENT_PARSE_ERROR,e);
        }
    }

    public static <T> T getModelFromQueryString(String queryStringJson, Class<T> type){
        T result = new Gson().fromJson(queryStringJson, type);
        return result;
    }

    public static <T> T getModelFromQueryString(HttpServletRequest request, Class<T> type){
        T result = new Gson().fromJson(getQueryStringAsJson(request), type);
        return result;
    }

    public static <T> T getModelFromRequest(HttpServletRequest request, Class<T> type){
        T result = null;
        try {
            String requestBody = null;
            if (request.getMethod().equalsIgnoreCase(HttpMethod.HTTP_METHOD.HTTP_POST.getString())) {
                if (request.getContentType().startsWith(ContentType.APPLICATION_FORM_URLENCODED.getMimeType())){
                    Map<String, String[]> parameterMap = request.getParameterMap();
                    JSONObject json = new JSONObject();
                    for(String key : parameterMap.keySet()){
                        String[] value = parameterMap.get(key);
                        if (value != null && value.length > 0) {
                            json.put(key, parameterMap.get(key)[0]);
                        }
                    }
                    requestBody = json.toJSONString();
                }else if (request.getContentType().startsWith(ContentType.TEXT_XML.getMimeType())){
                    JAXBUtil<T> jaxbUtil = new JAXBUtil<>();
                    result = jaxbUtil.unmarshal(type, IOUtils.toString(request.getInputStream(), request.getCharacterEncoding()));
                }else {
                    requestBody = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
                    if (org.apache.commons.lang.StringUtils.isNotEmpty(requestBody) && !requestBody.startsWith("{")) {
                        requestBody = getRequestBody(requestBody);
                    }
                }
            } else if (request.getMethod().equalsIgnoreCase(HttpMethod.HTTP_METHOD.HTTP_GET.getString())) {
                if (StringUtils.isEmpty(request.getQueryString())){
                    requestBody = "{}";
                }else {
                    requestBody = getRequestBody(URLDecoder.decode(request.getQueryString(), request.getCharacterEncoding()));
                }
            }
            if (result == null) {
                result = JSON.parseObject(requestBody, type);
            }
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new WebException(WebBasicCodeEnum.ARGUMENT_PARSE_ERROR,e);
        }
        return result;
    }

    public static String getReceiveParam(HttpServletRequest request) {
        BufferedReader bufferedReader = null;
        StringBuilder data = new StringBuilder();
        try {
            request.setCharacterEncoding("UTF-8");
            bufferedReader = request.getReader();
            if (bufferedReader == null) {
                return null;
            }
            String line;
            while ((line = bufferedReader.readLine())!=null){
                data.append(line);
            }
        } catch (Exception e) {
            logger.error("ͨ解析参数异常", e);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ie) {
                logger.error("ͨ解析参数异常", ie);
            }
        }
        return data.toString();
    }



    public static String getRequestBody(String requestBody) {
        if (StringUtils.isEmpty(requestBody)) {
            throw new WebException(WebBasicCodeEnum.ILLEGAL_ARGUMENT_ERROR);
        }
        Pattern pattern = Pattern.compile("(req|param)=\\{(.+:.+)+\\}");
        Matcher matcher = pattern.matcher(requestBody);
        if (matcher.find()) {
            return requestBody.replaceFirst("(req|param)=","");
        }

        JSONObject requestBodyJSON = null;
        matcher = Pattern.compile("(([a-zA-Z0-9_]+)=([^&]+))&?+").matcher(requestBody);
        while (matcher.find()) {
            if (requestBodyJSON == null) {
                requestBodyJSON = new JSONObject();
            }
            String key = matcher.group(2);
            String value = matcher.group(3);
            if (value.startsWith("[") && value.endsWith("]")){
                requestBodyJSON.put(key, JSONArray.parseArray(value));
            }else if (value.startsWith("{") && value.endsWith("}")){
                requestBodyJSON.put(key, JSONObject.parseObject(value));
            }else {
                requestBodyJSON.put(key, value);
            }
        }
        if (requestBodyJSON == null) {
            logger.error(requestBody);
            throw new WebException(WebBasicCodeEnum.ILLEGAL_ARGUMENT_ERROR);
        }
        return JSON.toJSONString(requestBodyJSON);
    }

    public static void main(String[] args) {
        String requestBody = "timestamp=1234567891023&signature=A82F684D47FE99528209B6EC0A56F1C0";
        JSONObject requestBodyJSON = null;
        Matcher matcher = Pattern.compile("(([a-zA-Z0-9_]+)=([^&]+))&?+").matcher(requestBody);
        while (matcher.find()) {
            if (requestBodyJSON == null) {
                requestBodyJSON = new JSONObject();
            }
            requestBodyJSON.put(matcher.group(2), matcher.group(3));
        }
        if (requestBodyJSON == null) {
            logger.error(requestBody);
            throw new WebException(WebBasicCodeEnum.ILLEGAL_ARGUMENT_ERROR);
        }
        logger.error(JSON.toJSONString(requestBodyJSON));
    }
}
