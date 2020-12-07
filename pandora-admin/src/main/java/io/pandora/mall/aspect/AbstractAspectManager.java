package io.pandora.mall.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @author Created by mr_zhou on 2020/12/7
 */
public abstract class AbstractAspectManager implements AspectService{

    private AspectService aspectService;

    public AbstractAspectManager(AspectService aspectService) {
        this.aspectService = aspectService;
    }

    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        return this.aspectService.doHandlerAspect(pjp,method);
    }

    protected abstract Object execute(ProceedingJoinPoint pjp, Method method) throws Throwable;

}
