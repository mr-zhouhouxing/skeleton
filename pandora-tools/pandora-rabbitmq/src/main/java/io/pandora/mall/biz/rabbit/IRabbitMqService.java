package io.pandora.mall.biz.rabbit;

/**
 * RabbitMQ 对外服务
 */
public interface IRabbitMqService {

    /**
     * direct 发送
     * @param queue
     * @param msg
     */
    void send(IRabbitMqQueue queue,Object msg);

    /**
     * 给Rabbitmq发送消息
     * */
    void send(IRabbitMqExchange exchange, IRabbitMqRouting routing, Object msg);

    /**
     * 给Rabbitmq发送延迟消息
     * */
    void send(IRabbitMqExchange exchange, IRabbitMqRouting routing, Object msg, long delay);

}
