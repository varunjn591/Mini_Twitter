package com.challenge.application.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TwitterAspect {

	private static final Logger logger = Logger.getLogger(TwitterAspect.class);
	
	@Around("execution(* com.challenge.application.*.*(..))")
	public Object timer(ProceedingJoinPoint pjp) throws Throwable{
		
		StopWatch sw = new StopWatch();
		String name = pjp.getSignature().getName();
		try {
			sw.start();
			return pjp.proceed();
		} finally {
			sw.stop();
			logger.info("TIME TAKEN FOR EXECUTION: " + sw.getTotalTimeMillis() + " - " + name);
		}
	}
}
