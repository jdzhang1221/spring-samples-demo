package com.jdzhang1221.rabbitmqsampledemo.mq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "log")
public class TopicReceiver1 {
    @RabbitHandler
    public void process(String msg){
        System.out.println("log.# 消费消息： "+msg);
    }
}
