package com.cj.operationlog.service.aop;

import com.alibaba.fastjson2.JSON;
import com.cj.operationlog.service.annoiation.OperationLog;
import com.cj.operationlog.service.async.AsyncFactory;
import com.cj.operationlog.service.async.AsyncManager;
import com.cj.operationlog.service.vo.OperationLogVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @description: 异步日志切面
 * @author: sakana
 * @date: 2024/7/27 20:27
 * @version: 1.0
 */
@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Resource
    private AsyncFactory asyncFactory;
    @Resource
    private AsyncManager asyncManager;

    @Pointcut("@annotation(com.cj.operationlog.service.annoiation.OperationLog)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 创建日志对象
        OperationLogVO logRecord = null;
        try {
            logRecord = this.createLogRecord(joinPoint);
        } catch (Throwable e) {
            log.error("没有合适的web请求{}", e);
        }
        // 记录详细日志
        long startTime = System.currentTimeMillis();
        // 获取方法名
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("方法执行出错:{}", e);
            if (logRecord != null) {
                logRecord.setErrorMessage(stackTraceToString(e));
            }
        } finally {
            if (logRecord != null) {
                long endTime = System.currentTimeMillis();
                logRecord.setTime(endTime - startTime);
                logRecord.setData(JSON.toJSONString(result));
                // 开启异步日志
                asyncFactory.insert(logRecord);
                asyncManager.execute(asyncFactory.getTask());
            }

        }
        return result;
    }

    public OperationLogVO createLogRecord(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        OperationLog annotation = method.getAnnotation(OperationLog.class);
        String type = annotation.value();
        String name = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(requestAttributes).getRequest();

        return OperationLogVO.builder()
                .method(request.getMethod())
                .name(name)
                .type(type)
                .requestUri(request.getRequestURI())
                .params(JSON.toJSONString(request.getParameterMap())).build();
    }

    public String stackTraceToString(Throwable e) {
        StringBuilder builder = new StringBuilder();
        builder.append(e.getClass().getName()).append(":").append(e.getMessage()).append("\n");
        for (StackTraceElement element : e.getStackTrace()) {
            builder.append(element.toString()).append("\n");
        }
        return builder.toString();
    }


}
