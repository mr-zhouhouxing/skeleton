package io.pandora.mall.mapper.system;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.system.SystemPushRecord;
import io.pandora.mall.domian.system.SystemPushRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemPushRecordMapper extends BaseDao<SystemPushRecord> {

    long countByExample(SystemPushRecordExample example);

    int deleteByExample(SystemPushRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(SystemPushRecord record);

    List<SystemPushRecord> selectByExample(SystemPushRecordExample example);

    SystemPushRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SystemPushRecord record, @Param("example") SystemPushRecordExample example);

    int updateByExample(@Param("record") SystemPushRecord record, @Param("example") SystemPushRecordExample example);

    int updateByPrimaryKeySelective(SystemPushRecord record);

    int updateByPrimaryKey(SystemPushRecord record);
}