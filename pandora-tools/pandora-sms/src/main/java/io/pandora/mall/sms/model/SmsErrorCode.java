package io.pandora.mall.sms.model;

import org.springframework.stereotype.Component;

/**
 * <p>
 *  阿里云文档：https://help.aliyun.com/document_detail/101346.html?spm=a2c4g.11174283.3.7.567c2c42dFrum2
 * </p>
 * @author Created by mr_zhou on 2020/12/25
 */
@Component
public class SmsErrorCode {

    // 发送成功
    public static final String OK = "OK";

    /**
     * @达到系统日发送总量
     *
     * 解决方案：如需修改限额，请在短信服务控制台左侧导航栏中单击国内消息设置>安全设置，修改发送总量阈值。
     */
    public static final String DAY_LIMIT_CONTROL = "isv.DAY_LIMIT_CONTROL";

    // 短信发送频率超限
    public static final String BUSINESS_LIMIT_CONTROL = "isv.BUSINESS_LIMIT_CONTROL";

    // IP 地址超出范围 即不在中国区域境内
    public static final String DENY_IP_RANGE = "isv.DENY_IP_RANGE";

    // 手机号码格式不正确
    public static final String MOBILE_NUMBER_ILLEGAL = "isv.MOBILE_NUMBER_ILLEGAL";

}
