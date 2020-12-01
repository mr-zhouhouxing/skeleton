package io.pandora.mall.module.security;

import io.pandora.mall.module.log.annotation.SysLog;
import io.pandora.mall.pojo.dto.admin.LoginDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by John on 2020/9/11
 */
@RestController
@Api(tags = {"登录授权-API"})
public class AuthController {

    @SysLog("用户登录")
    @ApiOperation("登录")
    @PostMapping("/v1/user/login")
    public void login(@RequestBody LoginDto dto){}

}
