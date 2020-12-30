package io.pandora.mall.oss.local;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Created by mr_zhou on 2020/12/30
 */
@Data
@Component
@ConfigurationProperties(prefix = "oss.local")
public class LocalUploadProperties {
    private String localPath;
    private String rootURL;
}
