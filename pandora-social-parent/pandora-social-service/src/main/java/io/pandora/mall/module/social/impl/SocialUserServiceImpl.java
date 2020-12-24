package io.pandora.mall.module.social.impl;

import io.pandora.mall.constant.Constant;
import io.pandora.mall.domian.social.SocialUser;
import io.pandora.mall.exception.BadRequestException;
import io.pandora.mall.mapper.social.SocialUserMapper;
import io.pandora.mall.module.security.MyUserDetailsService;
import io.pandora.mall.module.security.service.ITokenService;
import io.pandora.mall.module.social.SocialUserService;
import io.pandora.mall.module.social.enume.SocialUserLoginStatus;
import io.pandora.mall.module.social.processor.SocialUserLoginProcessor;
import io.pandora.mall.module.social.support.AbstractUserService;
import io.pandora.mall.pojo.dto.social.RegisterDto;
import io.pandora.mall.pojo.vo.social.RegisterVo;
import io.pandora.mall.pojo.vo.social.SocialUserInfoVo;
import io.pandora.mall.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
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
    @Autowired
    private ITokenService tokenService;
    @Autowired
    private SocialUserMapper socialUserMapper;

    @Override
    public RegisterVo register(RegisterDto dto, int regType) {
        if (!verifyPhone(dto.getPhone())){
            throw new BadRequestException("校验手机格式未通过,请检查手机号是否正确");
        }

        return null;
    }

    @Override
    public SocialUser login(SocialUserLoginProcessor processor, HttpServletRequest request) {
        // 调用父类方法 寻找具体登录策略
        SocialUser socialUser = super.login(processor,request);
        // 校验开始
        if (null == socialUser) {
            throw new BadRequestException("该账号不存在,请重新输入");
        }
        String password = MyUserDetailsService.encodePassword(processor.getPassword());
        if (!StringUtils.equals(socialUser.getPassword(),password)){
            throw new BadRequestException("密码错误,请检查后重新输入");
        }
        // 校验账号状态
        switch (socialUser.getStatus()){
            case 1 : throw new BadRequestException(SocialUserLoginStatus.forbidden.getMessage());
            case 2: throw new BadRequestException(SocialUserLoginStatus.cancelled.getMessage());
        }
        // 更新
        socialUser.setLoginTime(new Date());
        socialUser.setLoginType(Constant.LoginType.app);
        socialUser.setLastLoginIp(StringUtils.getIp(request));
        socialUser.setMobileSystem(StringUtils.getMobileSystem(request));
        this.updateById(socialUser);

        return socialUser;
    }

    @Override
    public SocialUserInfoVo toLogin(SocialUserLoginProcessor processor, HttpServletRequest request) {
        SocialUser socialUser = this.login(processor, request);
        String token = tokenService.createToken(socialUser.getId(), secretStr);

        return null;
    }

    @Override
    public Map<String, Object> sendVerifyCode(String phone) {


        return null;
    }

    @Override
    public boolean verifyCode(String phone, String code) {


        return false;
    }

    /**
     * 校验手机号码
     *
     * @param phone 176-0000-0000
     * @return
     */
    private static boolean verifyPhone(String phone){
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if (phone.length() != 11) {
            throw new BadRequestException("目前只支持11位格式手机号码");
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        boolean isMatch = m.matches();
        if (isMatch) return true;

        log.warn("[校验]==> 手机号:{},校验格式未通过",phone);
        return false;
    }
}
