package io.pandora.mall.biz.rabbit;

import java.io.IOException;

/**
 * @author Created by mr_zhou on 2020/11/26
 */
public interface IRabbitMqRegister {
    /**
     * 注册所有的 Exchange(交换机)
     */
    void registerExchange(IRabbitMqExchange... exchanges) throws IOException;

    /**
     * 注册所有的 Queue(队列)
     */
    void registerQueue(IRabbitMqQueue... queues) throws IOException;

    /**
     * 注册所有的 Binding(路由绑定)
     */
    void registerBinding(IRabbitMqBinding... bindings) throws IOException;

    /**
     * 监听 Queue(队列) 处理收到的消息
     */
    void listenerQueue(IRabbitMqListener listener, IRabbitMqQueue... queues);
}
