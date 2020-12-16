package io.pandora.mall.domian.system;

import com.baomidou.mybatisplus.annotation.TableName;
import io.pandora.mall.base.BaseDo;
import lombok.Data;

@Data
@TableName("mall_sys_user_dept")
public class UserDept extends BaseDo {

    private Integer id;

    private Integer deptId;

    private Integer roleId;

}