package kr.re.kitri.hello.aspect;

import kr.re.kitri.hello.annotation.TokenRequired;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SpringAspect {

    private static Logger log = LoggerFactory.getLogger(SpringAspect.class);

    @Before("@annotation(tokenRequired)")
    public void test(TokenRequired tokenRequired){
        log.debug("토큰이 적용되어야 합니다.");
    }

    @Before("execution(* kr.re.kitri.hello.service.*Service.*(..))")
    public void logging(JoinPoint joinPoint){
       String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + "가 실행되어 로그를 남겼습니다.");
    }

    @Around("execution(* kr.re.kitri.hello.dao.*Dao.*(..))")
    public Object queryLog(ProceedingJoinPoint pjp) throws Throwable{ // 예외를 날려줘야함
        long startTime = System.currentTimeMillis();
        Object obj = pjp.proceed(); // 메소드를 이 시점에 실행시킴
        long endTime = System.currentTimeMillis();
        long runningTime = endTime - startTime;

        log.debug(pjp.getSignature().getDeclaringTypeName() + " 클래스의 " + pjp.getSignature().getName() +
                " 메소드의 총 실행 시간은 " + runningTime + "밀리초 입니다.");
        /*log.debug("총 실행 시간 : " + runningTime);*/
        return obj;
    }
}
