package io.pandora.mall.biz.rabbit.configuration;

import io.pandora.mall.biz.rabbit.config.RabbitMqQueue;
import io.pandora.mall.biz.rabbit.consumer.OrderCancelConsumer;
import io.pandora.mall.biz.rabbit.impl.DefaultRabbitMqRegister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfiguration {
    /**
     * 使用 默认的注册器 注册 消息队列的消费者(消息处理器)
     */
    @Bean
    Object listenerRabbit(DefaultRabbitMqRegister rabbitMqRegister) {
        // 注册订单消费者
        rabbitMqRegister.listenerQueue(orderCancelConsumer(), RabbitMqQueue.PANDORA_QUEUE_ORDER);

        return rabbitMqRegister;
    }

    @Bean
    OrderCancelConsumer orderCancelConsumer(){ return new OrderCancelConsumer(); }
}
