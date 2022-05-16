package com.learn.booking.bookingsystem.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public final class ExecutionTimer {

    @Around("@annotation(timed)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint, final Timed timed) throws Throwable {
        long start = System.nanoTime();
        Object proceed = joinPoint.proceed();
        long executionTime = System.nanoTime() - start;
        String measuredMethod = StringUtils.isEmpty(timed.msg()) ? joinPoint.getSignature().getName() : timed.msg();
        MDC.put("threadId", "[Thread ID: " + Thread.currentThread().getId() + "]");
        log.info("{} execution finished in {} ms.", measuredMethod, executionTime);
        log.debug("{} executed with args {}", measuredMethod, joinPoint.getArgs());
        return proceed;
    }
}