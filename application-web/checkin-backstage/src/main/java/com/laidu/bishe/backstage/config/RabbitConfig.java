package com.laidu.bishe.backstage.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq 配置
 * Created by laidu on 2017/5/25.
 */
@Slf4j
@Configuration
@Data
public class RabbitConfig {

    /**
     * 识别队列
     */
    @Value("${rabbit.queue.recognition_queue:recognition_queue}")
    private String recognitionQueue;

    /**
     * 发送消息队列
     */
    @Value("${rabbit.queue.send_message_queue:send_message_queue}")
    private String sendMessageQueue;


    @Bean
    public Queue queue() {
        // 是否持久化
        boolean durable = true;
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = false;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new Queue(recognitionQueue, durable, exclusive, autoDelete);
    }

    @Bean
    public TopicExchange exchange() {
        // 是否持久化
        boolean durable = true;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new TopicExchange(recognitionQueue, durable, autoDelete);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(recognitionQueue);
    }


}
