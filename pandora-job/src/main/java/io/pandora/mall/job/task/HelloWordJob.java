package io.pandora.mall.job.task;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @author Created by mr_zhou on 2020/12/31
 */
@Component
//作业不并发
@DisallowConcurrentExecution
public class HelloWordJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("测试 任务 hello word .. ");
    }

}
