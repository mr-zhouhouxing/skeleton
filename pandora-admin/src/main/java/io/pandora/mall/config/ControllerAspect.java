package io.pandora.mall.config;

import io.pandora.mall.aspect.AspectServiceImpl;
import io.pandora.mall.aspect.ParamXssPassAspect;
import io.pandora.mall.aspect.ValidationParamAspect;
import io.pandora.mall.enume.ParamXssPass;
import io.pandora.mall.enume.ValidationParam;
import io.pandora.mall.util.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * 切面: 防止xss攻击  参数验证
 *
 * @author Created by mr_zhou on 2020/12/7
 */
@Aspect
@Configuration
public class ControllerAspect {

    @Pointcut("execution(* io.pandora.mall.*.controller..*(..))  ")
    public void aspect() { }

    @Around(value = "aspect()")
    public Object validationPoint(ProceedingJoinPoint pjp)throws Throwable{
        Method method = currentMethod(pjp,pjp.getSignature().getName());
        //创建被装饰者
        AspectServiceImpl aspectApi = new AspectServiceImpl();

        //是否需要验证参数
        if (!StringUtils.isEmpty(StringUtils.getMethodAnnotationOne(method, ValidationParam.class.getSimpleName()))) {
            new ValidationParamAspect(aspectApi).doHandlerAspect(pjp,method);
        }
        //是否需要拦截xss攻击
        if(method.isAnnotationPresent( ParamXssPass.class )){
            new ParamXssPassAspect(aspectApi).doHandlerAspect(pjp,method);
        }
        return  pjp.proceed(pjp.getArgs());
    }

    /**
     * 获取目标类的所有方法，找到当前要执行的方法
     */
    private Method currentMethod ( ProceedingJoinPoint joinPoint , String methodName ) {
        Method[] methods      = joinPoint.getTarget().getClass().getMethods();
        Method   resultMethod = null;
        for ( Method method : methods ) {
            if ( method.getName().equals( methodName ) ) {
                resultMethod = method;
                break;
            }
        }
        return resultMethod;
    }
}
