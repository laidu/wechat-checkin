package com.laidu.bishe.backstage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 操作返回结果类
 * Created by laidu on 2017/5/15.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultMessage {

    /**
     * 消息code
     */
    private String code;

    /**
     * 消息内容
     */
    private String message;
}
