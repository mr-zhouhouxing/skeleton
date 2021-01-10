package io.pandora.mall.rabbit.config;

import io.pandora.mall.rabbit.IRabbitMqExchange;

/**
 * RabbitMq Exchange(交换机)定义
 * */
public enum RabbitMqExchange implements IRabbitMqExchange {
    // 测试交换机
    //MQ_EXCHANGE_TEST("mq.exchange.test") ,
    /**
     * 商城订单交换机
     */
    PANDORA_EXCHANGE_ORDER("pandora.exchange.order");

    private String exchangeName;

    @Override
    public String exchangeName() {
        return this.exchangeName;
    }

//    @Override
//    public String type() {
//        if (exchangeName.equals("pandora.exchange.order")){
//            return ExchangeType.DIRECT;
//        }
//        return ExchangeType.TOPIC;
//    }

    RabbitMqExchange(String exchangeName){
        this.exchangeName = exchangeName;
    }
}
