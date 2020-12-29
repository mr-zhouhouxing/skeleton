package io.pandora.mall.mapper.social;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.social.SocialUserLoginType;
import io.pandora.mall.domian.social.SocialUserLoginTypeExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SocialUserLoginTypeMapper extends BaseDao<SocialUserLoginType> {

    long countByExample(SocialUserLoginTypeExample example);

    int deleteByExample(SocialUserLoginTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SocialUserLoginType record);

    List<SocialUserLoginType> selectByExample(SocialUserLoginTypeExample example);

    SocialUserLoginType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SocialUserLoginType record, @Param("example") SocialUserLoginTypeExample example);

    int updateByExample(@Param("record") SocialUserLoginType record, @Param("example") SocialUserLoginTypeExample example);

    int updateByPrimaryKeySelective(SocialUserLoginType record);

    int updateByPrimaryKey(SocialUserLoginType record);

    @Select("SELECT * FROM social_user_login_type WHERE type = #{type}")
    SocialUserLoginType selectOneByType(Integer type);
}