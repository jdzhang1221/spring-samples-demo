/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.jdzhang1221.rabbitmqsampledemo.mq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangjundong
 * @date 2020/8/2815:27
 */
@Component
@RabbitListener(queues = "log.all")
public class TopicReceiver2 {
    @RabbitHandler
    public void process(String msg){
        System.out.println("log.* 消费消息： "+msg);
    }
}
