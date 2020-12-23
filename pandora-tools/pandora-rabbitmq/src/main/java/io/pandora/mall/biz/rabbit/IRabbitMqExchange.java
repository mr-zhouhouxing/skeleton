package io.pandora.mall.biz.rabbit;

import io.pandora.mall.biz.rabbit.constant.ExchangeType;

import java.util.Map;

/**
 * <small>
 *     RabbitMQ Exchange
 * </small>
 *
 * @author Created by mr_zhou on 2020/11/26
 */
public interface IRabbitMqExchange {

    /**
     * This is Exchange Name
     */
    String exchangeName();

    /**
     * Exchange type: DIRECT("direct"),FANOUT("fanout"),TOPIC("topic"),HEADERS("headers")
     */
    default String type(){return ExchangeType.TOPIC;}

    /**
     * whether data support
     */
    default boolean durable(){return true;}

    /**
     * whether Auto Delete
     */
    default boolean autoDelete(){return false;}

    /**
     * whether Direct binding
     * true : NO
     * false: Yes
     */
    default boolean internal(){return false;}

    /**
     * extra parameters
     */
    default Map<String,Object> arguments(){return null;}

    /**
     * default delayed Exchange
     */
    default String delayExchangeName(){ return "delay." + exchangeName(); }
}
