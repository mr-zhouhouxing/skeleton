package io.pandora.mall.sms.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.sms.config.SmsProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Created by John on 2020/3/5
 */
@Slf4j
public class AliyunServiceImpl implements AliyunSmsService {

    private String domain;
    private String product;
    private String accessKeyId;
    private String accessKeyIdSelect;

    public AliyunServiceImpl(SmsProperties smsProperties) {
        accessKeyId = smsProperties.getAccessKeyId();
        accessKeyIdSelect = smsProperties.getAccessKeyIdSelect();
        product = smsProperties.getProduct();
        domain = smsProperties.getDomain();
    }

    @Override
    public boolean sendRegisterVerify(String phone, String verifyCode) {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        try {
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeyIdSelect);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(phone);
            // 阿里云控制台对应签名
            request.setSignName("Pandora");
            // 阿里云控制台对应模板Code
            request.setTemplateCode("SMS_169902168");
            request.setTemplateParam("{\"code\":\"" + verifyCode + "\"}");
            request.setOutId("yourOutId");
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals(Constant.OK)) {
                log.info("======> 手机【{}】正在请求发送验证码【{}】....发送状态【{}】", phone, verifyCode, sendSmsResponse.getCode());
                return true;
            } else {
                log.error("====> 手机【{}】发送验证码失败，失败信息【{}】", phone, sendSmsResponse.getMessage());
            }
        } catch (Exception e) {
            log.error("====> 手机【{}】发送验证码异常,失败信息【{}】", phone, e);
        }
        return false;
    }

}
