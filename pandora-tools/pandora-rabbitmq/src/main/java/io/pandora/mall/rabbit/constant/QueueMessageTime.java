package io.pandora.mall.rabbit.constant;

import org.springframework.stereotype.Component;

/**
 * 消息延迟时间常量
 *
 * @author Created by mr_zhou on 2020/11/27
 */
@Component
public class QueueMessageTime {

    // 10 S
    public static final long TEN_SECOND = 10 * 1000;

    // 15 Min
    public static final long FIFTEEN_MIN = 15 * 60000;

}
