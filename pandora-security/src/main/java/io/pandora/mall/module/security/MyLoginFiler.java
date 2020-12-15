package io.pandora.mall.module.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.enume.CodeEnum;
import io.pandora.mall.exception.CustomException;
import io.pandora.mall.mapper.system.UserMapper;
import io.pandora.mall.module.security.utils.JwtUtil;
import io.pandora.mall.pojo.dto.admin.LoginDto;
import io.pandora.mall.pojo.vo.system.UserInfo;
import io.pandora.mall.redis.util.RedisUtils;
import io.pandora.mall.response.ResponseBean;
import io.pandora.mall.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Created by John on 2020/9/10
 *
 *  登录过滤器
 *  继承AbstractAuthenticationProcessingFilter
 *  替换SpringSecurity默认登录过滤器UsernamePasswordAuthenticationFilter
 */
@Slf4j
public class MyLoginFiler extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ObjectMapper objectMapper;

    private String usernameParameter = "username";
    private String passwordParameter = "password";
    private boolean postOnly = true;

    public MyLoginFiler() {
        super(new AntPathRequestMatcher("/v1/user/login","POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            LoginDto dto = objectMapper.readValue(request.getInputStream(), LoginDto.class);

            String username = dto.getAccount();
            String password = dto.getPassword();

            // 校验验证码
            String result = redisUtils.get(username).toString();
            if (StringUtils.isBlank(result) || !result.equalsIgnoreCase(Constant.OK)){
                throw new CustomException("验证码已过期或验证码错误请重新输入");
            }
            // 成功则清除
            redisUtils.del(username);

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        MyUserDetails myUserDetails = (MyUserDetails) authResult.getPrincipal();
        //TODO 登录成功
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            String token = jwtUtil.generateToken(myUserDetails);
            UserInfo user = userMapper.findUserInfoByAccount(myUserDetails.getAccount());
            user.setToken(token);

            log.info("===> 账号:[{}] 登录成功",user.getAccount());
            ResponseBean responseBean = new ResponseBean(CodeEnum.SUCCESS.getCode(), "登录成功", user);

            writer.write(objectMapper.writeValueAsString(responseBean));
        } catch (Exception e) {
            logger.error("successfulAuthentication" + e.getMessage());
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        logger.info("===> 登录失败:{}",failed);
        //TODO 登录失败
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            ResponseBean responseBean = ResponseBean.failMsg("登录失败");

            writer.write(objectMapper.writeValueAsString(responseBean));
        } catch (Exception e) {
            logger.error("unsuccessfulAuthentication" + e.getMessage());
        }
    }

    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(this.passwordParameter);
    }

    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(this.usernameParameter);
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setUsernameParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.usernameParameter = usernameParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getUsernameParameter() {
        return this.usernameParameter;
    }

    public final String getPasswordParameter() {
        return this.passwordParameter;
    }
}
