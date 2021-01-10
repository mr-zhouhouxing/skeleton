package io.pandora.mall.push.enume;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Created by John on 2020/4/10
 */
@Data
@ApiModel("WS心跳对象")
public class HeartMessage implements Serializable {
    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("消息类型")
    private Integer type;

    @ApiModelProperty("消息内容")
    private String message;

    @ApiModelProperty("消息时间")
    private String time;
}
