package io.pandora.mall.module.social.impl;

import com.alibaba.fastjson.JSONObject;
import io.pandora.mall.base.service.ConfigService;
import io.pandora.mall.base.service.impl.BaseServiceImpl;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.domian.social.SocialUserInfo;
import io.pandora.mall.mapper.social.SocialUserInfoMapper;
import io.pandora.mall.mapper.social.SocialUserMapper;
import io.pandora.mall.module.social.SocialUserInfoService;
import io.pandora.mall.pojo.dto.social.SocialUserInfoDto;
import io.pandora.mall.redis.util.RedisUtils;
import io.pandora.mall.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
@Slf4j
@Service
public class SocialUserInfoServiceImpl extends BaseServiceImpl<SocialUserInfoMapper, SocialUserInfo> implements SocialUserInfoService {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ConfigService configService;
    @Autowired
    private SocialUserMapper socialUserMapper;
    @Autowired
    private SocialUserInfoMapper userInfoMapper;

    @Override
    public void saveOrUpdateSocialUserInfo(SocialUserInfo userInfo,Long userId,String phone) {
        try {
            SocialUserInfo info = userInfoMapper.selectUserInfoByUserId(userId);

            if (info != null && userInfo != null) {
                BeanUtils.copyProperties(userInfo,info);
                this.updateById(info);
            }else {
                String sex = "女";
                String userName =  generateUserName(phone, userId) ;
                String portrait = randomAvatar() ;
                Integer age =  18;
                Double stature =  175.5;
                Double weight = 56.0;
                // 适配第三方登录信息
                if ( !StringUtils.isNull(userInfo) ) {
                    sex = userInfo.getSex();
                    userName = StringUtils.isNotBlank(userInfo.getUserName()) ? userInfo.getUserName() : userName;
                    portrait = StringUtils.isNotBlank(userInfo.getPortrait()) ? userInfo.getPortrait() : portrait;
                }

                SocialUserInfo bean = new SocialUserInfo(
                        userId , userName , portrait , sex , age , stature , weight,"汉族", defaultSignature()
                );

                this.save(bean);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("[用户信息] 初始化账号:{},ID:{} 个人资料出现异常:{}",phone,userId,e.getMessage());
        }
    }

    @Override
    public SocialUserInfo selectUserInfoByUserId(Long userId) {
        Object obj = redisUtils.get(Constant.USER_INFO_PREFIX + userId);

        String result = "";
        if ( StringUtils.isNull(obj)){
            SocialUserInfo info = userInfoMapper.selectUserInfoByUserId(userId);
            if (info == null) return new SocialUserInfo();

            String bean = JSONObject.toJSONString(info);
            redisUtils.set(Constant.USER_INFO_PREFIX + userId, bean ,Constant.EXPIRE_THREE_HOUR);
            result = bean;
        }else {
            result = obj.toString();
        }
        return JSONObject.parseObject(result ,SocialUserInfo.class);
    }

    @Override
    public String updateUserInfo(SocialUserInfoDto dto) {
        Long userId = dto.getUserId();
        SocialUserInfo bean = userInfoMapper.selectUserInfoByUserId(userId);
        if (StringUtils.isNull(bean)) return "更新失败,未找到用户个人资料记录,检查用户ID是否正确";

        BeanUtils.copyProperties(dto,bean);
        this.updateById(bean);

        // 更新缓存信息
        String obj = JSONObject.toJSONString(bean);
        redisUtils.set(Constant.USER_INFO_PREFIX + userId , obj ,Constant.EXPIRE_THREE_HOUR);

        return "修改个人资料成功";
    }

    /**
     * 生成默认用户名
     *
     * @param userId
     * @return
     */
    private String generateUserName(String phone,Long userId){
        String prefix = "游客_No.";
        return Optional.ofNullable(prefix + phone).orElse(prefix + userId);
    }

    /**
     * 生成默认个性签名
     *
     * @return
     */
    private String defaultSignature(){
        String defaults = "每个人都是独立的主角,都应该有各自的个性!";
        return configService.getValueByKey(Constant.USER_DEFAULT_SIGNATURE,defaults);
    }

    /**
     * 生成用户随机头像
     *
     * @return
     */
    private String randomAvatar(){
        String defaultAvatar = Constant.USER_DEFAULT_AVATAR;
        String value = configService.getValueByKey(Constant.USER_RANDOM_AVATAR, defaultAvatar);
        if (value.contains(",")) {
            String[] values = value.split(",");
            int index = (int)(Math.random() * values.length);
            defaultAvatar = values[index];
        }
        return defaultAvatar;
    }

}
