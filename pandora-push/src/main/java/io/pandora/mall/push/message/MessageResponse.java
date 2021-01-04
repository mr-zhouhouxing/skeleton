package io.pandora.mall.push.message;

import io.pandora.mall.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Created by mr_zhou on 2021/1/4
 */
@Data
@ApiModel("统一推送对象")
public class MessageResponse extends BaseDto {
    @ApiModelProperty("消息类型")
    private Integer type;

    @ApiModelProperty("装饰系统内推送信息")
    private Object arrList;

    @ApiModelProperty("用户聊天信息")
    private MessageVo message;
}
