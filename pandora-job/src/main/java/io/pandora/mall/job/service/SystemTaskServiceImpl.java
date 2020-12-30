package io.pandora.mall.job.service;

import com.github.pagehelper.PageInfo;
import io.pandora.mall.base.service.impl.BaseServiceImpl;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.domian.system.SysTask;
import io.pandora.mall.exception.CustomException;
import io.pandora.mall.job.QuartzManager;
import io.pandora.mall.job.enume.JobStatusEnum;
import io.pandora.mall.mapper.system.SysTaskMapper;
import io.pandora.mall.pojo.dto.system.TaskQuery;
import io.pandora.mall.support.QueryHelpPlus;
import io.pandora.mall.util.spring.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Created by mr_zhou on 2020/12/30
 */
@Slf4j
@Service
public class SystemTaskServiceImpl extends BaseServiceImpl<SysTaskMapper, SysTask> implements SystemTaskService {

    @Autowired
    private SysTaskMapper taskMapper;
    @Autowired
    private QuartzManager quartzManager;

    @Override
    public SysTask get(Long id) { return taskMapper.selectById(id); }

    @Override
    public Map<String,Object> list(TaskQuery query, Pageable pageable) {
        getPage(pageable);
        List list = taskMapper.selectList(QueryHelpPlus.getPredicate(SysTask.class, query));
        PageInfo<SysTask> page = new PageInfo<>(list);

        Map<String,Object> map = new HashMap<>();
        map.put("count",page.getTotal());
        map.put("data",page.getList());

        return map;
    }

    @Override
    public int insertTask(SysTask task) {
        task.setJobStatus(JobStatusEnum.STOP.getCode());
        task.setCreateUser(SecurityUtils.getUsername());
        task.setCreateTime(new Date());
        task.setUpdateUser(SecurityUtils.getUsername());
        task.setUpdateTime(new Date());
        return taskMapper.insert(task);
    }

    @Override
    public int updateTask(SysTask task) {
        SysTask sysTask = get(task.getId());
        if (sysTask == null) {
            throw new CustomException("该任务不存在");
        }

        BeanUtils.copyProperties(task,sysTask);
        return taskMapper.updateById(sysTask);
    }

    @Override
    public int removeTask(Long id) {
        SysTask sysTask = get(id);
        if (sysTask == null)  {
            throw new CustomException("该任务不存在");
        }
        try {
            quartzManager.deleteJob(sysTask);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return taskMapper.deleteById(id);
    }

    @Override
    public int removeBatch(Long[] ids) {
        if (ids == null && ids.length <= Constant.ZERO){
            return 0;
        }
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            try {
                SysTask sysTask = get(id);
                quartzManager.deleteJob(sysTask);
                idList.add(id);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
        return taskMapper.deleteBatchIds(idList);
    }

    @Override
    public void initSchedule() throws SchedulerException {
        List<SysTask> taskList = taskMapper.selectList(null);
        if (taskList == null){
            log.info("[定时任务] 初始化定时任务条数为空...return");
            return;
        }
        log.info("[定时任务] 初始化定时任务条数:{}",taskList.size());
        taskList.forEach(task -> {
            if (JobStatusEnum.RUNNING.getCode().equals(task.getJobStatus())){
                quartzManager.addJob(task);
            }
        });
    }

    @Override
    public void changeStatus(Long jobId, String jobStatus) throws SchedulerException {
        SysTask task = get(jobId);
        if (task == null) {
            return;
        }
        if (JobStatusEnum.STOP.getCode().equals(jobStatus)) {
            quartzManager.deleteJob(task);
            task.setJobStatus(JobStatusEnum.STOP.getCode());
        } else {
            task.setJobStatus(JobStatusEnum.RUNNING.getCode());
            quartzManager.addJob(task);
        }
        updateTask(task);
    }

    @Override
    public void updateCron(Long jobId) throws SchedulerException {
        SysTask task = get(jobId);
        if (task == null) {
            return;
        }
        if (JobStatusEnum.RUNNING.getCode().equals(task.getJobStatus())) {
            quartzManager.updateJobCron(task);
        }
        updateTask(task);
    }

    @Override
    public void run(SysTask task) throws SchedulerException {
        quartzManager.runJobNow(task);
    }
}
