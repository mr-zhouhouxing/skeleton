package io.pandora.mall.mapper.chat;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.chat.ChatClientUser;
import io.pandora.mall.domian.chat.ChatClientUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatClientUserMapper extends BaseDao<ChatClientUser> {

    long countByExample(ChatClientUserExample example);

    int deleteByExample(ChatClientUserExample example);

    int deleteByPrimaryKey(String pkId);

    int insertSelective(ChatClientUser record);

    List<ChatClientUser> selectByExample(ChatClientUserExample example);

    ChatClientUser selectByPrimaryKey(String pkId);

    int updateByExampleSelective(@Param("record") ChatClientUser record, @Param("example") ChatClientUserExample example);

    int updateByExample(@Param("record") ChatClientUser record, @Param("example") ChatClientUserExample example);

    int updateByPrimaryKeySelective(ChatClientUser record);

    int updateByPrimaryKey(ChatClientUser record);
}