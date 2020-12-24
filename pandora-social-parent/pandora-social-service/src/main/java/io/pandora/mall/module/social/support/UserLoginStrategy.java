package io.pandora.mall.module.social.support;

import io.pandora.mall.domian.social.SocialUser;
import io.pandora.mall.module.social.processor.SocialUserLoginProcessor;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
public interface UserLoginStrategy {

    SocialUser login(SocialUserLoginProcessor processor, HttpServletRequest request);

}
