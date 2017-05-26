package com.laidu.bishe.common.web.exception;


import com.laidu.bishe.utils.exception.BaseException;
import com.laidu.bishe.utils.exception.IErrorCode;

public class WebException extends BaseException {
    public WebException(IErrorCode iErrorCode, Throwable e) {
        super(iErrorCode, e);
    }

    public WebException(IErrorCode iErrorCode) {
        super(iErrorCode);
    }

    public WebException(String code , String message) {
        super(code,message);
    }
}
