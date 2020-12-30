package io.pandora.mall.oss.config;

import io.pandora.mall.oss.UploadServer;
import io.pandora.mall.oss.aliyun.AliYunOSSProperties;
import io.pandora.mall.oss.aliyun.AliYunUploadServer;
import io.pandora.mall.oss.local.LocalUploadProperties;
import io.pandora.mall.oss.local.LocalUploadServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Created by mr_zhou on 2020/12/30
 */
@Slf4j
@EnableCaching
@Configuration
public class OSSConfiguration {
    /**
     * 本地上传(默认)
     */
    @Bean
    @ConditionalOnProperty(prefix = "oss.local", name = "localPath")
    @ConditionalOnMissingBean(UploadServer.class)
    public UploadServer localUploadServer(LocalUploadProperties properties) {
        if (log.isDebugEnabled()) {
            log.info("启用本地上传服务...");
        }
        return new LocalUploadServer(properties);
    }

    /**
     * 阿里云OSS上传
     */
    @Bean
    @ConditionalOnProperty(prefix = "oss.aliyun", name = "accessKeySecret")
    @ConditionalOnMissingBean(UploadServer.class)
    public UploadServer aliYunUploadServer(AliYunOSSProperties properties) {
        if (log.isDebugEnabled()) {
            log.info("启用阿里云上传服务...");
        }
        return new AliYunUploadServer(properties);
    }

}
