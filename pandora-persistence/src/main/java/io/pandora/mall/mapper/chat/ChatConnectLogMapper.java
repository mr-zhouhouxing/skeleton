package io.pandora.mall.mapper.chat;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.chat.ChatConnectLog;
import io.pandora.mall.domian.chat.ChatConnectLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatConnectLogMapper extends BaseDao<ChatConnectLog> {

    long countByExample(ChatConnectLogExample example);

    int deleteByExample(ChatConnectLogExample example);

    int deleteByPrimaryKey(String pkId);

    int insertSelective(ChatConnectLog record);

    List<ChatConnectLog> selectByExample(ChatConnectLogExample example);

    ChatConnectLog selectByPrimaryKey(String pkId);

    int updateByExampleSelective(@Param("record") ChatConnectLog record, @Param("example") ChatConnectLogExample example);

    int updateByExample(@Param("record") ChatConnectLog record, @Param("example") ChatConnectLogExample example);

    int updateByPrimaryKeySelective(ChatConnectLog record);

    int updateByPrimaryKey(ChatConnectLog record);
}