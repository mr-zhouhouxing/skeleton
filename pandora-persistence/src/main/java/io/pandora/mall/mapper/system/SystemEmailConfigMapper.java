package io.pandora.mall.mapper.system;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.system.SystemEmailConfig;
import io.pandora.mall.domian.system.SystemEmailConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemEmailConfigMapper extends BaseDao<SystemEmailConfig> {

    long countByExample(SystemEmailConfigExample example);

    int deleteByExample(SystemEmailConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SystemEmailConfig record);

    List<SystemEmailConfig> selectByExample(SystemEmailConfigExample example);

    SystemEmailConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemEmailConfig record, @Param("example") SystemEmailConfigExample example);

    int updateByExample(@Param("record") SystemEmailConfig record, @Param("example") SystemEmailConfigExample example);

    int updateByPrimaryKeySelective(SystemEmailConfig record);

    int updateByPrimaryKey(SystemEmailConfig record);
}