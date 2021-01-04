package io.pandora.mall.manage.admin.controller;

import io.pandora.mall.constant.Constant;
import io.pandora.mall.domian.system.SysTask;
import io.pandora.mall.enume.CodeEnum;
import io.pandora.mall.job.enume.JobStatusEnum;
import io.pandora.mall.job.service.SystemTaskService;
import io.pandora.mall.manage.BaseController;
import io.pandora.mall.manage.log.annotation.SysLog;
import io.pandora.mall.pojo.dto.system.TaskQuery;
import io.pandora.mall.response.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Created by mr_zhou on 2020/12/30
 */
@RestController
@Api(tags = {"系统管理 - 【定时任务】"})
@RequestMapping("/${webPath}/task")
public class TaskController extends BaseController {

    @Autowired
    private SystemTaskService taskService;

    @PostMapping("/list")
    @SysLog("查询定时任务列表")
    @ApiOperation("查询定时任务列表")
    public ResponseBean list( TaskQuery query, Pageable pageable) {
        // 查询列表数据
        Map<String,Object> result = taskService.list(query,pageable);

        return ResponseBean.succeed(result);
    }

    @PostMapping("/add")
    @SysLog("添加定时任务")
    @ApiOperation("添加定时任务")
    public ResponseBean add(@RequestBody SysTask task) {
        int insert = taskService.insertTask(task);

        if (insert > Constant.ZERO){
            return ResponseBean.failCodeAndMsg(CodeEnum.ERROR.getCode(),"新增成功");
        }
        return ResponseBean.succeed("新增成功");
    }

    @PostMapping("/update")
    @SysLog("修改定时任务")
    @ApiOperation("修改定时任务")
    public ResponseBean update(@RequestBody SysTask task) {
        SysTask sysTask = taskService.get(task.getId());
        if (sysTask == null){
            return ResponseBean.failCodeAndMsg(CodeEnum.ERROR.getCode(), "修改任务失败,任务不存在");
        }
        if (JobStatusEnum.RUNNING.getCode().equals(sysTask.getJobStatus())) {
            return ResponseBean.failCodeAndMsg(CodeEnum.ERROR.getCode(), "修改之前请先停止任务！");
        }
        int updateTask = taskService.updateTask(task);

        return updateTask > 0 ? ResponseBean.succeed("修改成功")
                : ResponseBean.failCodeAndMsg(CodeEnum.ERROR.getCode(), "修改失败！");
    }

    @PostMapping("/changeStatus")
    @SysLog("修改定时任务状态")
    @ApiOperation("修改定时任务状态")
    public ResponseBean update( Long id, Boolean jobStatus ) {
        String status = jobStatus == true ? JobStatusEnum.RUNNING.getCode() : JobStatusEnum.STOP.getCode();

        try {
            taskService.changeStatus(id,status);
            return ResponseBean.succeed("修改成功");

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return ResponseBean.failCodeAndMsg(CodeEnum.ERROR.getCode(), "修改失败！");
    }

    @PostMapping("/changeCron/{id}")
    @SysLog("修改定时任务表达式")
    @ApiOperation("修改定时任务表达式[使Quartz表达式与数据库保持一致]")
    public ResponseBean changeCron(@PathVariable("id") Long id ) {
        try {
            taskService.updateCron(id);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return ResponseBean.succeed("修改表达式成功");

    }

    @GetMapping("/run/{id}")
    @SysLog("立即运行任务")
    @ApiOperation("立即运行任务")
    public ResponseBean run(@PathVariable("id") Long id) {
        SysTask sysTask = taskService.get(id);
        if (sysTask == null){
            return ResponseBean.failCodeAndMsg(CodeEnum.ERROR.getCode(),"执行任务失败,任务不存在");
        }
        if (JobStatusEnum.STOP.getCode().equals(sysTask.getJobStatus())) {
            return ResponseBean.failCodeAndMsg(CodeEnum.ERROR.getCode(), "立即执行请先开启任务！");
        }
        try {
            taskService.run(sysTask);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return ResponseBean.succeed("执行任务成功");
    }

    @GetMapping("/remove/{id}")
    @SysLog("删除定时任务")
    @ApiOperation("删除定时任务")
    public ResponseBean remove(@PathVariable("id") Long id) {
        SysTask sysTask = taskService.get(id);
        if (sysTask == null){
            return ResponseBean.failCodeAndMsg(CodeEnum.ERROR.getCode(),"删除失败,任务不存在");
        }
        if (JobStatusEnum.RUNNING.getCode().equals(sysTask.getJobStatus())) {
            return ResponseBean.failCodeAndMsg(CodeEnum.ERROR.getCode(), "删除前请先停止任务！");
        }
        int removeTask = taskService.removeTask(id);

        return removeTask > 0 ? ResponseBean.succeed("删除成功")
                : ResponseBean.failCodeAndMsg(CodeEnum.ERROR.getCode(), "删除失败");
    }

    @PostMapping("/removeBatch")
    @SysLog("删除定时任务")
    @ApiOperation("删除定时任务")
    public ResponseBean remove(@RequestParam("ids[]") Long[] ids) {
        if (ids == null && ids.length <= Constant.ZERO ){
          return ResponseBean.failCodeAndMsg(CodeEnum.ERROR.getCode(), "删除失败,ID数组是空的");
        }
        for (Long id : ids) {
            SysTask sysTask = taskService.get(id);
            if (sysTask != null){
                if (JobStatusEnum.RUNNING.getCode().equals(sysTask.getJobStatus())) {
                    return ResponseBean.failCodeAndMsg(CodeEnum.ERROR.getCode(), "删除前请先停止任务！");
                }
            }
        }
        int removeBatch = taskService.removeBatch(ids);
        return removeBatch > 0 ? ResponseBean.succeed("删除成功")
                : ResponseBean.failCodeAndMsg(CodeEnum.ERROR.getCode(), "删除失败");
    }

}
