package io.pandora.mall.mapper.social;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.social.SocialUserThirdparty;
import io.pandora.mall.domian.social.SocialUserThirdpartyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SocialUserThirdpartyMapper extends BaseDao<SocialUserThirdparty> {

    long countByExample(SocialUserThirdpartyExample example);

    int deleteByExample(SocialUserThirdpartyExample example);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SocialUserThirdparty record);

    List<SocialUserThirdparty> selectByExample(SocialUserThirdpartyExample example);

    SocialUserThirdparty selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SocialUserThirdparty record, @Param("example") SocialUserThirdpartyExample example);

    int updateByExample(@Param("record") SocialUserThirdparty record, @Param("example") SocialUserThirdpartyExample example);

    int updateByPrimaryKeySelective(SocialUserThirdparty record);

    int updateByPrimaryKey(SocialUserThirdparty record);
}