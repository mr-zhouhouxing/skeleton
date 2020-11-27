package io.pandora.mall.biz.rabbit.impl;

import io.pandora.mall.biz.rabbit.IRabbitMqExchange;
import io.pandora.mall.biz.rabbit.IRabbitMqQueue;
import io.pandora.mall.biz.rabbit.IRabbitMqRouting;
import io.pandora.mall.biz.rabbit.IRabbitMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @author Created by mr_zhou on 2020/11/26
 */
public class RabbitMqServiceImpl implements IRabbitMqService, RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    private Logger logger = LoggerFactory.getLogger(RabbitMqServiceImpl.class);

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }

    @Override
    public void send(IRabbitMqQueue queue, Object msg) {
        rabbitTemplate.convertAndSend(queue.queueName(),msg);
    }

    @Override
    public void send(IRabbitMqExchange exchange, IRabbitMqRouting routing, Object msg) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(exchange.exchangeName(),routing.routingKey(),msg,correlationData);
    }

    @Override
    public void send(IRabbitMqExchange exchange, IRabbitMqRouting routing, Object msg, long delay) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        if (delay > 0 ){
            MessagePostProcessor processor = (Message message) ->{
              message.getMessageProperties().setExpiration(delay + "");
              return message;
            };
            rabbitTemplate.convertAndSend(exchange.delayExchangeName(),routing.routingKey(),msg,processor,correlationData);
        }else {
            rabbitTemplate.convertAndSend(exchange.exchangeName(),routing.routingKey(),msg,correlationData);
        }
    }

    /**
     * 消息发送的回调
     *
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack){
            logger.info("消息发送到交换机成功,correlationId:{} cause:{}",correlationData.getId(),cause);
        }else {
            logger.info("消息发送到交换机失败,原因:{}",cause);
        }
    }

    /**
     * 消息发送失败响应
     *
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", message, replyCode, replyText, exchange, routingKey);
    }
}
