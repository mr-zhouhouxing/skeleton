package io.pandora.mall.module.social.impl;

import io.pandora.mall.base.service.ConfigService;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.domian.social.SocialUser;
import io.pandora.mall.domian.social.SocialUserExample;
import io.pandora.mall.domian.social.SocialUserInfo;
import io.pandora.mall.domian.social.SocialUserInvite;
import io.pandora.mall.email.service.EmailSendService;
import io.pandora.mall.exception.BadRequestException;
import io.pandora.mall.mapper.social.SocialUserInviteMapper;
import io.pandora.mall.mapper.social.SocialUserMapper;
import io.pandora.mall.module.security.MyUserDetailsService;
import io.pandora.mall.module.security.service.ITokenService;
import io.pandora.mall.module.social.SocialUserInfoService;
import io.pandora.mall.module.social.SocialUserIntegralService;
import io.pandora.mall.module.social.SocialUserService;
import io.pandora.mall.module.social.enume.SocialUserLoginStatus;
import io.pandora.mall.module.social.enume.SocialUserLoginType;
import io.pandora.mall.module.social.processor.user.SocialUserLoginProcessor;
import io.pandora.mall.module.social.support.AbstractUserService;
import io.pandora.mall.pojo.dto.social.PasswordDto;
import io.pandora.mall.pojo.dto.social.RegisterDto;
import io.pandora.mall.pojo.vo.social.SocialUserInfoVo;
import io.pandora.mall.pojo.vo.system.TokenVo;
import io.pandora.mall.push.offline.service.SystemPushService;
import io.pandora.mall.sms.model.SMSType;
import io.pandora.mall.sms.model.SmsSendRequest;
import io.pandora.mall.sms.model.SmsSendResponse;
import io.pandora.mall.sms.service.SmsSendService;
import io.pandora.mall.util.CodeUtils;
import io.pandora.mall.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
@Slf4j
@Service
public class SocialUserServiceImpl extends AbstractUserService<SocialUserMapper, SocialUser> implements SocialUserService {

    @Value("${jwt.secret}")
    private String secretStr;

    private final ITokenService tokenService;
    private final ConfigService configService;
    private final SmsSendService smsSendService;
    private final SystemPushService pushService;
    private final EmailSendService emailSendService;
    private final SocialUserMapper socialUserMapper;
    private final SocialUserInfoService userInfoService;
    private final SocialUserInviteMapper userInviteMapper;
    private final SocialUserIntegralService userIntegralService;

    @Autowired
    public SocialUserServiceImpl(
            ITokenService tokenService, ConfigService configService, SmsSendService smsSendService,
            SystemPushService pushService, EmailSendService emailSendService, SocialUserMapper socialUserMapper,
            SocialUserInfoService userInfoService, SocialUserInviteMapper userInviteMapper,
            SocialUserIntegralService userIntegralService
    ) {
        this.tokenService = tokenService;
        this.configService = configService;
        this.smsSendService = smsSendService;
        this.pushService = pushService;
        this.emailSendService = emailSendService;
        this.socialUserMapper = socialUserMapper;
        this.userInfoService = userInfoService;
        this.userInviteMapper = userInviteMapper;
        this.userIntegralService = userIntegralService;
    }

    @Override
    @Transactional
    public String register(RegisterDto dto,HttpServletRequest request) {
        // 校验
        verifyPhone(dto.getPhone());
        verifyCode(dto.getPhone(),dto.getVerifyCode());
        if (verifyAccountExit(dto.getPhone()))  return  "该账号已存在";

        String password = Optional.ofNullable(dto.getPassword()).orElse(MyUserDetailsService.encodePassword(dto.getPassword()));
        String mobileSystem = StringUtils.getMobileSystem(request);
        String ip = StringUtils.getIp(request);
        String city = StringUtils.getCityInfo(ip);
        String code = this.genInviteCode(dto.getPhone());

        // 组装用户实体
        SocialUser socialUser = new SocialUser(
                password,dto.getPhone(),dto.getOpenId(),code, SocialUserLoginType.PASSWORD_TO_LOGIN.getType(),
                mobileSystem,ip,new Date(),city,dto.getRegisterResource()
        );

        SocialUser user = this.insertSocialUser(socialUser);

        String inviteCode = dto.getInviteCode();
        if (StringUtils.isNotBlank(inviteCode)){
            // 如果 用户携带邀请码注册 添加邀请记录
            userInviteMapper.insert(new SocialUserInvite(inviteCode,user.getId(),new Date()));
        }
        return "注册成功";
    }

    private SocialUser insertSocialUser(SocialUser socialUser){
        this.save(socialUser);
        userInfoService.saveOrUpdateSocialUserInfo(null,socialUser.getId(),socialUser.getPhone());
        String value = configService.getValueByKey(Constant.USER_REGISTER_AWARD_INTEGRAL, "10");
        BigDecimal number = new BigDecimal(value);
        userIntegralService.initUserAccount(socialUser.getId(),number,Constant.IntegralSource.register);
        return socialUser;
    }

