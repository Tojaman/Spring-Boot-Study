package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.sound.midi.Soundbank;

//스프링 빈으로 등록해줘야 한다.
//이때 @Component 어노테이션을 이용할 수도 있지만 SpringConfig에 직접 스프링 빈으로 등록을 해주는 게 좋다.
//왜냐하면 AOP는 정형화되지 않고 특별한 느낌이라 확실히 빈으로 등록했다고 인식시켜주는 것이 좋다??
@Aspect
public class TimeTraceAop {
//    hello.hellospring 패키지 하위에 있는 모든 클래스에 적용하라는 뜻
    @Around("execution(* hello.hellospring..*(..))")
    public Object execut(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("SRART : " + joinPoint.toString());
        try {
            return joinPoint.proceed(); // 대상 메서드 실행하고 @Around 어노테이션 코드 일시 중지
        } finally { // 대상 메소드 실행 완료되면 finally 블록 내부 코드 실행
            long finish = System.currentTimeMillis();
            long timeMs = finish = start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }

}
