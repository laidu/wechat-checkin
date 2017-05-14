package com.laidu.bishe.utils.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by chenwen on 16/9/21.
 */
public class HttpLogHelper {
    private static final String HEADER_TEMPLATE = "[HTTP REQUEST HEADER]: %s";
    private static final String HTTP_METHOD_TEMPLATE = "[HTTP REQUEST METHOD]: %s";
    private static final String URL_TEMPLATE = "[HTTP REQUEST URL]: %s";
    private static final String LOCAL_IP_TEMPLATE = "[HTTP REQUEST LOCAL IP ADDRESS]: %S";
    private static final String CLIENT_IP_TEMPLATE = "[HTTP REQUEST CLIENT IP ADDRESS]: %s";
    private static final String QUERY_STRING_TEMPLATE = "[HTTP REQUEST QUERY STRING]: %s";
    private static final String BODY_TEMPLATE = "[HTTP REQUEST BODY]: %s";
    private static final String HTTP_RESPONSE_TEMPLATE = "[HTTP RESPONSE CONTENT]: %s";
    private static final String HTTP_RESPONSE_CODE_TEMPLATE = "[HTTP RESPONSE CODE]: %s";
    private static final String EMPTY_FLAG = "[EMPTY]";

    private static final String HEADER_RESPONSE_TEMPLATE = "[HTTP RESPONSE HEADER]: %s";
    private static final String IP_TEMPLATE = "[HTTP REQUEST IP ADDRESS]: %s";
    private static final String HTTP_RESPONSE_BODY_SIZE_TEMPLATE = "[HTTP RESPONSE BODY SIZE]: %s";
    private static final String HTTP_COST_TIME = "[HTTP COST TIME]: %s";

    public HttpLogHelper() {
    }

    public static String logRequestOnClient(String header, HttpMethod.HTTP_METHOD method, String url, String localIp, String qs, String body) {
        if(!StringUtils.isEmpty(header) && !StringUtils.isEmpty(url) && !StringUtils.isEmpty(localIp)) {
            return String.format("%s | %s | %s | %s | %s | %s", new Object[]{String.format("[HTTP REQUEST HEADER]: %s", new Object[]{header}), String.format("[HTTP REQUEST METHOD]: %s", new Object[]{HttpMethod.getString(method)}), String.format("[HTTP REQUEST LOCAL IP ADDRESS]: %S", new Object[]{localIp}), String.format("[HTTP REQUEST URL]: %s", new Object[]{url}), String.format("[HTTP REQUEST QUERY STRING]: %s", new Object[]{StringUtils.isEmpty(qs)?"[EMPTY]":qs}), String.format("[HTTP REQUEST BODY]: %s", new Object[]{StringUtils.isEmpty(body)?"[EMPTY]":body})});
        } else {
            throw new IllegalArgumentException("`header`, `url` and `clientId` should not be empty!");
        }
    }

    public static String logResponseOnClient(String header, HttpMethod.HTTP_METHOD method, String url, String localIp, String qs, String reqBody, String code, String resBody) {
        if(StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("`code` should not be empty!");
        } else {
            return String.format("%s | %s | %s", new Object[]{logRequestOnClient(header, method, url, localIp, qs, reqBody), String.format("[HTTP RESPONSE CODE]: %s", new Object[]{code}), String.format("[HTTP RESPONSE CONTENT]: %s", new Object[]{StringUtils.isEmpty(resBody)?"[EMPTY]":resBody})});
        }
    }

    public static String logResponseOnClient(String header, String requestHeader, HttpMethod.HTTP_METHOD method, String url, String localIp, String qs, String reqBody, String code, long resBodySize, long costTime) {
        if(StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("`code` should not be empty!");
        } else {
            return String.format("%s | %s | %s | %s | %s", String.format(HEADER_RESPONSE_TEMPLATE,header),
                    logRequestOnClient(requestHeader, method, url, localIp, qs, reqBody),
                    String.format(HTTP_RESPONSE_CODE_TEMPLATE, code),
                    String.format(HTTP_RESPONSE_BODY_SIZE_TEMPLATE, resBodySize),
                    String.format(HTTP_COST_TIME,(costTime*1.0 / 1000.0)));
        }
    }

    public static String logRequestOnServer(HttpMethod.HTTP_METHOD method, String clientIp, String url, String qs, String body) {
        if(StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("`url` should not be empty!");
        } else {
            return String.format("%s | %s | %s | %s | %s", new Object[]{String.format("[HTTP REQUEST METHOD]: %s", new Object[]{HttpMethod.getString(method)}), String.format("[HTTP REQUEST CLIENT IP ADDRESS]: %s", new Object[]{clientIp}), String.format("[HTTP REQUEST URL]: %s", new Object[]{url}), String.format("[HTTP REQUEST QUERY STRING]: %s", new Object[]{StringUtils.isEmpty(qs)?"[EMPTY]":qs}), String.format("[HTTP REQUEST BODY]: %s", new Object[]{StringUtils.isEmpty(body)?"[EMPTY]":body})});
        }
    }

    public static String logResponseOnServer(HttpMethod.HTTP_METHOD method, String clientIp, String url, String qs, String reqBody, String code, String resBody) {
        return String.format("%s | %s | %s", new Object[]{logRequestOnServer(method, clientIp, url, qs, reqBody), String.format("[HTTP RESPONSE CODE]: %s", new Object[]{code}), String.format("[HTTP RESPONSE CONTENT]: %s", new Object[]{StringUtils.isEmpty(resBody)?"[EMPTY]":resBody})});
    }
}
