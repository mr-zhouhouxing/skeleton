package io.pandora.mall.module.social.processor.integral;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Created by mr_zhou on 2020/12/29
 */
@Data
@Builder
public class UserIntegralProcessor {
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "积分数量")
    private BigDecimal number;

    @ApiModelProperty(value = "积分来源:1.邀请")
    private Integer source;

    @ApiModelProperty(value = "来源详情")
    private String sourceMemo;
}
