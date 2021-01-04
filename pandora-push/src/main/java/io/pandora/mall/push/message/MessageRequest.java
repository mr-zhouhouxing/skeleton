package io.pandora.mall.push.message;

import io.pandora.mall.base.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Created by mr_zhou on 2021/1/4
 */
@Data
public class MessageRequest extends BaseDto {

    @ApiModelProperty("发送者ID")
    private long fromId;

    @ApiModelProperty("接收人ID")
    private long toId;

    @ApiModelProperty("接收类型")
    private String chatType;

    @ApiModelProperty("消息类型")
    private String type;

    @ApiModelProperty("消息内容")
    private String data;

    @ApiModelProperty("其他参数扩展字段")
    private String options;
}