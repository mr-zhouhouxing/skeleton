package io.pandora.mall.push.offline.dto;

import io.pandora.mall.base.BaseDto;
import io.pandora.mall.push.message.MessageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Created by mr_zhou on 2021/1/4
 */
@Data
public class PushRequest extends BaseDto {

    @ApiModelProperty("推送渠道[1.个推推送,2.极光推送]")
    private Integer channel;

    @ApiModelProperty("消息推送实体")
    private MessageRequest request;

    public PushRequest() {}

    public PushRequest(Integer channel, MessageRequest request) {
        this.channel = channel;
        this.request = request;
    }

    @Override
    public String toString() {
        return "PushRequest{ channel = " + channel + ", request = " + request + '}';
    }
}
