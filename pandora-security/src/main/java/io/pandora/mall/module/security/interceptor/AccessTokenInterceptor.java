package io.pandora.mall.module.security.interceptor;

import io.pandora.mall.annotation.AccessToken;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.domian.system.Token;
import io.pandora.mall.exception.TokenException;
import io.pandora.mall.module.security.service.ITokenService;
import io.pandora.mall.redis.util.RedisUtils;
import io.pandora.mall.util.DateUtils;
import io.pandora.mall.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 拦截APP下请求
 *
 * @author Created by John on 2020/8/25
 */
public class AccessTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ITokenService tokenService;

    private final static Logger LOG = LoggerFactory.getLogger(AccessTokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String tokenStr = request.getHeader(Constant.ACCESS_TOKEN);

        //("---------------------开始进入请求地址拦截----------------------------");

        if (!(handler instanceof HandlerMethod)) return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(AccessToken.class)) try {
            if (method.getAnnotation(AccessToken.class).required()) {

                if (StringUtils.isBlank(tokenStr)) {
                    LOG.info("==> AccessTokenInterceptor accessToken Null");
                    throw new TokenException("AccessToken cannot be NULL");
                }

                // 校验token
                Token token = tokenService.findByToken(tokenStr);
                if (token == null || !token.getToken().equals(tokenStr)) {
                    LOG.info("==> AccessTokenInterceptor accessToken failed");
                    throw new TokenException("AccessToken failed");
                }
                if (!DateUtils.belongCalendar(new Date(),token.getExpireTime())){
                    LOG.info("==> AccessTokenInterceptor accessToken Expire");
                    throw new TokenException("AccessToken Expire");
                }

                return true;
            }
        } catch (Exception e) {
            LOG.error("AccessTokenInterceptor:{}", e);
            throw new TokenException(e.getMessage());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        //System.out.println("------------处理请求完成后视图渲染之前的处理操作----------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //System.out.println("---------------视图渲染之后的操作-------------------------");
    }
}
