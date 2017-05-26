package com.laidu.bishe.backstage.service.impl;

import com.laidu.bishe.backstage.config.RabbitConfig;
import com.laidu.bishe.backstage.model.RecognitionInfo;
import com.laidu.bishe.backstage.service.QueueSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by laidu on 2017/5/25.
 */
@Slf4j
@Service
public class QueueSenderImpl implements QueueSender{

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitConfig rabbitConfig;


    @Override
    public void sendRecognitionInfo(RecognitionInfo recognitionInfo) {
        amqpTemplate.convertAndSend(rabbitConfig.getRecognitionQueue(),recognitionInfo);
    }
}
