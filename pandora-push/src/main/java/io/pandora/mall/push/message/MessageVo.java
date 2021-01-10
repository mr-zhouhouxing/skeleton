package io.pandora.mall.push.message;

import io.pandora.mall.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Created by mr_zhou on 2021/1/4
 */
@Data
@ApiModel("消息对象")
public class MessageVo extends BaseDto {
    @ApiModelProperty("消息ID")
    private String id;

    @ApiModelProperty("发送者头像")
    private String fromAvatar;

    @ApiModelProperty("发送者昵称")
    private String fromName;

    @ApiModelProperty("发送者ID")
    private long fromId;

    @ApiModelProperty("接收人ID")
    private long toId;

    @ApiModelProperty("接收人名称")
    private String toName;

    @ApiModelProperty("接收人头像")
    private String toAvatar;

    @ApiModelProperty("接收类型")
    private String chatType;

    @ApiModelProperty("消息类型")
    private String type;

    @ApiModelProperty("消息内容")
    private String data;

    @ApiModelProperty("其他参数扩展字段")
    private Object options;

    @ApiModelProperty("创建时间")
    private double createTime;

    @ApiModelProperty("撤回时间")
    private double removeTime;

    @ApiModelProperty("0 不撤回 1撤回")
    private int isRemove;

    @ApiModelProperty("发送状态success")
    private String sendStatus;

    @ApiModelProperty("是否已读")
    private int isRed;
}
