package io.pandora.mall.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "pandora")
@PropertySource("classpath:config/config.properties")
public class PandoraProperties {

    /** 项目名,末尾不带 */
    private String projectName;

    /** 项目根目录,末尾带 */
    private String projectRootURL;

}
