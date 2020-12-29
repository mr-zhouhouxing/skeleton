package io.pandora.mall.sms.service;

import io.pandora.mall.base.service.BaseService;
import io.pandora.mall.domian.system.SystemSmsRecord;
import io.pandora.mall.sms.model.SmsSendRequest;
import io.pandora.mall.sms.model.SmsSendResponse;

/**
 * @author Created by John on 2020/3/5
 */
public interface SmsSendService extends BaseService<SystemSmsRecord> {

    SmsSendResponse sendVerifyCode(SmsSendRequest smsSendRequest);

    boolean verifyCodeEquals(String phone , String code);

    boolean exitVerifyCode(String phone);

    void cleanVerifyCode(String phone);

}
