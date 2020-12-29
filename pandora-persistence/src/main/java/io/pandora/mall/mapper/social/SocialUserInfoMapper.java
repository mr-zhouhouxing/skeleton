package io.pandora.mall.mapper.social;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.social.SocialUserInfo;
import io.pandora.mall.domian.social.SocialUserInfoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SocialUserInfoMapper extends BaseDao<SocialUserInfo> {

    long countByExample(SocialUserInfoExample example);

    int deleteByExample(SocialUserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SocialUserInfo record);

    List<SocialUserInfo> selectByExample(SocialUserInfoExample example);

    SocialUserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SocialUserInfo record, @Param("example") SocialUserInfoExample example);

    int updateByExample(@Param("record") SocialUserInfo record, @Param("example") SocialUserInfoExample example);

    int updateByPrimaryKeySelective(SocialUserInfo record);

    int updateByPrimaryKey(SocialUserInfo record);

    @Select("SELECT * FROM social_user_info WHERE user_id = #{userId}")
    SocialUserInfo selectUserInfoByUserId(Long userId);
}