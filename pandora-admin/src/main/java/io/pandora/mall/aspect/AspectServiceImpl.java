package io.pandora.mall.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * 基本被装饰类,做一些公共处理
 * @author Created by mr_zhou on 2019/10/12.
 */
public class AspectServiceImpl implements AspectService {

    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        return null;
    }
}
