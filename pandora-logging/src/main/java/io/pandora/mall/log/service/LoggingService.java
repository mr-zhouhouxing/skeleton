package io.pandora.mall.log.service;

import io.pandora.mall.base.BaseService;
import io.pandora.mall.domian.system.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

/**
 * @author Created by mr_zhou on 2020/11/30
 */
public interface LoggingService extends BaseService {
    /**
     * 保存日志
     *
     * @param username
     * @param ip
     * @param joinPoint
     * @param log
     * @param uid
     */
    @Async
    void saveLogging(String username, String ip, ProceedingJoinPoint joinPoint, Log log, Long uid);

}
