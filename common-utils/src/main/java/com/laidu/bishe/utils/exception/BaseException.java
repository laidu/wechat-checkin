package com.laidu.bishe.utils.exception;


/**
 * Created by cw on 15-11-20.
 */
public class BaseException extends RuntimeException {

    private String code;

    private IErrorCode iErrorCode;


    public BaseException(IErrorCode iErrorCode, Throwable e) {
        super(iErrorCode.getMessage(), e);
        this.code = iErrorCode.getCode();
        this.iErrorCode = iErrorCode;
    }

    public BaseException(IErrorCode iErrorCode) {
        super(iErrorCode.getMessage());
        this.code = iErrorCode.getCode();
        this.iErrorCode = iErrorCode;
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(String code, String message, Throwable e) {
        super(message,e);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public IErrorCode getiErrorCode() {
        return iErrorCode;
    }
}
