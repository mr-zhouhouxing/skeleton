package io.pandora.mall.module.social.impl;

import io.pandora.mall.base.service.impl.BaseServiceImpl;
import io.pandora.mall.domian.social.SocialUserInfo;
import io.pandora.mall.mapper.social.SocialUserInfoMapper;
import io.pandora.mall.module.social.SocialUserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
@Service
public class SocialUserInfoServiceImpl extends BaseServiceImpl<SocialUserInfoMapper, SocialUserInfo> implements SocialUserInfoService {
}
