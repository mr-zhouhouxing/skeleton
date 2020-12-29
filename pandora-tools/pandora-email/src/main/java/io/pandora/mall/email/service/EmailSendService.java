package io.pandora.mall.email.service;

import io.pandora.mall.base.service.BaseService;
import io.pandora.mall.domian.system.SystemEmailConfig;
import io.pandora.mall.email.model.EmailSendRequest;
import io.pandora.mall.email.model.EmailSendResponse;

/**
 * @author Created by mr_zhou on 2020/12/25
 */
public interface EmailSendService extends BaseService<SystemEmailConfig> {

    /**
     * 发送邮件
     * @param request
     * @return
     */
    EmailSendResponse sendEmail(EmailSendRequest request);

    boolean verifyCodeEquals(String email , String code);

    boolean exitVerifyCode(String email);

    void cleanVerifyCode(String email);
}
