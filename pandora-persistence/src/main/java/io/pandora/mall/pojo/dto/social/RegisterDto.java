package io.pandora.mall.pojo.dto.social;

import io.pandora.mall.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户注册入参")
public class RegisterDto extends BaseDto {

    @NotBlank(message = "用户手机号不能为空")
    @ApiModelProperty("用户手机号")
    private String phone;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("注册来源:1.自行下载,2.好友分享,3.广告注册")
    private int registerResource;

    @ApiModelProperty("邀请码(选填)")
    private String inviteCode;

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty("验证码")
    private String verifyCode;

    private String openId;

    public RegisterDto( String phone,int registerResource, String verifyCode) {
        this.phone = phone;
        this.registerResource = registerResource;
        this.verifyCode = verifyCode;
    }
}

