package net.test.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SampleAdvice {
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SampleAdvice.class);
	
	/*@Before("execution(* net.test.service.MessageService*.*(..))")*/
	public void startLog(JoinPoint jp) {
		
		logger.info("--------------------");
		logger.info("--------------------");
		logger.info(Arrays.toString(jp.getArgs()));
	}
	
	/*@Around("execution(* net.test.service.MessageService*.*(..))")*/
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		long starttime = System.currentTimeMillis();
		logger.info(Arrays.toString(pjp.getArgs()));
		
		Object result = pjp.proceed();
		
		long endTime = System.currentTimeMillis();
		logger.info(pjp.getSignature().getName() + " : " + (endTime - starttime));
		logger.info("================================");
		
		return result;
	}

}
