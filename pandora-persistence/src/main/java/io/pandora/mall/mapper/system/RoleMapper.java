package io.pandora.mall.mapper.system;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.system.Role;

public interface RoleMapper extends BaseDao<Role> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_sys_role
     *
     * @mbggenerated Wed Sep 09 16:19:59 CST 2020
     */
    int insertSelective(Role record);
}