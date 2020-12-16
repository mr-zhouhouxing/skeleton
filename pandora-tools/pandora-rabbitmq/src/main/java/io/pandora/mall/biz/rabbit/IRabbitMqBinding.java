package io.pandora.mall.biz.rabbit;

/**
 * @author Created by mr_zhou on 2020/11/26
 */
public interface IRabbitMqBinding {
    // 需要绑定的交换机
    IRabbitMqExchange exchange();

    // 需要绑定的路由
    IRabbitMqRouting routing();

    // 需要绑定的队列
    IRabbitMqQueue queue();

    // 消息队列是否允许超时
    boolean allowDelay();
}
