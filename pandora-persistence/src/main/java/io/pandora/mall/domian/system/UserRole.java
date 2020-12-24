package io.pandora.mall.domian.system;

import com.baomidou.mybatisplus.annotation.TableName;
import io.pandora.mall.base.BaseDo;

@TableName("mall_sys_user_role")
public class UserRole extends BaseDo {

    private Integer id;

    private Integer userId;

    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}