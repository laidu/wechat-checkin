package com.laidu.bishe.backstage.service;

import com.laidu.bishe.backstage.model.CheckinContentInfo;
import com.laidu.bishe.backstage.model.RecognitionInfo;

/**
 * 队列消息发送者接口定义
 * Created by laidu on 2017/5/25.
 */
public interface QueueSender {

    void sendRecognitionInfo(RecognitionInfo recognitionInfo);
}
