package io.pandora.mall.email.model;

import lombok.Builder;
import lombok.Data;

/**
 * 邮件发送响应实体
 *
 * @author Created by mr_zhou on 2020/12/25
 */
@Data
@Builder
public class EmailSendResponse {
    private String responseCode;
    private String responseMsg;
}

