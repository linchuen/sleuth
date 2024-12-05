package com.cooba.aop;

import com.cooba.dto.Response;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.CurrentTraceContext;
import org.springframework.cloud.sleuth.TraceContext;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TraceAspect {
    @Autowired
    Tracer tracer;

    @Around("execution(* com.cooba.controller.*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        if (result instanceof Response<?>) {
            CurrentTraceContext currentTraceContext = tracer.currentTraceContext();
            if (currentTraceContext == null) {
                return result;
            }

            TraceContext context = currentTraceContext.context();
            if (context == null) {
                return result;
            }

            ((Response<?>) result).setTraceId(context.traceId());
        }

        return result;
    }
}
