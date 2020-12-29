package io.pandora.mall.pojo.vo.social;

import io.pandora.mall.domian.social.SocialUserInfo;
import io.pandora.mall.pojo.vo.system.TokenVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SocialUserInfoVo implements Serializable {
    @ApiModelProperty("用户编号")
    private Long userId;

    @ApiModelProperty("0.普通用户，1.普通会员，2.超级会员")
    private Integer level;

    @ApiModelProperty("账号角色")
    private Integer role;

    @ApiModelProperty("用户通行凭证")
    private TokenVo token;

    @ApiModelProperty("用户个人资料详情")
    private SocialUserInfo userInfo;
}
