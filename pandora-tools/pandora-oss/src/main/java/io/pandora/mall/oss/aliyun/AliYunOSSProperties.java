package io.pandora.mall.oss.aliyun;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Created by mr_zhou on 2020/12/30
 */
@Data
@Component
@ConfigurationProperties(prefix = "oss.aliyun")
public class AliYunOSSProperties {
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String endpoint;
}
