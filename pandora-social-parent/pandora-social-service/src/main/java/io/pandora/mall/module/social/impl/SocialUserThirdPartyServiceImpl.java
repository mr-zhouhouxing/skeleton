package io.pandora.mall.module.social.impl;

import io.pandora.mall.base.service.impl.BaseServiceImpl;
import io.pandora.mall.domian.social.SocialUser;
import io.pandora.mall.domian.social.SocialUserThirdparty;
import io.pandora.mall.mapper.social.SocialUserThirdpartyMapper;
import io.pandora.mall.module.social.SocialUserThirdPartyService;
import io.pandora.mall.module.social.processor.user.ThirdPartyUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
@Slf4j
@Service
public class SocialUserThirdPartyServiceImpl extends BaseServiceImpl<SocialUserThirdpartyMapper, SocialUserThirdparty> implements SocialUserThirdPartyService {


    @Override
    public SocialUser insertThirdPartyUser(ThirdPartyUser param, String password) throws Exception {
        return null;
    }

}
