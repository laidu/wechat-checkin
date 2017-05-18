package com.laidu.bishe.utils.exception;

/**
 * Created by chenwen on 16/6/23.
 */
public interface IErrorCode {
    /**
     * WebBasicCodeEnum 开始code
     */
    int WebBasicCodeStartCode = 2;

    int LoginBasicCodeStartCode = 200;

    int BasicCodeStartCode = 2000;

    int MessageCodeStartCode = 4000;

    int CustomerCodeStartCode = 6000;

    IErrorCode OK = new IErrorCode() {
        @Override
        public String getCode() {
            return "E000000";
        }

        @Override
        public String getMessage() {
            return "请求成功";
        }
    };

    IErrorCode ERROR = new IErrorCode() {
        @Override
        public String getCode() {
            return "E000001";
        }

        @Override
        public String getMessage() {
            return "系统繁忙,请稍后再试";
        }
    };

    int codeLen = 6;

    String getCode();

    String getMessage();

    default String getCode(int startCode, int ordinal){
        startCode += ordinal;
        String code = String.valueOf(startCode);
        StringBuilder stringBuilder = new StringBuilder("E");
        for(int i = 0; i < codeLen - code.length(); ++i){
            stringBuilder.append("0");
        }
        stringBuilder.append(code);
        return stringBuilder.toString();
    }
}
