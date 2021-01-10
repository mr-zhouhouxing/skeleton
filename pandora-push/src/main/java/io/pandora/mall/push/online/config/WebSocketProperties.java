package io.pandora.mall.push.online.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "websocket")
public class WebSocketProperties {
    private Long heartbeatAllowIntervalTime;
    private Long heartbeatCheckIntervalTime;
    private Long wsWaitingIntervalTime;
}
