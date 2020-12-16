package io.pandora.mall.module.security.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Configuration
@ConditionalOnExpression("!'${ignore}'.isEmpty()")
@ConfigurationProperties(prefix = "ignore")
@EnableConfigurationProperties(FilterIgnorePropertiesConfig.class)
public class FilterIgnorePropertiesConfig {

    private List<String> urls = new ArrayList<>();

    private List<String> authenticates = new ArrayList<>();

}
