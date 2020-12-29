package io.pandora.mall.mapper.social;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.social.SocialUser;
import io.pandora.mall.domian.social.SocialUserExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SocialUserMapper extends BaseDao<SocialUser> {

    long countByExample(SocialUserExample example);

    int deleteByExample(SocialUserExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(SocialUser record);

    List<SocialUser> selectByExample(SocialUserExample example);

    SocialUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SocialUser record, @Param("example") SocialUserExample example);

    int updateByExample(@Param("record") SocialUser record, @Param("example") SocialUserExample example);

    int updateByPrimaryKeySelective(SocialUser record);

    int updateByPrimaryKey(SocialUser record);

    @Select("SELECT * FROM social_user WHERE phone = #{account}")
    SocialUser selectOneByAccount(String account);
}