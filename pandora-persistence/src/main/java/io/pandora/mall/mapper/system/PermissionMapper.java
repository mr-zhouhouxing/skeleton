package io.pandora.mall.mapper.system;

import io.pandora.mall.base.BaseDao;

import java.security.Permission;
import java.util.List;
import java.util.Map;

public interface PermissionMapper extends BaseDao<Permission> {
    /**
     * 查询角色权限
     * @param userId
     * @return
     */
    List<Map<String,String>> findPermissionByUserId(Integer userId);
}