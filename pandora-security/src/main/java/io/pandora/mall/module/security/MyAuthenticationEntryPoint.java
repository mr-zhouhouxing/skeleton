package io.pandora.mall.module.security;

import io.pandora.mall.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SpringSecurity异常处理
 */
@Slf4j
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e){
        e.printStackTrace();
        log.info("===> authentication exception:{}", request.getRequestURL());
        throw new CustomException(e.getMessage());
    }

}
