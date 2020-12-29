package io.pandora.mall.pojo.dto.social;

import io.pandora.mall.domian.social.SocialUserInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Created by mr_zhou on 2020/12/29
 */
@Data
@ApiModel("用户资料入参")
public class SocialUserInfoDto extends SocialUserInfo {
}
