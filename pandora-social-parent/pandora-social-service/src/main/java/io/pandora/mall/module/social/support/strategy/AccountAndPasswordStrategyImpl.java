package io.pandora.mall.module.social.support.strategy;

import io.pandora.mall.domian.social.SocialUser;
import io.pandora.mall.module.social.processor.SocialUserLoginProcessor;
import io.pandora.mall.module.social.support.UserLoginStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 账号密码登录策略实现
 *
 * @author Created by mr_zhou on 2020/12/24
 */
@Slf4j
@Component("accountAndPasswordStrategy")
public class AccountAndPasswordStrategyImpl implements UserLoginStrategy {

    @Override
    public SocialUser login(SocialUserLoginProcessor processor, HttpServletRequest request) {
        return null;
    }
}
