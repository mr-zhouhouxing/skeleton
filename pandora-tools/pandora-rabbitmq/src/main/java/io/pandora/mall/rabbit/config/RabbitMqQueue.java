package io.pandora.mall.rabbit.config;

import io.pandora.mall.rabbit.IRabbitMqQueue;

/**
 * RabbitMq queue(队列)的定义
 * */
public enum RabbitMqQueue implements IRabbitMqQueue {
    // MQ_QUEUE_TEST("mq.queue.test"),
    PANDORA_QUEUE_ORDER("pandora.queue.order");

    private String queueName;

    @Override
    public String queueName() {
        return this.queueName;
    }

    RabbitMqQueue(String queueName){
        this.queueName = queueName;
    }

}
