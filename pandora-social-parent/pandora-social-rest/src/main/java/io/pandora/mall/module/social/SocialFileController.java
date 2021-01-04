package io.pandora.mall.module.social;

import io.pandora.mall.annotation.AccessToken;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.enume.CodeEnum;
import io.pandora.mall.manage.log.annotation.SysLog;
import io.pandora.mall.oss.service.SystemFileService;
import io.pandora.mall.response.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 * @author Created by mr_zhou on 2020/12/30
 */
@RestController
@Api(tags = {"社交系统 - 【文件服务】"})
@RequestMapping("/${social}/file")
public class SocialFileController {

    @Autowired
    private SystemFileService fileService;

    @AccessToken
    @SysLog("上传头像")
    @PostMapping("/upload/avatar")
    @ApiOperation(value = "上传头像")
    public ResponseBean uploadAvatar(@RequestParam("file") MultipartFile file){
        String url = "";
        try {
            url = fileService.upload(file.getBytes(),file.getOriginalFilename(), Constant.File.USER_INFO_IMAGE);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseBean.failCodeAndMsg(CodeEnum.ERROR.getCode(),"上传文件失败,文件字节流获取异常");
        }
        return ResponseBean.succeed(url);
    }

}
