package io.pandora.mall.mapper.system;

import io.pandora.mall.base.BaseDao;

import java.security.Permission;
import java.util.List;
import java.util.Map;

public interface PermissionMapper extends BaseDao<Permission> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_sys_permission
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    int insertSelective(Permission record);

    /**
     * 查询角色权限
     * @param userId
     * @return
     */
    List<Map<String,String>> findPermissionByUserId(Integer userId);
}