package io.pandora.mall.job.service;

import io.pandora.mall.base.service.BaseService;
import io.pandora.mall.domian.system.SysTask;
import io.pandora.mall.pojo.dto.system.TaskQuery;
import org.quartz.SchedulerException;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * @author Created by mr_zhou on 2020/12/30
 */
public interface SystemTaskService extends BaseService<SysTask> {

    SysTask get(Long id);

    Map<String,Object> list(TaskQuery query , Pageable pageable);

    int insertTask(SysTask task);

    int updateTask(SysTask task);

    int removeTask(Long id);

    int removeBatch(Long[] ids);

    void initSchedule() throws SchedulerException;

    void changeStatus(Long jobId, String jobStatus) throws SchedulerException;

    void updateCron(Long jobId) throws SchedulerException;

    void run(SysTask task) throws SchedulerException;
}
