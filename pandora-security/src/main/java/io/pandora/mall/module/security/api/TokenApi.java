package io.pandora.mall.module.security.api;

import io.pandora.mall.manage.log.annotation.SysLog;
import io.pandora.mall.module.security.service.ITokenService;
import io.pandora.mall.response.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by mr_zhou on 2020/12/15
 */
@RestController
@RequestMapping("/${api}/token")
@Api(tags = {"系统服务:Token服务"})
public class TokenApi {

    private final ITokenService tokenService;

    public TokenApi(ITokenService tokenService) {
        this.tokenService = tokenService;
    }

    @SysLog("创建Token")
    @GetMapping("/createToken")
    @ApiOperation(value = "创建Token",notes = "根据用户ID/APPID + 密钥创建Token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",dataType = "long",required = true,paramType = "query"),
            @ApiImplicitParam(name = "secret",value = "secret",dataType = "String",required = true,paramType = "query")
    })
    public ResponseBean createToken(long id,String secret){
        String token = tokenService.createToken(id, secret);
        return ResponseBean.succeed(token);
    }



}
