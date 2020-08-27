package com.jdzhang1221.rabbitmqsampledemo.mq.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    final static String QUEUE_NAME1="log";
    final static String QUEUE_NAME2="log.all";
    final static String QUEUE_NAME3="log.all.error";
    final static String EXCHANGE_NAME="topicExchange";

    @Bean
    public Queue queue1(){
        return new Queue(QUEUE_NAME1);
    }
    @Bean
    public Queue queue2(){
        return new Queue(QUEUE_NAME2);
    }
    @Bean
    public Queue queue3(){
        return new Queue(QUEUE_NAME3);
    }
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }
    @Bean
    Binding bindingtopicExchangeQueue1(Queue queue1,TopicExchange topicExchange){
        return BindingBuilder.bind(queue1).to(topicExchange).with("log.#");
    }
    @Bean
    Binding bindingtopicExchangeQueue2(Queue queue2,TopicExchange topicExchange){
        return BindingBuilder.bind(queue2).to(topicExchange).with("log.*");
    }
    @Bean
    Binding bindingtopicExchangeQueue3(Queue queue3,TopicExchange topicExchange){
        return BindingBuilder.bind(queue3).to(topicExchange).with("log.*.error");
    }
}
