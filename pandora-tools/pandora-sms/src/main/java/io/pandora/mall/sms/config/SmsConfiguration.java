package io.pandora.mall.sms.config;

import io.pandora.mall.sms.util.SmsSendUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Created by John on 2020/3/5
 */
@Slf4j
@EnableCaching
@Configuration
public class SmsConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "sms.aliyun", name = "accessKeyIdSelect")
    @ConditionalOnMissingBean(SmsSendUtils.class)
    public SmsSendUtils smsServer(SmsProperties properties) {
        if (log.isDebugEnabled()) {
            log.info("启用阿里云短信服务...");
        }
        return new SmsSendUtils(properties);
    }

}
