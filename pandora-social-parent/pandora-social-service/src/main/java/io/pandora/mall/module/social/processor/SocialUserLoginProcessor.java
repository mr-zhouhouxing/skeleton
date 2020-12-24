package io.pandora.mall.module.social.processor;

import lombok.Builder;
import lombok.Data;

/**
 * 用户登录类型处理器
 *
 * @author Created by mr_zhou on 2020/12/24
 */
@Data
@Builder
public class SocialUserLoginProcessor {
    private String account;
    private String password;
    private String verifyCode;
    private Integer loginType;
}
