package io.pandora.mall.biz.rabbit;

/**
 * <small>
 *     Route
 * </small>
 *
 * @author Created by mr_zhou on 2020/11/26
 */
public interface IRabbitMqRouting {
    /**
     *  RabbitMQ RouteKey
     */
    String routingKey();
}
