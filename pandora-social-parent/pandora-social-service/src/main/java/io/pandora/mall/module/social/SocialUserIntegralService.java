package io.pandora.mall.module.social;

import io.pandora.mall.base.service.BaseService;
import io.pandora.mall.domian.social.SocialUserIntegral;
import io.pandora.mall.module.social.processor.integral.UserIntegralProcessor;

import java.math.BigDecimal;

/**
 * @author Created by mr_zhou on 2020/12/29
 */
public interface SocialUserIntegralService extends BaseService<SocialUserIntegral> {

    /**
     * 初始化用户积分账号
     *
     * @param userId
     * @param number
     * @param resource
     */
    void initUserAccount(Long userId, BigDecimal number,int resource);

    /**
     * 增加积分
     *
     * @param processor
     */
    void addUserIntegral(UserIntegralProcessor processor);

    /**
     * 减少积分
     *
     * @param processor
     */
    void decreaseUserIntegral(UserIntegralProcessor processor);
}
