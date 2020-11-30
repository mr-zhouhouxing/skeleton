package io.pandora.mall.util.spring;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 以静态变量保存Spring ApplicationContext,
 * 可在任何代码任何地方任何时候取出ApplicationContext
 */
@Service
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

    /** 取得存储在静态变量中的ApplicationContext.*/
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /** 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.*/
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /** 通过class获取Bean.*/
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /** 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.*/
    public static <T> T getBean(String name,Class<T> clazz) {
        assertContextInjected();
        return applicationContext.getBean(name,clazz);
    }

    /** 清除SpringContextHolder中的ApplicationContext为Null.*/
    public static void clearHolder() {
        if (logger.isDebugEnabled()) {
            logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        }
        applicationContext = null;
    }

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /** 实现DisposableBean接口, 在Context关闭时清理静态变量.*/
    @Override
    public void destroy() throws Exception { SpringContextHolder.clearHolder();}

    /** 检查ApplicationContext不为空.*/
    private static void assertContextInjected() {
        Validate.validState(applicationContext != null, "ApplicationContext属性未注入,请在ApplicationContext.xml中定义SpringContextHolder.");
    }

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}