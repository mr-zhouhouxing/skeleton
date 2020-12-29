package io.pandora.mall.sms.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import io.pandora.mall.sms.config.SmsProperties;
import io.pandora.mall.sms.model.SmsSendResponse;

/**
 * @author Created by mr_zhou on 2020/12/25
 */
public class SmsSendUtils {

    private static final String endpointName = "cn-hangzhou";

    private static String domain;
    private static String product;
    private static String accessKeyId;
    private static String accessKeyIdSelect;

    public SmsSendUtils(SmsProperties smsProperties) {
        accessKeyId = smsProperties.getAccessKeyId();
        accessKeyIdSelect = smsProperties.getAccessKeyIdSelect();
        product = smsProperties.getProduct();
        domain = smsProperties.getDomain();
    }

    /**
     * 调用第三方SMS平台 阿里云下发短信
     *
     * @param phoneNumber   手机号
     * @param message       消息主体
     * @return
     */
    public static SmsSendResponse sendMessage(String phoneNumber,String message){
        return SmsSendResponse.builder().build();
    }

    /**
     * 调用第三方SMS平台 阿里云下发验证码
     *
     * @param phoneNumber   手机号
     * @param verifyCode    验证码
     * @return
     */
    public static SmsSendResponse sendVerifyCode(String phoneNumber,String verifyCode){
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        try {
            IClientProfile profile = DefaultProfile.getProfile(endpointName, accessKeyId, accessKeyIdSelect);
            DefaultProfile.addEndpoint(endpointName, endpointName, product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(phoneNumber);
            request.setSignName("Pandora");             // 阿里云控制台对应签名
            request.setTemplateCode("SMS_169902168");   // 阿里云控制台对应模板Code
            request.setTemplateParam("{\"code\":\"" + verifyCode + "\"}");
            request.setOutId("yourOutId");

            long startTime = System.currentTimeMillis();
            SendSmsResponse response = acsClient.getAcsResponse(request);

            return SmsSendResponse
                    .builder()
                    .msgId(response.getBizId()).code(response.getCode()).errorMsg(response.getMessage())
                    .time(String.valueOf(System.currentTimeMillis() - startTime))
                    .build();

        }catch (Exception e){
            e.printStackTrace();
            return SmsSendResponse.builder().code("500").errorMsg(e.getMessage()).build();
        }
    }

}
