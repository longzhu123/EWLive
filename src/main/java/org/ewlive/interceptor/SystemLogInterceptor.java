package org.ewlive.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统日志拦截类
 */
@Slf4j
@Aspect
@Component
public class SystemLogInterceptor {




    private static Long use_second = 0l;


    //service层切入点
    @Pointcut("@annotation(org.ewlive.aop.SysLog)")
    public void serviceAspect() {
        log.info("========serviceAspect===========");
    }

    /**
     * Before 在核心业务执行前执行，不能阻止核心业务的调用。
     *
     * @param joinPoint
     * @throws Exception
     */
    @Before("serviceAspect()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        use_second = System.currentTimeMillis();
    }

    /**
     * After 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此方法
     *
     * @param joinPoint
     * @throws Exception
     */
    @After(value = "serviceAspect()")
    public void doAfter(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        use_second = System.currentTimeMillis() - use_second;
        //新增用户操作日志
       // asyncTaskService.addSysLogOpearte(joinPoint, use_second, request);
    }


    /**
     * Around 手动控制调用核心业务逻辑，以及调用前和调用后的处理,
     * <p>
     * 注意：当核心业务抛异常后，立即退出，转向AfterAdvice 执行完AfterAdvice，再转到ThrowingAdvice
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(value = "serviceAspect()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        // 调用核心逻辑
        Object retVal = pjp.proceed();
        return retVal;
    }

    /**
     * AfterReturning 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice
     *
     * @param joinPoint
     */
    @AfterReturning(value = "serviceAspect()", returning = "retVal")
    public void afterReturning(JoinPoint joinPoint, String retVal) {
        // todo something
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        //新增用户异常日志
        //asyncTaskService.addSysErrorLog(joinPoint, e, request);
    }


}