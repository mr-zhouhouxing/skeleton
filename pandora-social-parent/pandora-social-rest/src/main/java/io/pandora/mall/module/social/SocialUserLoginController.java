package io.pandora.mall.module.social;

import io.pandora.mall.annotation.ValidationParam;
import io.pandora.mall.manage.log.annotation.SysLog;
import io.pandora.mall.module.social.enume.SocialUserLoginType;
import io.pandora.mall.module.social.processor.SocialUserLoginProcessor;
import io.pandora.mall.module.social.processor.ThirdPartyLoginHelper;
import io.pandora.mall.pojo.dto.admin.LoginDto;
import io.pandora.mall.pojo.dto.social.RegisterDto;
import io.pandora.mall.pojo.vo.social.SocialUserInfoVo;
import io.pandora.mall.response.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
@RestController
@Api(tags = {"社交系统:登录注册"})
@RequestMapping("/${social}/sso")
public class SocialUserLoginController {

    @Autowired
    private SocialUserService socialUserService;

    @SysLog("用户注册")
    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public ResponseBean register(@RequestBody @Valid RegisterDto dto, HttpServletRequest request){

        return ResponseBean.succeed("");
    }

    @SysLog("验证码注册并登录")
    @PostMapping("/registerAndLogin")
    @ApiOperation(value = "用户注册")
    public ResponseBean registerAndLogin(@RequestBody @Valid RegisterDto dto, HttpServletRequest request){
        SocialUserLoginProcessor processor = SocialUserLoginProcessor.builder()
                .account(dto.getPhone())
                .verifyCode(dto.getVerifyCode())
                .loginType(SocialUserLoginType.VERIFY_CODE_TO_LOGIN.getType())
                .build();
        SocialUserInfoVo userInfo = socialUserService.toLogin(processor, request);
        return ResponseBean.succeed(userInfo);
    }

    @SysLog("账号密码登录")
    @PostMapping("/login")
    @ApiOperation(value = "账号密码登录")
    public ResponseBean login(@RequestBody @Valid LoginDto dto, HttpServletRequest request){
        SocialUserLoginProcessor processor = SocialUserLoginProcessor.builder()
                .account(dto.getAccount())
                .password(dto.getPassword())
                .loginType(SocialUserLoginType.PASSWORD_TO_LOGIN.getType())
                .build();
        SocialUserInfoVo userInfo = socialUserService.toLogin(processor, request);
        return ResponseBean.succeed(userInfo);
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @param type
     */
    @SysLog("第三方用户登录")
    @GetMapping("/login")
    @ApiOperation(value = "第三方用户登录")
    public void thirdLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam("type") int type) {
        //拼接第三方登录授权地址
        String url = ThirdPartyLoginHelper.getRedirectUrl(request.getHeader("host"), type);
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SysLog("发送验证码")
    @GetMapping("/code/send")
    @ApiOperation(value = "发送验证码")
    public ResponseBean sendVerifyCode(@ValidationParam("phone") String phone ){

        return ResponseBean.succeed("");
    }

    @SysLog("校验验证码")
    @GetMapping("/code/verify")
    @ApiOperation(value = "校验验证码")
    public ResponseBean verifyCode(@ValidationParam("phone") String phone,@ValidationParam("code") String code){

        return ResponseBean.succeed("");
    }

}
