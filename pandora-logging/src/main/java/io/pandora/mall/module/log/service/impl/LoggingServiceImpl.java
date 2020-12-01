package io.pandora.mall.module.log.service.impl;

import cn.hutool.json.JSONObject;
import io.pandora.mall.base.BaseServiceImpl;
import io.pandora.mall.domian.system.Log;
import io.pandora.mall.module.log.annotation.SysLog;
import io.pandora.mall.module.log.service.LoggingService;
import io.pandora.mall.module.log.service.dto.LogQueryCriteria;
import io.pandora.mall.mapper.system.LoggingMapper;
import io.pandora.mall.util.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Created by mr_zhou on 2020/11/30
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LoggingServiceImpl extends BaseServiceImpl<LoggingMapper,Log> implements LoggingService {

    private final LoggingMapper loggingMapper;

    public LoggingServiceImpl(LoggingMapper loggingMapper) {
        this.loggingMapper = loggingMapper;
    }

    @Override
    public List<Log> queryAll(LogQueryCriteria criteria) {
        return null;
    }

    @Override
    public Object queryAll(LogQueryCriteria criteria, Pageable pageable) {
        return null;
    }

    @Override
    public void saveLogging(String username, String ip, ProceedingJoinPoint joinPoint, Log log, Long uid) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = method.getAnnotation(SysLog.class);

        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName()+"."+signature.getName()+"()";

        StringBuilder params = new StringBuilder("{");
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        if(argValues != null){
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }
        // 描述
        if (log != null) log.setDescription(sysLog.value());

        //类型 0-后台 1-前台
        log.setType(sysLog.type());
        if(uid != null) log.setUid(uid);

        assert log != null;
        log.setRequestIp(ip);

        String loginPath = "login";
        if(loginPath.equals(signature.getName())){
            try {
                assert argValues != null;
                username = new JSONObject(argValues[0]).get("username").toString();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        log.setAddress(StringUtils.getCityInfo(log.getRequestIp()));
        log.setMethod(methodName);
        log.setUsername(username);
        log.setParams(params.toString() + " }");
        this.save(log);
    }
}
