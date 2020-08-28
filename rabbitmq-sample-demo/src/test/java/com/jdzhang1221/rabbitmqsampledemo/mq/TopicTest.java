/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.jdzhang1221.rabbitmqsampledemo.mq;

import com.jdzhang1221.rabbitmqsampledemo.RabbitmqSampleDemoApplication;
import com.jdzhang1221.rabbitmqsampledemo.mq.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangjundong
 * @date 2020/8/2818:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqSampleDemoApplication.class)
public class TopicTest {

    @Autowired
    private TopicSender topicSender;

    @Test
    public void test(){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        topicSender.topicSender("time =>"+sf.format(new Date()));
    }
}
