package io.pandora.mall.module.social;

import io.pandora.mall.domian.social.SocialUserInfo;
import io.pandora.mall.manage.log.annotation.SysLog;
import io.pandora.mall.pojo.dto.social.SocialUserInfoDto;
import io.pandora.mall.response.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Created by mr_zhou on 2020/12/23
 */
@RestController
@Api(tags = {"社交系统:用户服务"})
@RequestMapping("/${social}/user")
public class SocialUserController {

    private final SocialUserInfoService socialUserInfoService;

    public SocialUserController(SocialUserInfoService socialUserInfoService) {
        this.socialUserInfoService = socialUserInfoService;
    }

    @SysLog("获取用户信息")
    @GetMapping("/getInfo/{userId}")
    @ApiOperation(value = "获取用户信息")
    public ResponseBean getInfo(@PathVariable("userId") Long userId){
        SocialUserInfo userInfo = socialUserInfoService.selectUserInfoByUserId(userId);
        return ResponseBean.succeed(userInfo);
    }

    @SysLog("更新个人资料")
    @PostMapping("/update")
    @ApiOperation(value = "更新个人资料")
    public ResponseBean update(@RequestBody SocialUserInfoDto dto){
        String userInfo = socialUserInfoService.updateUserInfo(dto);
        return ResponseBean.succeed(userInfo);
    }

    @SysLog("上传头像")
    @PostMapping("/upload/avatar")
    @ApiOperation(value = "上传头像")
    public ResponseBean uploadAvatar(@RequestParam("file") MultipartFile file){
    //  String userInfo = socialUserInfoService.updateUserInfo(dto);
        return ResponseBean.succeed("");
    }

}
