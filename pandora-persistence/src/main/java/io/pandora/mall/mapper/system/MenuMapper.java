package io.pandora.mall.mapper.system;

import io.pandora.mall.base.BaseDao;
import io.pandora.mall.domian.system.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper extends BaseDao<Menu> {
    /**
     * 根据 父ID查询菜单
     * @param pid
     * @return
     */
    @Select("SELECT * FROM mall_sys_menu WHERE pid = #{pid}")
    List<Menu> findByPid(@Param("pid") Integer pid);

    /**
     * 根据 角色ID 查询 菜单ID集合
     * @param roleId
     * @return
     */
    @Select("SELECT menu_id FROM mall_sys_role_menu WHERE role_id = #{roleId}")
    List<Integer> findMenuIdsByRoleId(Integer roleId);

}