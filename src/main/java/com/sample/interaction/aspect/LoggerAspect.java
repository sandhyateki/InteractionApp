package com.sample.interaction.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Profile({"default"})
@Slf4j
public class LoggerAspect {

    @Around("withinProcessor() || withinController()")
    public Object beforeSampleCreation(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Method (" + joinPoint.getSignature() + ") begins");
        Object result = joinPoint.proceed();
        log.info("Method (" + joinPoint.getSignature() + ") ends");
        return result;
    }

    @Pointcut("within(com.sample.interaction.controller.*)")
    public void withinController() {
    }

    @Pointcut("within(com.sample.interaction.processor.*)")
    public void withinProcessor() {
    }

}