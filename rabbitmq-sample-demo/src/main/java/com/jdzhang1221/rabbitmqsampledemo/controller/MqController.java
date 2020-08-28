/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.jdzhang1221.rabbitmqsampledemo.controller;

import com.jdzhang1221.rabbitmqsampledemo.mq.topic.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangjundong
 * @date 2020/8/2815:36
 */
@RequestMapping("/mq")
@RestController
public class MqController {
    @Autowired
    private TopicSender topicSender;

    @RequestMapping("/send")
    public void send(){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        topicSender.topicSender("time =>"+sf.format(new Date()));
    }
}
