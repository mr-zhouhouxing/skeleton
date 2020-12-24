package io.pandora.mall.sms.service;

/**
 * @author Created by John on 2020/3/5
 */
public interface AliyunSmsService {

    boolean sendRegisterVerify(String phone, String verifyCode);

}
