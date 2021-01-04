package io.pandora.mall.mapper.system;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.system.SystemPushClient;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Created by mr_zhou on 2021/1/4
 */
public interface SystemPushClientMapper extends BaseDao<SystemPushClient> {

    @Select("SELECT client_id 'clientId' FROM system_push_client WHERE user_id = #{userId}")
    String getClientIdByUserId(Long userId);

    @Update("UPDATE system_push_client SET client_id = #{clientId} WHERE user_id = #{userId}")
    String updateClientId(Long userId,String clientId);
}
