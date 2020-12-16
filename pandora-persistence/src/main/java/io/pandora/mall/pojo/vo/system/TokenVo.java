package io.pandora.mall.pojo.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Created by mr_zhou on 2020/12/16
 */
@Data
@ApiModel("Token出参")
public class TokenVo implements Serializable {
    @ApiModelProperty("过期时间")
    private long expire;
    @ApiModelProperty("accessToken")
    private String accessToken;

    @Override
    public String toString() {
        return "TokenVo{ expire=" + expire + ", accessToken='" + accessToken + "}";
    }
}
