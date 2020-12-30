package io.pandora.mall.job.listener;

import io.pandora.mall.job.service.SystemTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Created by mr_zhou on 2020/12/30
 */
@Order(1)
@Component
public class ScheduleJobInitListener implements CommandLineRunner {

    @Autowired
    private SystemTaskService taskService;

    @Override
    public void run(String... args) throws Exception {
        try {
            taskService.initSchedule();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
