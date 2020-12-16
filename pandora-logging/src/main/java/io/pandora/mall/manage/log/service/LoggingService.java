package io.pandora.mall.manage.log.service;

import io.pandora.mall.base.service.BaseService;
import io.pandora.mall.domian.system.Log;
import io.pandora.mall.manage.log.service.dto.LogQueryCriteria;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @author Created by mr_zhou on 2020/11/30
 */
public interface LoggingService extends BaseService<Log> {

    /**
     * 查询全部数据
     * @param criteria 查询条件
     * @return /
     */
    List<Log> queryAll(LogQueryCriteria criteria);

    /**
     * 分页查询
     * @param criteria 查询条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(LogQueryCriteria criteria, Pageable pageable);

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
