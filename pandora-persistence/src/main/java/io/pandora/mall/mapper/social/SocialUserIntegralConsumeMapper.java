package io.pandora.mall.mapper.social;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.social.SocialUserIntegralConsume;
import io.pandora.mall.domian.social.SocialUserIntegralConsumeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SocialUserIntegralConsumeMapper extends BaseDao<SocialUserIntegralConsume> {

    long countByExample(SocialUserIntegralConsumeExample example);

    int deleteByExample(SocialUserIntegralConsumeExample example);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SocialUserIntegralConsume record);

    List<SocialUserIntegralConsume> selectByExample(SocialUserIntegralConsumeExample example);

    SocialUserIntegralConsume selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SocialUserIntegralConsume record, @Param("example") SocialUserIntegralConsumeExample example);

    int updateByExample(@Param("record") SocialUserIntegralConsume record, @Param("example") SocialUserIntegralConsumeExample example);

    int updateByPrimaryKeySelective(SocialUserIntegralConsume record);

    int updateByPrimaryKey(SocialUserIntegralConsume record);
}