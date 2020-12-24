package io.pandora.mall.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Created by John on 2020/3/5
 */
@Data
@Component
@PropertySource("classpath:config/config.properties")
@ConfigurationProperties(prefix = "sms.aliyun")
public class SmsProperties {
    private String product;
    private String domain;
    private String accessKeyId;
    private String accessKeyIdSelect;
}
