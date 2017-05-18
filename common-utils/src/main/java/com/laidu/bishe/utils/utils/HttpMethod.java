package com.laidu.bishe.utils.utils;

/**
 * Created by chenwen on 16/9/21.
 */
public class HttpMethod {
    public HttpMethod() {
    }

    public static String getString(HttpMethod.HTTP_METHOD method) {
        return method.getString();
    }

    public static enum HTTP_METHOD implements HttpMethod.IBaseEnum {
        HTTP_GET("GET"),
        HTTP_HEADER("HEADER"),
        HTTP_POST("POST"),
        HTTP_PUT("PUT"),
        HTTP_DELETE("DELETE"),
        HTTP_TRACE("TRACE"),
        HTTP_PATCH("PATCH"),
        HTTP_CONNECT("CONNECT"),
        HTTP_OPTIONS("OPTIONS");

        private final String _strValue;

        private HTTP_METHOD(String strValue) {
            this._strValue = strValue;
        }

        public String getString() {
            return this._strValue;
        }
    }

    interface IBaseEnum {
        String getString();
    }
}
