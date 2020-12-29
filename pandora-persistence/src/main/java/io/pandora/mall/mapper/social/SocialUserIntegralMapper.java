package io.pandora.mall.mapper.social;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.social.SocialUserIntegral;
import io.pandora.mall.domian.social.SocialUserIntegralExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SocialUserIntegralMapper extends BaseDao<SocialUserIntegral> {

    long countByExample(SocialUserIntegralExample example);

    int deleteByExample(SocialUserIntegralExample example);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SocialUserIntegral record);

    List<SocialUserIntegral> selectByExample(SocialUserIntegralExample example);

    SocialUserIntegral selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SocialUserIntegral record, @Param("example") SocialUserIntegralExample example);

    int updateByExample(@Param("record") SocialUserIntegral record, @Param("example") SocialUserIntegralExample example);

    int updateByPrimaryKeySelective(SocialUserIntegral record);

    int updateByPrimaryKey(SocialUserIntegral record);

    int increaseUserIntegral(Long userId, BigDecimal number);

    int decreaseUserIntegral(Long userId, BigDecimal number);
}