package com.laidu.bishe.utils.enums;

/**
 * Created by chenwen on 16/6/23.
 */
public enum BasicErrorCodeEnum implements IErrorCode{
    OK_E000000("请求成功"),
    SERVER_ERROR_E000001("系统繁忙,请稍后再试"),
    ILLEGAL_ARGUMENT_ERROR_E000002("请求参数不合法,请核对参数"),
    ARGUMENT_PARSE_ERROR_E000003("参数解析异常"),
    NOT_SUPPORTED_METHOD_ERROR_E000004("不支持的请求方式"),
    PERMISSION_ERROR_E000005("权限不足"),
    DOWNLOAD_ERROR_E000006("下载异常"),
    USERNAME_EXISTS_E000007("用户名已存在"),
    DOWNLOAD_OUTTIME_E000008("下载超时"),
    UPLOAD_FILE_EMPTY_E000009("上传文件为空"),
    UPLOAD_FILE_FAILED_E000010("上传文件失败"),
    GET_CAR_INFO_FAILURE_E000011("获取车辆信息失败"),
    GET_CAR_CRAWLER_RESULT_FAILURE_E000012("获取爬取结果信息失败"),
    ILLEGAL_ARGUMENT_ERROR_E000013("车牌号和车架号、引擎号不能同时为空")
    ;

    private String message;

    BasicErrorCodeEnum(String message){
        this.message = message;
    }

    @Override
    public String getCode() {
        return name().substring(name().lastIndexOf("_") + 1);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
