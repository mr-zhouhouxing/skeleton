package io.pandora.mall.module.social.support;

import io.pandora.mall.base.service.BaseService;
import io.pandora.mall.domian.social.SocialUser;
import io.pandora.mall.module.social.processor.user.SocialUserLoginProcessor;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
public interface UserLoginService<T> extends BaseService<T> {

    SocialUser login(SocialUserLoginProcessor processor, HttpServletRequest request);

}
