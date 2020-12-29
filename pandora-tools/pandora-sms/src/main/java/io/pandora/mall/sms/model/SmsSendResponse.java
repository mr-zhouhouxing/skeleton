package io.pandora.mall.sms.model;

import lombok.Builder;
import lombok.Data;

/**
 * @Description:普通短信发送响应实体类
 * @author Created by mr_zhou on 2020/12/25
 */
@Data
@Builder
public class SmsSendResponse {
    /** 响应时间 */
    private String time;

    /** 消息id */
    private String msgId;

    /** 状态码说明（成功返回空） */
    private String errorMsg;

    /** 状态码（详细参考提交响应状态码）*/
    private String code;
}