    @Override
    public SocialUser login(SocialUserLoginProcessor processor, HttpServletRequest request) {
        if (processor.getLoginType() == SocialUserLoginType.VERIFY_CODE_TO_LOGIN.getType() ){
            if (!verifyAccountExit(processor.getAccount())) {
                this.register(new RegisterDto(processor.getAccount(),Constant.ONE,processor.getVerifyCode()),request);
            }
        }
        SocialUser socialUser = super.login(processor,request);

        // 校验 start...
        if (null == socialUser) throw new BadRequestException("该账号不存在,请重新输入");
        if (StringUtils.isNotBlank(socialUser.getPassword())) {
            String password = MyUserDetailsService.encodePassword(processor.getPassword());
            if (!StringUtils.equals(socialUser.getPassword(),password)){
                throw new BadRequestException("密码错误,请检查后重新输入");
            }
        }
        switch (socialUser.getStatus()){
            case Constant.ONE :
                throw new BadRequestException(SocialUserLoginStatus.forbidden.getMessage());
            case Constant.TWO:
                throw new BadRequestException(SocialUserLoginStatus.cancelled.getMessage());
        }

        socialUser.setLoginTime(new Date());
        socialUser.setLoginType(Constant.LoginType.APP);
        socialUser.setLastLoginIp(StringUtils.getIp(request));
        socialUser.setMobileSystem(StringUtils.getMobileSystem(request));
        this.updateById(socialUser);

        return socialUser;
    }

    @Override
    public SocialUserInfoVo toLogin(SocialUserLoginProcessor processor, HttpServletRequest request) {
        SocialUser socialUser = this.login(processor, request);

        Long userId = socialUser.getId();
        TokenVo token = tokenService.createToken(socialUser.getId());
        SocialUserInfo userInfo = userInfoService.selectUserInfoByUserId(userId);

        return SocialUserInfoVo.builder()
                .userId(socialUser.getId()).userInfo(userInfo).level(socialUser.getLevel())
                .role(socialUser.getRole()).token(token).build();
    }

    @Override
    public Map<String, Object> sendVerifyCode(String phone,int type) {
        if ( !verifyPhone(phone) ) {
            throw new BadRequestException("Incorrect format of mobile phone number");
        }
        SocialUser user = socialUserMapper.selectOneByAccount(phone);
        if (type != SMSType.register.getType() && user == null){
            throw new BadRequestException("The account does not exist");
        }

        Map<String,Object> data = new HashMap<>(2);
        SmsSendResponse response = smsSendService.sendVerifyCode(new SmsSendRequest(phone,"",type));

        data.put("code",response.getCode());
        data.put("message",response.getErrorMsg());
        return data;
    }

    @Override
    public boolean verifyCode(String phone, String code) {
        verifyPhone(phone);

        boolean exit = phone.contains("@") ? emailSendService.exitVerifyCode(phone) :
                smsSendService.exitVerifyCode(phone);
        if (!exit){
            throw new BadRequestException("验证码失效,请重新发送");
        }
        boolean verify = phone.contains("@") ? emailSendService.verifyCodeEquals(phone,code):smsSendService.verifyCodeEquals(phone,code);
        if (!verify){
            throw new BadRequestException("验证码错误,请检查后重新输入");
        }
        return true;
    }

    @Override
    public String updatePassword(PasswordDto source) {
        // 校验
        verifyPhone(source.getAccount());
        verifyCode(source.getAccount(),source.getVerifyCode());

        SocialUser user = socialUserMapper.selectOneByAccount(source.getAccount());
        if (StringUtils.isNull(user)) throw new BadRequestException("该账号不存在");

        String password = user.getPassword();
        String newPassword = MyUserDetailsService.encodePassword(source.getPassword());
        if (StringUtils.equals(password,newPassword)){
            throw new BadRequestException("新密码不能与旧密码相同");
        }

        user.setPassword(newPassword);
        this.updateById(user);

        return "修改成功";
    }

    @Override
    public void cleanCode(String account) {
        if (account.contains("@")) emailSendService.cleanVerifyCode(account);

        else if (account.length() == 11) smsSendService.cleanVerifyCode(account);
    }

    @Override
    public String logout(Long userId) {
        if (StringUtils.isNull(userId)) throw new BadRequestException("用户ID不能为空");

        tokenService.deleteToken(userId);
        pushService.cleanClientId(userId);
        return "退出登录成功";
    }

    private boolean verifyAccountExit(String account){
        return !StringUtils.isNull(socialUserMapper.selectOneByAccount(account));
    }

    private String genInviteCode(String phone){
        String code = CodeUtils.toSerialCode(Long.valueOf(phone));

        SocialUserExample example = new SocialUserExample();
        example.createCriteria().andInviteCodeEqualTo(code);
        boolean b = true;
        while (b){
            long countByExample = socialUserMapper.countByExample(example);
            if (countByExample > Constant.ZERO){
                code = CodeUtils.toSerialCode(Long.valueOf(phone));
            }else {
                b = false;
            }
        }
        return code;
    }

    private static boolean verifyPhone(String phone){
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if (phone.length() != 11) {
            throw new BadRequestException("目前只支持11位格式手机号码");
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        boolean isMatch = m.matches();

        log.info("[校验]==> 手机号:{},校验格式状态为:{}",phone,isMatch ? "校验通过" : "校验不通过");
        if (!isMatch) throw new BadRequestException("校验手机格式未通过,请检查手机号是否正确");

        return true ;
    }
}
