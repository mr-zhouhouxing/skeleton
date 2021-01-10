package io.pandora.mall.manage.log.aspect;

import io.pandora.mall.domian.system.Log;
import io.pandora.mall.manage.log.service.LoggingService;
import io.pandora.mall.util.StringUtils;
import io.pandora.mall.util.ThrowableUtils;
import io.pandora.mall.util.spring.SpringContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Created by mr_zhou on 2020/11/30
 */
@Aspect
@Component
public class LoggingAspect {

    private final LoggingService loggingService;

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    public LoggingAspect(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Pointcut("@annotation(io.pandora.mall.manage.log.annotation.SysLog)")
    public void logPointcut() { }

    /**
     * 记录controller日志，包括请求、IP、参数
     * @param joinPoint
     * @return Object
     * @throws Throwable
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        currentTime.set(System.currentTimeMillis());

        result = joinPoint.proceed();

        long time = System.currentTimeMillis() - currentTime.get();
        Log log = new Log("INFO",time);
        currentTime.remove();
        HttpServletRequest request = SpringContextHolder.getHttpServletRequest();

        long uid = getUid();
        String ip = StringUtils.getIp(SpringContextHolder.getHttpServletRequest());
        // 存库
        loggingService.saveLogging(getUsername(), ip, joinPoint, log, uid);

        // 打印到控制台
        printLogConsole(uid,StringUtils.getMobileSystem(request),request.getRequestURI().toString(),ip,time);

        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Log log = new Log("ERROR",System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        log.setExceptionDetail(ThrowableUtils.getStackTrace(e).getBytes());

        String ip = StringUtils.getIp(SpringContextHolder.getHttpServletRequest());
        // 存储异常信息
        loggingService.saveLogging(getUsername(), ip, (ProceedingJoinPoint)joinPoint, log,getUid());
    }

    /**
     * 输出到控制台
     * @param userId
     * @param mobile
     * @param url
     * @param ip
     * @param time
     */
    private void printLogConsole(long userId,String mobile,String url,String ip,long time){
        // 记录下请求内容
        StringBuilder sb = new StringBuilder();
        sb.append(userId + "-" + mobile);
        sb.append(" - " + url + " - ");
        sb.append(ip + " - ");
        sb.append(time + "ms");

        logger.info(sb.toString());
    }

    private String getUsername() {
        return "测试用户";
//        try { return SecurityUtils.getUsername();
//        }catch (Exception e){ return ""; }
    }

    private Long getUid(){
        return 10000L;
//        try { return SecurityUtils.getUserId();
//        }catch (Exception e){ return 0L; }
    }

}
