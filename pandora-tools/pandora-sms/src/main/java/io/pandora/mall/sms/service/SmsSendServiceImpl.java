package io.pandora.mall.sms.service;

import io.pandora.mall.base.service.ConfigService;
import io.pandora.mall.base.service.impl.BaseServiceImpl;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.domian.system.Config;
import io.pandora.mall.domian.system.SystemSmsRecord;
import io.pandora.mall.email.model.EmailSendRequest;
import io.pandora.mall.email.model.EmailSendType;
import io.pandora.mall.email.service.EmailSendService;
import io.pandora.mall.mapper.system.SystemSmsRecordMapper;
import io.pandora.mall.redis.util.RedisUtils;
import io.pandora.mall.sms.model.SMSType;
import io.pandora.mall.sms.model.SmsErrorCode;
import io.pandora.mall.sms.model.SmsSendRequest;
import io.pandora.mall.sms.model.SmsSendResponse;
import io.pandora.mall.util.GenSmsCode;
import io.pandora.mall.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Created by John on 2020/3/5
 */
@Slf4j
@Service
public class SmsSendServiceImpl extends BaseServiceImpl<SystemSmsRecordMapper, SystemSmsRecord> implements SmsSendService {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ConfigService configService;
    @Autowired
    private EmailSendService emailSendService;

    @Override
    public SmsSendResponse sendVerifyCode(SmsSendRequest request) {
        long startTime = System.currentTimeMillis();
        Integer smsType = request.getSmsType();
        // 断言 校验
        Assert.isTrue(SMSType.verify(smsType),"Unsupported SMS sending types");
        Assert.hasText(request.getMobile(),"Cell phone number is not allowed to be empty");

        Object obj = redisUtils.get(Constant.VERIFY_CODE + request.getMobile());
        String code = Optional
                .ofNullable(obj == null ? null : obj.toString())
                .orElse(GenSmsCode.genSixVerifyCode());

        // SmsSendResponse response = SmsSendUtils.sendVerifyCode(request.getMobile(), code);

        SmsSendResponse response = SmsSendResponse
                .builder()
                .msgId("10000").code(Constant.OK).errorMsg("发送成功")
                .time(String.valueOf(System.currentTimeMillis() - startTime))
                .build();

        // 插入记录
        insertMobileSmsSendRecord(response,request.getMobile(),code,request.getSmsType());

        if (response.getCode().equalsIgnoreCase(SmsErrorCode.OK)) {
            redisUtils.set(Constant.VERIFY_CODE + request.getMobile() ,code ,Constant.VERIFY_CODE_TIME);
            log.info("【SMS】手机号:{},验证码:{},发送成功......",request.getMobile(),code);
            response.setErrorMsg("验证码发送成功");
            return response;
        }

        // 失败处理
        return this.smsSendErrorHandle(response,request,code);
    }

    @Override
    public boolean verifyCodeEquals(String phone, String code) {
        String verify = redisUtils.get(Constant.VERIFY_CODE + phone).toString();
        return StringUtils.isNotBlank(verify) ? code.equals(verify) : false ;
    }

    @Override
    public boolean exitVerifyCode(String phone) {
        return redisUtils.hasKey(Constant.VERIFY_CODE + phone);
    }

    @Override
    public void cleanVerifyCode(String phone) {
        redisUtils.del(Constant.VERIFY_CODE + phone);
    }

    /**
     * 短信发送失败处理器
     *
     * @param response  响应内容
     * @param request   请求参数
     * @param code      发送信息
     * @return
     */
    private SmsSendResponse smsSendErrorHandle(SmsSendResponse response,SmsSendRequest request,String code){
        String responseCode = response.getCode();

        // 进入失败逻辑处理
        if (responseCode.equalsIgnoreCase(SmsErrorCode.BUSINESS_LIMIT_CONTROL)){
            response.setErrorMsg("该手机号今日频率已超出最大限制次数,请联系客服或明日重试,谢谢");
            return response;
        }

        if (responseCode.equalsIgnoreCase(SmsErrorCode.DAY_LIMIT_CONTROL)){
            // 通知管理员
            List<String> email = new ArrayList<>();
            email.add(
                    configService.getValueByKey(Constant.SYSTEM_ADMIN_EMAIL, Constant.SYSTEM_ADMIN_DEFAULT_EMAIL)
            );
            Config config = configService.getByKey(Constant.SYSTEM_SMS_NUMBER_WARN);

            emailSendService.sendEmail(
                    new EmailSendRequest(email,config.getName(), config.getValue(), EmailSendType.TEXT.getType())
            );

            response.setErrorMsg("今日系统流量暴增,短信服务拥挤,请耐心等待稍后重试");
            return response;
        }

        // 进入短信发送重试
        Integer retryNumber = configService.getValue2Int(Constant.SMS_RETRY_NUMBER, 5);
        for (int index = 1; index <= retryNumber ; index++) {
            try {
                Thread.sleep(10000);
            }catch (InterruptedException e){ }
            log.info("【SMS】手机号:{},发送验证码:{} 失败,重试第{}次",request.getMobile(),code,index);
            sendVerifyCode(request);
        }
        return response;
    }

    /**
     * 添加短信发送记录
     *
     * @param parameter
     * @param mobile
     * @param code
     */
    private void insertMobileSmsSendRecord(SmsSendResponse parameter,String mobile,String code,int type){
        try {
            SystemSmsRecord record = new SystemSmsRecord();
            record.setCode(code);
            record.setMobile(mobile);
            record.setSmsType(type);
            record.setSmsId(parameter.getMsgId());
            record.setTime(parameter.getTime());
            record.setResponseCode(parameter.getCode());
            record.setResponseMsg(parameter.getErrorMsg());
            record.setCreateTime(new Date());
            this.save(record);
        }catch (Exception e){
            log.error("【SMS】插入:{},发送类型:{},短信记录出现异常,异常信息:{}",mobile,type,e.getMessage());
        }
    }
}
