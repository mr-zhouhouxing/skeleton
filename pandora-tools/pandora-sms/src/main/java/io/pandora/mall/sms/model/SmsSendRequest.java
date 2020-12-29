package io.pandora.mall.sms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Created by mr_zhou on 2020/12/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsSendRequest {
    private String mobile;
    private String message;
    private Integer smsType;
}

