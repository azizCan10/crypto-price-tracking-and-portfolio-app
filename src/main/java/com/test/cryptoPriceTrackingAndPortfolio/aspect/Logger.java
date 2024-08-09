package com.test.cryptoPriceTrackingAndPortfolio.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class Logger {

    @Before("execution(* com.test.cryptoPriceTrackingAndPortfolio.service.*.*(..)) || execution(* com.test.cryptoPriceTrackingAndPortfolio.controller.*.*(..))")
    public void before(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        StringBuilder argsLog = new StringBuilder();

        for (Object arg : joinPoint.getArgs()) {
            argsLog.append(arg).append(", ");
        }

        if (!argsLog.isEmpty()) {
            log.info("{}.{}({}) method started.", className, methodName, argsLog.substring(0, argsLog.length() - 2));
        } else {
            log.info("{}.{}() method started.", className, methodName);
        }
    }

    @After("execution(* com.test.cryptoPriceTrackingAndPortfolio.service.*.*(..)) || execution(* com.test.cryptoPriceTrackingAndPortfolio.controller.*.*(..))")
    public void after(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        log.info("{}.{}() method ended.", className, methodName);
    }

    @AfterThrowing(pointcut = "execution(* com.test.cryptoPriceTrackingAndPortfolio.service.*.*(..))", throwing = "exception")
    public void logException(Throwable exception) {
        log.error("ERROR: {} WAS THROWN AT {}. ERROR MESSAGE: {}", exception.getClass().getSimpleName(), exception.getStackTrace()[0].toString(), exception.getMessage());
    }
}
