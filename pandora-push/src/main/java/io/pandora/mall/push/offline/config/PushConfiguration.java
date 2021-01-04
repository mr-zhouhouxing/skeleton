package io.pandora.mall.push.offline.config;

import io.pandora.mall.push.offline.getui.GeTuiProperties;
import io.pandora.mall.push.offline.getui.GeTuiServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 第三方推送 配置
 *
 * @author Created by mr_zhou on 2021/1/4
 */
@Slf4j
@Configuration
public class PushConfiguration {

    /**
     * 启用个推推送服务
     *
     * @param properties
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "push.getui", name = "getuiAccessKeySecret")
    @ConditionalOnMissingBean(GeTuiServer.class)
    public GeTuiServer geTuiServer(GeTuiProperties properties) {
        if (log.isDebugEnabled()) {
            log.info("启用个推推送服务...");
        }
        return new GeTuiServer(properties);
    }

}
