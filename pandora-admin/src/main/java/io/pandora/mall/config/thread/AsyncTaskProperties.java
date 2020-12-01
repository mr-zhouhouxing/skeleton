package io.pandora.mall.config.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 线程助手类
 *
 * @author https://juejin.im/entry/5abb8f6951882555677e9da2
 */
@Data
@Component
@ConfigurationProperties(prefix = "task.pool")
public class AsyncTaskProperties {
    private int corePoolSize;
    private int maxPoolSize;
    private int keepAliveSeconds;
    private int queueCapacity;
}
