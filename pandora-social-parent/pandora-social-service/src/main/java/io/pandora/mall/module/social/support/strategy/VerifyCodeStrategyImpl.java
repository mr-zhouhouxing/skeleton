package io.pandora.mall.module.social.support.strategy;

import io.pandora.mall.domian.social.SocialUser;
import io.pandora.mall.mapper.social.SocialUserMapper;
import io.pandora.mall.module.social.SocialUserService;
import io.pandora.mall.module.social.processor.user.SocialUserLoginProcessor;
import io.pandora.mall.module.social.support.UserLoginStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
@Slf4j
@Component("verifyCodeStrategy")
public class VerifyCodeStrategyImpl implements UserLoginStrategy {

    @Autowired
    private SocialUserService socialUserService;
    @Autowired
    private SocialUserMapper socialUserMapper;

    @Override
    public SocialUser login(SocialUserLoginProcessor processor, HttpServletRequest request) {
        // 校验验证码
        socialUserService.verifyCode(processor.getAccount(), processor.getVerifyCode());
        return socialUserMapper.selectOneByAccount(processor.getAccount());
    }

}
