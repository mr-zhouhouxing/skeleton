package io.pandora.mall.module.social;

import io.pandora.mall.annotation.AccessToken;
import io.pandora.mall.domian.social.SocialUserInfo;
import io.pandora.mall.manage.log.annotation.SysLog;
import io.pandora.mall.pojo.dto.social.SocialUserInfoDto;
import io.pandora.mall.response.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Created by mr_zhou on 2020/12/23
 */
@RestController
@Api(tags = {"社交系统 - 【用户服务】"})
@RequestMapping("/${social}/user")
public class SocialUserController {

    @Autowired
    private SocialUserInfoService socialUserInfoService;

    @AccessToken
    @SysLog("获取用户信息")
    @GetMapping("/getInfo/{userId}")
    @ApiOperation(value = "获取用户信息")
    public ResponseBean getInfo(@PathVariable("userId") Long userId){
        SocialUserInfo userInfo = socialUserInfoService.selectUserInfoByUserId(userId);
        return ResponseBean.succeed(userInfo);
    }

    @AccessToken
    @SysLog("更新个人资料")
    @PostMapping("/update")
    @ApiOperation(value = "更新个人资料")
    public ResponseBean update(@RequestBody SocialUserInfoDto dto){
        String userInfo = socialUserInfoService.updateUserInfo(dto);
        return ResponseBean.succeed(userInfo);
    }
}
