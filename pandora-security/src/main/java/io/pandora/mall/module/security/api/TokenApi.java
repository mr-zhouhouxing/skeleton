package io.pandora.mall.module.security.api;

import io.pandora.mall.constant.Constant;
import io.pandora.mall.exception.TokenException;
import io.pandora.mall.manage.log.annotation.SysLog;
import io.pandora.mall.module.security.service.ITokenService;
import io.pandora.mall.pojo.dto.system.TokenDto;
import io.pandora.mall.pojo.vo.system.TokenVo;
import io.pandora.mall.response.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author Created by mr_zhou on 2020/12/15
 */
@RestController
@RequestMapping("/token")
@Api(tags = {"系统服务 - 【系统授权】"})
public class TokenApi {

    private final ITokenService tokenService;

    public TokenApi(ITokenService tokenService) {
        this.tokenService = tokenService;
    }

    @SysLog("获取Token")
    @PostMapping("/getToken")
    @ApiOperation(value = "获取Token",notes = "根据用户ID/APP_ID + 密钥获取Token")
    public ResponseBean getToken(@RequestBody TokenDto dto) throws TokenException {
        String token = tokenService.createToken(dto.getId(),dto.getSecret());
        TokenVo vo = new TokenVo();
        vo.setAccessToken(token);
        vo.setExpire(Constant.ACCESS_TOKEN_EXPIRE_TIME / 1000);

        return ResponseBean.succeed(vo);
    }

    @SysLog("刷新Token")
    @GetMapping("/refreshToken")
    @ApiOperation(value = "刷新Token",notes = "根据用户ID/APP_ID + 密钥获取Token")
    @ApiImplicitParam(name = "accessToken",value = "Token",dataType = "String",required = true,paramType = "query")
    public ResponseBean refreshToken(String accessToken) throws TokenException {
        tokenService.refreshToken(accessToken);
        TokenVo vo = new TokenVo();
        vo.setAccessToken(accessToken);
        vo.setExpire(Constant.ACCESS_TOKEN_EXPIRE_TIME / 1000);

        return ResponseBean.succeed(vo);
    }

}
