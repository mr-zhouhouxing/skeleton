package io.pandora.mall.chat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Created by mr_zhou on 2021/1/6
 */
@Data
@Component
@ConfigurationProperties(prefix = "user")
public class AppProp {
    private Integer port;
    private String active;
    private List<String> ignoreUrl;
}
