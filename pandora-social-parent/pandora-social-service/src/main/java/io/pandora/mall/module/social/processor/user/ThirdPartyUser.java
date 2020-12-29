package io.pandora.mall.module.social.processor.user;

import lombok.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 第三方用户实体类
 * @author mr_zhou
 * @version 2019年7月26日 下午3:26:23
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPartyUser implements Serializable {
	// 用户
	private String account;
	// 用户昵称
	private String username;
	// 用户头像地址
	private String avatarUrl;
	// 用户性别
	private String gender;
	// 用户认证
	private String token;
	// 用户第三方id
	private String openid;
	// 用户类型
	private String provider;
	// 用户id
	private Integer userId;

	private HttpServletRequest request;
}
