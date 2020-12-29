package io.pandora.mall.module.social.support;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.pandora.mall.base.service.BaseService;
import io.pandora.mall.base.service.impl.BaseServiceImpl;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.domian.social.SocialUser;
import io.pandora.mall.exception.BadRequestException;
import io.pandora.mall.exception.CustomException;
import io.pandora.mall.mapper.social.SocialUserLoginTypeMapper;
import io.pandora.mall.module.social.enume.SocialUserLoginType;
import io.pandora.mall.module.social.processor.user.SocialUserLoginProcessor;
import io.pandora.mall.util.StringUtils;
import io.pandora.mall.util.spring.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
@Service
public abstract class AbstractUserService<M extends BaseMapper<T>, T> extends BaseServiceImpl<M, T>
        implements BaseService<T>, UserLoginService<T> {

    @Autowired
    private SocialUserLoginTypeMapper userLoginTypeMapper;

    @Override
    public SocialUser login(SocialUserLoginProcessor processor, HttpServletRequest request) {
        io.pandora.mall.domian.social.SocialUserLoginType loginType = userLoginTypeMapper.selectOneByType(processor.getLoginType());
        if (loginType == null || loginType.getStatus() != Constant.ONE){
            throw new BadRequestException("该登录方式已被停用,请选择其他登录方式或联系客服咨询停用原因");
        }

        UserLoginStrategy payStrategy = getUserLoginStrategy(processor.getLoginType());
        return payStrategy.login(processor,request);
    }

    private UserLoginStrategy getUserLoginStrategy(Integer loginType){
        String beanId = SocialUserLoginType.getBean(loginType);
        if (StringUtils.isEmpty(beanId)) {
            throw new CustomException("未获取到登录策略["+ loginType+ "]对应BEAN");
        }
        return SpringContextHolder.getBean(beanId, UserLoginStrategy.class);
    }

}
