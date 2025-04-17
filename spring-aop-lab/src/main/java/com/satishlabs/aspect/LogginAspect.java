package com.satishlabs.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogginAspect {
	private static final Logger logger = LoggerFactory.getLogger(LogginAspect.class);
	
	//Before Advice
	@Before("execution(*com.satishlabs.service.*.*(..))")// Pointcut expression
	public void logBefore(JoinPoint joinPoint) {
		 logger.info("Before executing: " + joinPoint.getSignature());
	}
	
	 // After returning advice
    @AfterReturning("execution(* com.example.service.*.*(..))")
    public void logAfterReturning(JoinPoint joinPoint) {
        logger.info("✅ Method executed successfully: " + joinPoint.getSignature());
    }

    // After throwing advice
    @AfterThrowing(pointcut = "execution(* com.example.service.*.*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("❌ Exception in method: " + joinPoint.getSignature() + " - " + exception.getMessage());
    }

    // Around advice
    @Around("execution(* com.example.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("🔄 Around: Before method execution");
        Object result = joinPoint.proceed();
        logger.info("🔄 Around: After method execution");
        return result;
    }
}
