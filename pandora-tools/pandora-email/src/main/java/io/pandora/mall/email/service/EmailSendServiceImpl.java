package io.pandora.mall.email.service;

import io.pandora.mall.base.service.impl.BaseServiceImpl;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.domian.system.SystemEmailConfig;
import io.pandora.mall.email.model.EmailSendRequest;
import io.pandora.mall.email.model.EmailSendResponse;
import io.pandora.mall.email.model.EmailSendType;
import io.pandora.mall.email.util.EmailSendUtils;
import io.pandora.mall.exception.BadRequestException;
import io.pandora.mall.mapper.system.SystemEmailConfigMapper;
import io.pandora.mall.redis.util.RedisUtils;
import io.pandora.mall.util.GenSmsCode;
import io.pandora.mall.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @author Created by mr_zhou on 2020/12/25
 */
@Slf4j
@Service
public class EmailSendServiceImpl extends BaseServiceImpl<SystemEmailConfigMapper,SystemEmailConfig> implements EmailSendService {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private SystemEmailConfigMapper systemEmailConfigMapper;

    @Override
    public EmailSendResponse sendEmail(EmailSendRequest request) {
        // 校验
        Assert.isTrue(EmailSendType.verify(request.getSendType()),"The type of message sent is not supported");

        SystemEmailConfig systemEmailConfig = systemEmailConfigMapper.selectList(null).get(0);
        if (null == systemEmailConfig) {
            throw new BadRequestException("Please configure the mail sending information first");
        }

        switch (request.getSendType()) {
            case Constant.ONE :
                // 验证码
                return sendEmailVerifyCode(request,systemEmailConfig);
            case Constant.THREE :
                // 发送文本
                return sendEmailText(request,systemEmailConfig);
            default:
                // 发送HTML
                return null;
        }
    }

    private EmailSendResponse sendEmailVerifyCode(EmailSendRequest request,SystemEmailConfig config){
        String email = request.getToEmails().get(0);
        // 生成验证码
        String code = Optional
                .ofNullable(redisUtils.get(Constant.VERIFY_CODE + email).toString())
                .orElse(GenSmsCode.genSixVerifyCode());

        request.setSubject("邮箱验证");
        request.setContent("【Pan】尊敬的用户,您的验证码为:" + code + " 有效期15分钟,为了您账号的安全请勿讲验证码泄漏");

        EmailSendResponse response = EmailSendUtils.emailSend(request, config);

        log.info("【邮件发送】给邮箱:{},发送验证码:{} 响应状态:{} ......",email,code,response.getResponseMsg());
        if (response.getResponseCode().equalsIgnoreCase(Constant.OK)){
            redisUtils.set(Constant.VERIFY_CODE + email ,code ,Constant.VERIFY_CODE_TIME);
        }
        return response;
    }

    private EmailSendResponse sendEmailText(EmailSendRequest request,SystemEmailConfig config){
        EmailSendResponse response = EmailSendUtils.emailSend(request, config);

        String msg = request.getSubject() + ":" + request.getContent();
        log.info("【邮件发送】给邮箱:{},发送文本内容:{} 状态:{}......",request.getToEmails(),msg,response.getResponseMsg());

        return response;
    }

    @Override
    public boolean verifyCodeEquals(String email, String code) {
        String verify = redisUtils.get(Constant.VERIFY_CODE + email).toString();
        return StringUtils.isNotBlank(verify) ? code.equals(verify) : false ;
    }

    @Override
    public boolean exitVerifyCode(String email) {
        return redisUtils.hasKey(Constant.VERIFY_CODE + email);
    }

    @Override
    public void cleanVerifyCode(String email) {
        redisUtils.del(Constant.VERIFY_CODE + email);
    }
}
