package io.pandora.mall.module.social;

import io.pandora.mall.base.service.BaseService;
import io.pandora.mall.domian.social.SocialUser;
import io.pandora.mall.domian.social.SocialUserInfo;
import io.pandora.mall.module.social.processor.SocialUserLoginProcessor;
import io.pandora.mall.pojo.dto.social.RegisterDto;
import io.pandora.mall.pojo.vo.social.RegisterVo;
import io.pandora.mall.pojo.vo.social.SocialUserInfoVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
public interface SocialUserService extends BaseService<SocialUser> {

    RegisterVo register(RegisterDto dto, int regType);

    SocialUser login(SocialUserLoginProcessor processor, HttpServletRequest request);

    SocialUserInfoVo toLogin(SocialUserLoginProcessor processor, HttpServletRequest request);

    Map<String, Object> sendVerifyCode(String phone);

    boolean verifyCode(String phone, String code);

}