package io.pandora.mall.module.social;

import io.pandora.mall.base.service.BaseService;
import io.pandora.mall.domian.social.SocialUserInfo;
import io.pandora.mall.pojo.dto.social.SocialUserInfoDto;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
public interface SocialUserInfoService extends BaseService<SocialUserInfo> {

    /**
     * 保存或更新
     *
     * @param userInfo
     * @param userId
     * @param phone
     */
    void saveOrUpdateSocialUserInfo(SocialUserInfo userInfo,Long userId,String phone);

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId
     * @return
     */
    SocialUserInfo selectUserInfoByUserId(Long userId);

    /**
     * 更新个人资料
     *
     * @return
     */
    String updateUserInfo(SocialUserInfoDto dto);
}
