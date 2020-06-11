package com.plm.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : cwh
 * 2019/4/12 0012
 * description ：
 */

@Aspect
@Component
@Slf4j
public class AspectHelper {


    @Pointcut("execution(* com.plm.controller.UserController.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        log.info("url={}", request.getRequestURI());
        //method
        log.info("method={}", request.getMethod());
        //ip
        log.info("ip={}", request.getRemoteAddr() + ":" + request.getRemotePort());
        //类方法
        log.info("class_name={}", joinPoint.getSignature().getDeclaringTypeName() + "---" + joinPoint.getSignature().getName());
        //参数
        log.info("args={}", joinPoint.getArgs());

    }

    @After("log()")
    public void doAfter() {
        log.info("after");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        // log.info("return={}",object.toString());
    }
}
