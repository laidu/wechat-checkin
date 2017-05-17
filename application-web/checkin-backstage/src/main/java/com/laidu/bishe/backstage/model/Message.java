package com.laidu.bishe.backstage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 操作返回结果类
 * Created by laidu on 2017/5/15.
 */
@Data
@Builder
@AllArgsConstructor
public class Message {

    /**
     * 消息code
     */
    private String code;

    /**
     * 消息内容
     */
    private String message;
}
