package io.pandora.mall.module.social;

import io.pandora.mall.manage.log.annotation.SysLog;
import io.pandora.mall.module.social.enume.SocialUserLoginType;
import io.pandora.mall.module.social.processor.user.SocialUserLoginProcessor;
import io.pandora.mall.module.social.processor.user.ThirdPartyLoginHelper;
import io.pandora.mall.pojo.dto.admin.LoginDto;
import io.pandora.mall.pojo.dto.social.PasswordDto;
import io.pandora.mall.pojo.dto.social.RegisterDto;
import io.pandora.mall.pojo.vo.social.SocialUserInfoVo;
import io.pandora.mall.response.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
@RestController
@Api(tags = {"社交系统 - 【登录注册】"})
@RequestMapping("/${social}/sso")
public class SocialUserLoginController {

    private final SocialUserService socialUserService;

    public SocialUserLoginController(SocialUserService socialUserService) {
        this.socialUserService = socialUserService;
    }

    @SysLog("用户注册")
    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public ResponseBean register(@RequestBody @Valid RegisterDto dto, HttpServletRequest request){
        String register = socialUserService.register(dto, request);
        socialUserService.cleanCode(dto.getPhone());
        return ResponseBean.succeed(register);
    }

    @SysLog("验证码注册并登录")
    @PostMapping("/registerAndLogin")
    @ApiOperation(value = "验证码注册并登录")
    public ResponseBean registerAndLogin(@RequestBody @Valid RegisterDto dto, HttpServletRequest request){
        SocialUserLoginProcessor processor = SocialUserLoginProcessor.builder()
                .account(dto.getPhone())
                .verifyCode(dto.getVerifyCode())
                .loginType(SocialUserLoginType.VERIFY_CODE_TO_LOGIN.getType())
                .build();
        SocialUserInfoVo userInfo = socialUserService.toLogin(processor, request);
        socialUserService.cleanCode(dto.getPhone());
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

    @SysLog("第三方用户登录")
    @GetMapping("/thirdLogin")
    @ApiOperation(value = "第三方用户登录[1002:微信登录]")
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
    @ApiOperation(value = "发送验证码[TYPE:1.登录,2.注册,3.忘记密码]")
    public ResponseBean sendVerifyCode(String account , Integer type){
        Map<String, Object> map = socialUserService.sendVerifyCode(account, type);
        return ResponseBean.succeed(map);
    }

    @SysLog("校验验证码")
    @GetMapping("/code/verify")
    @ApiOperation(value = "校验验证码")
    public ResponseBean verifyCode(String phone , String code){
        boolean verify = socialUserService.verifyCode(phone, code);
        return ResponseBean.succeed(verify);
    }

    @SysLog("修改密码")
    @PostMapping("/update/password")
    @ApiOperation(value = "修改密码")
    public ResponseBean updatePassword(@Valid @RequestBody PasswordDto dto){
        String message = socialUserService.updatePassword(dto);
        socialUserService.cleanCode(dto.getAccount());
        return ResponseBean.succeed(message);
    }

}
