package com.laidu.bishe.utils.exception;


/**
 * Created by cw on 15-11-20.
 */
public class NetworkException extends BaseException {
    public NetworkException(IErrorCode iErrorCode, Throwable e) {
        super(iErrorCode, e);
    }

    public NetworkException(IErrorCode iErrorCode) {
        super(iErrorCode);
    }
}
