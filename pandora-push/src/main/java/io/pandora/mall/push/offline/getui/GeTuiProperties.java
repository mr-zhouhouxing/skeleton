package io.pandora.mall.push.offline.getui;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Created by John on 2020/3/27
 */
@Data
@Component
@ConfigurationProperties(prefix = "push.getui")
public class GeTuiProperties {
    private String logUrl;
    private String appId;
    private String appKey;
    private String masterSecret;
    private String url;
}
