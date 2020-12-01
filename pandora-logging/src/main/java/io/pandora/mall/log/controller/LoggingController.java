package io.pandora.mall.log.controller;

import io.pandora.mall.log.service.LoggingService;
import io.pandora.mall.log.service.dto.LogQueryCriteria;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Pageable;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by mr_zhou on 2020/12/1
 */
@RestController
@RequestMapping("/${webPath}/logging")
@Api(tags = {"系统管理:日志管理"})
public class LoggingController {

    private final LoggingService loggingService;

    public LoggingController(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @GetMapping
    @ApiOperation("日志查询")
    @PreAuthorize("@el.check('admin','log:list')")
    public ResponseEntity<Object> getLogs(LogQueryCriteria criteria, Pageable pageable){
        criteria.setLogType("INFO");
        criteria.setType(0);
        return new ResponseEntity<>(loggingService.queryAll(criteria,pageable), HttpStatus.OK);
    }





}

