package io.pandora.mall.mapper.social;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.social.SocialUserInvite;
import io.pandora.mall.domian.social.SocialUserInviteExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SocialUserInviteMapper extends BaseDao<SocialUserInvite> {

    long countByExample(SocialUserInviteExample example);

    int deleteByExample(SocialUserInviteExample example);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SocialUserInvite record);

    List<SocialUserInvite> selectByExample(SocialUserInviteExample example);

    SocialUserInvite selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SocialUserInvite record, @Param("example") SocialUserInviteExample example);

    int updateByExample(@Param("record") SocialUserInvite record, @Param("example") SocialUserInviteExample example);

    int updateByPrimaryKeySelective(SocialUserInvite record);

    int updateByPrimaryKey(SocialUserInvite record);
}