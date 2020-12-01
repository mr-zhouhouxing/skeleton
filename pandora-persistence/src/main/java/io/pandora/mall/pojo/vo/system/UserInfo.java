package io.pandora.mall.pojo.vo.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Created by John on 2020/9/11
 */
@Data
public class UserInfo implements Serializable {
    @ApiModelProperty(name = "id",value = "用户ID")
    private Integer id;

    @ApiModelProperty(name = "account",value = "帐号")
    private String account;

    @ApiModelProperty(name = "nickName",value = "昵称")
    private String nickName;

    @ApiModelProperty(name = "avatear",value = "头像")
    private String avatear;

    @ApiModelProperty(name = "email",value = "邮箱")
    private String email;

    @ApiModelProperty(name = "deptName",value = "部门名称")
    private String deptName;

    @ApiModelProperty(name = "jobName",value = "岗位名称")
    private String jobName;

    @ApiModelProperty(name = "sex",value = "性别")
    private String sex;

    @ApiModelProperty(name = "token",value = "token")
    private String token;

    @ApiModelProperty(name = "roles",value = "角色")
    private String roles;

    @ApiModelProperty(name = "roleId",value = "角色ID")
    private Integer roleId;
}
