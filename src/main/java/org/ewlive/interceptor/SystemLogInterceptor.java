package org.ewlive.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.ewlive.aop.SysLog;
import org.ewlive.constants.CommonConstants;
import org.ewlive.entity.system.SysLogError;
import org.ewlive.entity.system.SysLogOperate;
import org.ewlive.entity.system.SysUser;
import org.ewlive.exception.ServiceException;
import org.ewlive.service.system.SysLogErrorService;
import org.ewlive.service.system.SysLogOperateService;
import org.ewlive.util.CommonUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 系统日志拦截类
 */
@Slf4j
@Aspect
@Component
public class SystemLogInterceptor {


    @Resource
    private SysLogOperateService sysLogOperateService;

    @Resource
    private SysLogErrorService sysLogErrorService;


    private static Long useSecond = 0l;


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
        useSecond = System.currentTimeMillis();
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
        useSecond = System.currentTimeMillis() - useSecond;
        //新增用户操作日志
        addSysLogOpearte(joinPoint, useSecond, request);
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
        addSysLogError(joinPoint, e, request);
    }


    /**
     * 新增用户操作日志
     *
     * @param joinPoint
     * @throws Exception
     */
    @Async
    public void addSysLogOpearte(JoinPoint joinPoint, Long useSecond, HttpServletRequest request) throws Exception {
        try {
            String desc = getServiceMthodDescription(joinPoint);
            Boolean sysLog = getServiceMthodSysLog(joinPoint);
            if (CommonUtil.isStringNotEmpty(desc) && sysLog) {
                log.info("记录操作日志开始...");
                //获取token
                Class reqObj = (joinPoint.getArgs()[0]).getClass().getSuperclass();
                Field tokenFiled = reqObj.getDeclaredField("token");
                tokenFiled.setAccessible(true);
                String token = tokenFiled.get(joinPoint.getArgs()[0]) + "";
                //正常日志入库
                SysLogOperate sysLogOperate = new SysLogOperate();
                sysLogOperate.setToken(token);
                sysLogOperate.setIp(CommonUtil.getIpAddr(request));
                sysLogOperate.setOperContent(desc);
                sysLogOperate.setTaskTimeSpan(useSecond);
                sysLogOperateService.addSysLogOperate(sysLogOperate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增用户异常日志
     * @param joinPoint
     * @param ex
     * @param request
     */
    @Async
    public void addSysLogError(JoinPoint joinPoint, Exception ex, HttpServletRequest request) {
        String ip = CommonUtil.getIpAddr(request);
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        try {
            //获取控制台日志
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ex.printStackTrace(new PrintStream(baos));
            String exception = baos.toString();
            /* ========日志输出========= */
            //异常信息
            String exptMsg;
            if (ex instanceof ServiceException) {
                exptMsg = ((ServiceException) ex).getResultMsg();
            } else {
                exptMsg = exception;
            }

            String exptMethod = (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");//异常方法
            String exptMethodDesc = getServiceMthodDescription(joinPoint);//方法描述
            String exptMethodParams = JSON.toJSONString(args);//请求参数

            //获取token
            Class reqObj = (joinPoint.getArgs()[0]).getClass().getSuperclass();
            Field tokenFiled = reqObj.getDeclaredField("token");
            tokenFiled.setAccessible(true);
            String token = tokenFiled.get(joinPoint.getArgs()[0]) + "";
            SysUser sysUser = null;
            if (CommonUtil.isStringNotEmpty(token)) {
                sysUser = JSON.parseObject(CommonConstants.map.get(token), SysUser.class);
            }
            String nickName = !Objects.isNull(sysUser) ? sysUser.getNickName() : "";

            System.out.println("=====异常通知开始=====");

            log.info("异常信息:" + exptMsg);
            log.info("异常方法:" + exptMethod);
            log.info("方法描述:" + exptMethodDesc);
            log.info("请求人:" + nickName);
            log.info("请求IP:" + ip);
            log.info("请求参数:" + exptMethodParams);

            //记录异常操作日志
            log.info("=====异常操作日志入库=====");
            SysLogError sysLogError = new SysLogError();
            sysLogError.setToken(token);
            sysLogError.setId(CommonUtil.createUUID());
            sysLogError.setFunction(exptMethod);
            sysLogError.setFunDescription(exptMethodDesc);
            sysLogError.setReqPerson(nickName);
            sysLogError.setReqIp(ip);
            sysLogError.setReqParams(exptMethodParams);
            sysLogError.setErrorMsg(exptMsg);
            sysLogErrorService.addSysLogError(sysLogError);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取SysLog注解的desc信息
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getServiceMthodDescription(JoinPoint joinPoint) throws Exception {
        //获取目标类名
        String targetName = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取相关参数
        Object[] arguments = joinPoint.getArgs();
        //生成类对象
        Class targetClass = Class.forName(targetName);
        //获取该类中的方法
        Method[] methods = targetClass.getMethods();

        String description = "";

        for (Method method : methods) {
            if (!method.getName().equals(methodName)) {
                continue;
            }
            Class[] clazzs = method.getParameterTypes();
            if (clazzs.length != arguments.length) {
                continue;
            }
            description = method.getAnnotation(SysLog.class).description();
        }
        return description;
    }

    /**
     * 获取注解中对是否记录日志操作信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static Boolean getServiceMthodSysLog(JoinPoint joinPoint) throws Exception {
        //获取目标类名
        String targetName = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取相关参数
        Object[] arguments = joinPoint.getArgs();
        //生成类对象
        Class targetClass = Class.forName(targetName);
        //获取该类中的方法
        Method[] methods = targetClass.getMethods();

        Boolean sysLog = null;

        for (Method method : methods) {
            if (!method.getName().equals(methodName)) {
                continue;
            }
            Class[] clazzs = method.getParameterTypes();
            if (clazzs.length != arguments.length) {
                continue;
            }
            sysLog = method.getAnnotation(SysLog.class).syslog();
        }
        return sysLog;
    }

}