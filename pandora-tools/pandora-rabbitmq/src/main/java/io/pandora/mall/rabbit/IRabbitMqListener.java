package io.pandora.mall.rabbit;

import org.springframework.amqp.core.MessageListener;

/**
 * 消息监听消费者
 *
 * @author Created by mr_zhou on 2020/11/26
 */
public interface IRabbitMqListener extends MessageListener {
    /**
     * 处理 RabbitMQ 的消息
     * @param obj
     * @return
     */
    boolean handleMessage(Object obj);

}
