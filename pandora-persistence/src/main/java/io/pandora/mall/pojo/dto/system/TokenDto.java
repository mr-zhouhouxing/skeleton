package io.pandora.mall.pojo.dto.system;

import io.pandora.mall.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Created by mr_zhou on 2020/12/16
 */
@Data
@ApiModel("Token入参")
public class TokenDto extends BaseDto {
    
    @ApiModelProperty("用户ID/APPID")
    private long id;

    @ApiModelProperty("密钥[空则走默认密钥]")
    private String secret;

    @Override
    public String toString() {
        return "TokenDto{ id =" + id + ", secret =" + secret + "}";
    }
}
