package io.pandora.mall.module.social;

import io.pandora.mall.base.service.BaseService;
import io.pandora.mall.domian.social.SocialUser;
import io.pandora.mall.domian.social.SocialUserThirdparty;
import io.pandora.mall.module.social.processor.ThirdPartyUser;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
public interface SocialUserThirdpartyService extends BaseService<SocialUserThirdparty> {

    SocialUser insertThirdPartyUser(ThirdPartyUser param, String password) throws Exception;

}
