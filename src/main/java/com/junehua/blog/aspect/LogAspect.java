package com.junehua.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {

    private final Logger logger= LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* com.junehua.blog.controller.*.*(..))")
    public void log(){};

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classAndMethod = joinPoint.getSignature().getDeclaringTypeName() + "--->" + joinPoint.getSignature().getName();
        logger.info("请求信息-》"+url+"---ip地址-》"+ip+"----其他信息-》"+classAndMethod);
    }

    @After("log()")
    public void doAfter(){
        logger.info("-------after-------");
    }

    @AfterReturning(pointcut = "log()",returning = "result")
    public void doAfter(Object result){
        logger.info("返回结果是{}",result);
    }

}
