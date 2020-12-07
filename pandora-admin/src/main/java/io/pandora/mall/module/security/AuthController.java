package io.pandora.mall.module.security;

import com.wf.captcha.ArithmeticCaptcha;
import io.pandora.mall.annotation.AnonymousAccess;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.exception.CustomException;
import io.pandora.mall.module.log.annotation.SysLog;
import io.pandora.mall.pojo.dto.admin.LoginDto;
import io.pandora.mall.redis.util.RedisUtils;
import io.pandora.mall.response.ResponseBean;
import io.pandora.mall.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Created by John on 2020/9/11
 */
@RestController
@Api(tags = {"登录授权"})
public class AuthController {

    @Value("${jwt.code.key}")
    private String codeKey;
    @Value("${jwt.expiration}")
    private Long expiration;

    private final RedisUtils redisUtils;

    public AuthController(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @SysLog("用户登录")
    @ApiOperation("登录")
    @PostMapping("/v1/user/login")
    public void login(@RequestBody LoginDto dto){}

    @SysLog("获取验证码")
    @ApiOperation("获取验证码")
    @GetMapping(value = "/code")
    @AnonymousAccess
    public ResponseEntity<Object> getCode(){
        // 算术类型 https://gitee.com/whvse/EasyCaptcha
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果
        String result ="";
        try {
            result = new Double(Double.parseDouble(captcha.text())).intValue()+"";
        }catch (Exception e){
            result = captcha.text();
        }
        String uuid = codeKey + UUID.randomUUID().toString().replace("-","");
        // 保存
        redisUtils.set(uuid, result, expiration, TimeUnit.MINUTES);
        // 验证码信息
        Map<String,Object> imgResult = new HashMap<String,Object>(2){{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return ResponseEntity.ok(imgResult);
    }

    @AnonymousAccess
    @SysLog("校验验证码")
    @ApiOperation("校验验证码")
    @GetMapping(value = "/code/verify")
    public ResponseBean verifyCode(String username, String uuid, String value){
        String result = redisUtils.get(uuid).toString();
        if (StringUtils.isBlank(result)) {
            throw new CustomException("验证码已过期");
        }
        if (StringUtils.isBlank(value) || !result.equalsIgnoreCase(value)) {
            throw new CustomException("验证码输入错误");
        }
        redisUtils.del(uuid);
        // 如果校验成功 验证码延长五分钟 并将值 修改为OK 方便登录验证
        redisUtils.set(username, Constant.OK ,expiration,TimeUnit.MINUTES);
        return ResponseBean.succeed(true);
    }

}
