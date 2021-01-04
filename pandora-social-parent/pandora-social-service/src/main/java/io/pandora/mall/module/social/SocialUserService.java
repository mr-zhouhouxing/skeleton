package io.pandora.mall.module.social;

import io.pandora.mall.base.service.BaseService;
import io.pandora.mall.domian.social.SocialUser;
import io.pandora.mall.module.social.processor.user.SocialUserLoginProcessor;
import io.pandora.mall.pojo.dto.social.PasswordDto;
import io.pandora.mall.pojo.dto.social.RegisterDto;
import io.pandora.mall.pojo.vo.social.SocialUserInfoVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
public interface SocialUserService extends BaseService<SocialUser> {

    String register(RegisterDto dto,HttpServletRequest request);

    SocialUser login(SocialUserLoginProcessor processor, HttpServletRequest request);

    SocialUserInfoVo toLogin(SocialUserLoginProcessor processor, HttpServletRequest request);

    Map<String, Object> sendVerifyCode(String phone,int type);

    boolean verifyCode(String phone, String code);

    String updatePassword(PasswordDto source);

    void cleanCode(String account);

    String logout(Long userId);
}