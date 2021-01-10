package io.pandora.mall.rabbit.constant;

import org.springframework.stereotype.Component;

/**
 * @author Created by mr_zhou on 2020/11/27
 */
@Component
public class ExchangeType {
    public static final String DIRECT = "direct";
    public static final String FANOUT = "fanout";
    public static final String TOPIC = "topic";
    public static final String HEADERS = "headers";
    public static final String DELAYED = "x-delayed-message";
}
