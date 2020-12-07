package io.pandora.mall.config.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Created by mr_zhou on 2020/12/1
 */
@Slf4j
@EnableAsync
@Configuration
public class AsyncTaskExecutePool implements AsyncConfigurer {

    private final AsyncTaskProperties properties;

    public AsyncTaskExecutePool(AsyncTaskProperties taskProperties) {
        this.properties = taskProperties;
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(properties.getCorePoolSize());
        //最大线程数
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        //队列容量
        executor.setQueueCapacity(properties.getQueueCapacity());
        //活跃时间
        executor.setKeepAliveSeconds(properties.getKeepAliveSeconds());
        //线程名字前缀
        executor.setThreadNamePrefix("pd-async-");
        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();

        return executor;
    }

    /**
     * 异步任务异常处理器
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            log.error("====" + throwable.getMessage() + "====", throwable);
            log.error("exception method:" + method.getName());
        };
    }
}
