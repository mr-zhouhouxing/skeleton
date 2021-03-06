package io.pandora.mall.mapper.system;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.system.Config;
import io.pandora.mall.domian.system.ConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConfigMapper extends BaseDao<Config> {

    int countByExample(ConfigExample example);

    int deleteByExample(ConfigExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(Config record);

    List<Config> selectByExampleWithBLOBs(ConfigExample example);

    List<Config> selectByExample(ConfigExample example);

    Config selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Config record, @Param("example") ConfigExample example);

    int updateByExampleWithBLOBs(@Param("record") Config record, @Param("example") ConfigExample example);

    int updateByExample(@Param("record") Config record, @Param("example") ConfigExample example);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKeyWithBLOBs(Config record);

    int updateByPrimaryKey(Config record);
}