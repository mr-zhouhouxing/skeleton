package io.pandora.mall.sms.config;

import io.pandora.mall.sms.service.AliyunServiceImpl;
import io.pandora.mall.sms.service.AliyunSmsService;
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
    @ConditionalOnMissingBean(AliyunSmsService.class)
    public AliyunServiceImpl aliyunSmsServer(SmsProperties properties) {
        if (log.isDebugEnabled()) {
            log.info("启用阿里云短信服务...");
        }
        return new AliyunServiceImpl(properties);
    }
}
