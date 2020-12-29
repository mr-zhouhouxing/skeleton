package io.pandora.mall.mapper.social;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.social.SocialUserIntegralSource;
import io.pandora.mall.domian.social.SocialUserIntegralSourceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SocialUserIntegralSourceMapper extends BaseDao<SocialUserIntegralSource> {

    long countByExample(SocialUserIntegralSourceExample example);

    int deleteByExample(SocialUserIntegralSourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SocialUserIntegralSource record);

    List<SocialUserIntegralSource> selectByExample(SocialUserIntegralSourceExample example);

    SocialUserIntegralSource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SocialUserIntegralSource record, @Param("example") SocialUserIntegralSourceExample example);

    int updateByExample(@Param("record") SocialUserIntegralSource record, @Param("example") SocialUserIntegralSourceExample example);

    int updateByPrimaryKeySelective(SocialUserIntegralSource record);

    int updateByPrimaryKey(SocialUserIntegralSource record);
}