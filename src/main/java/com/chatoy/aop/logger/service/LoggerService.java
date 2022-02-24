package com.chatoy.aop.logger.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Aspect
public class LoggerService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.chatoy.*.*.*.*(..))")
    public void beforeLog(JoinPoint jp) {
        logger.info("[" + jp.getTarget().getClass().getSimpleName() + "||" + jp.getSignature().getName() + " START]");
    }

    @After("execution(* com.chatoy.*.*.*.*(..))")
    public void afterLog(JoinPoint jp) {
        logger.info("[" + jp.getTarget().getClass().getSimpleName() + "||" + jp.getSignature().getName() + " END]");
    }

    @Before("execution(* com.chatoy.*.*.*.*.*(..))")
    public void beforeTcpLog(JoinPoint jp) {
        logger.info("[TCP:" + jp.getTarget().getClass().getSimpleName() + "||" + jp.getSignature().getName() + " START]");
    }

    @After("execution(* com.chatoy.*.*.*.*.*(..))")
    public void afterTcpLog(JoinPoint jp) {
        logger.info("[TCP:" + jp.getTarget().getClass().getSimpleName() + "||" + jp.getSignature().getName() + " END]");
    }
}