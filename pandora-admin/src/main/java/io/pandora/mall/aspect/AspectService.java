package io.pandora.mall.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * 统一装饰者接口
 *
 * @author Created by mr_zhou on 2020/12/7
 */
public interface AspectService {

    Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable;

}
