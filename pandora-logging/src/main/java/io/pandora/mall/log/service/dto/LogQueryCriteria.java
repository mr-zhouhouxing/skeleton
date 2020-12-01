package io.pandora.mall.log.service.dto;

import io.pandora.mall.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Created by mr_zhou on 2020/12/1
 */
@Data
public class LogQueryCriteria {
    @Query(blurry = "username,description,address,requestIp,method,params")
    private String blurry;

    @Query
    private String logType;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;

    @Query
    private Integer type;
}
