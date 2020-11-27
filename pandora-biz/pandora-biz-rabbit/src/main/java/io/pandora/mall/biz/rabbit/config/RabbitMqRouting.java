package io.pandora.mall.biz.rabbit.config;

import io.pandora.mall.biz.rabbit.IRabbitMqRouting;

/**
 * RabbitMq routing（路由定义）
 * */
public enum RabbitMqRouting implements IRabbitMqRouting {
    // MQ_ROUTING_TEST("mq.routing.test"),
    PANDORA_ROUTING_ORDER("pandora.routing.order");

    private String routingKey;

    @Override
    public String routingKey() {
        return this.routingKey;
    }

    RabbitMqRouting(String routingKey){
        this.routingKey = routingKey;
    }
}
