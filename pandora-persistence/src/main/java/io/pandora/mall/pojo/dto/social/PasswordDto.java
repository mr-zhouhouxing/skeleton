package io.pandora.mall.pojo.dto.social;

import io.pandora.mall.base.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Created by mr_zhou on 2020/12/29
 */
@Data
public class PasswordDto extends BaseDto {

    @NotBlank(message = "账号不能为空")
    @ApiModelProperty("账号手机号码")
    private String account;

    @NotBlank(message = "新密码不能为空")
    @ApiModelProperty("新密码")
    private String password;

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty("验证码")
    private String verifyCode;
}
