package com.example.gtd.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* find*())")
    private void allFindMethods() {}

    @Before("allFindMethods()")
    public void beforeFindMethods() {
        System.out.println("попытка найти что-то");
    }
}
