package io.pandora.mall.manage.admin.controller;

import io.pandora.mall.job.service.SystemTaskService;
import io.pandora.mall.manage.BaseController;
import io.pandora.mall.manage.log.annotation.SysLog;
import io.pandora.mall.pojo.dto.system.TaskQuery;
import io.pandora.mall.response.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//    @PreAuthorize("")
    public ResponseBean list( TaskQuery query, Pageable pageable) {
        // 查询列表数据
        Map<String,Object> result = taskService.list(query,pageable);

        return ResponseBean.succeed(result);
    }



}
