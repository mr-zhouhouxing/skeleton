package io.pandora.mall.rabbit.configuration;

import io.pandora.mall.rabbit.config.RabbitMqQueue;
import io.pandora.mall.rabbit.consumer.OrderCancelConsumer;
import io.pandora.mall.rabbit.impl.DefaultRabbitMqRegister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ消费者注册中心
 */
@Configuration
public class ConsumerConfiguration {
    /**
     * 使用 默认的注册器 注册 消息队列的消费者(消息处理器)
     */
    @Bean
    Object listenerRabbit(DefaultRabbitMqRegister rabbitMqRegister) {
        // 监听订单消费者
        rabbitMqRegister.listenerQueue(orderCancelConsumer(), RabbitMqQueue.PANDORA_QUEUE_ORDER);

        return rabbitMqRegister;
    }

    @Bean
    OrderCancelConsumer orderCancelConsumer(){ return new OrderCancelConsumer(); }
}
