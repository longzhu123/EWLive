package org.ewlive.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.ewlive.aop.AuthReq;
import org.ewlive.constants.CommonConstants;
import org.ewlive.constants.ExceptionConstants;
import org.ewlive.constants.ResultConstants;
import org.ewlive.entity.system.SysUser;
import org.ewlive.exception.ServiceException;
import org.ewlive.util.CommonUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 鉴权拦截器
 */
@Aspect
@Component
@Slf4j
public class AuthInterceptor {


    /**
     * 拦截加上 @AuthReq 注解的 API 请求
     *
     * @param joinPoint      拦截点
     * @param requestMapping 请求
     * @param authReq        标识为需要鉴权的请求
     * @return 响应
     * @throws Throwable 异常
     */
    @Around("within(org.ewlive..*) && @annotation(requestMapping) && @annotation(authReq)")
    public Object aroundController(ProceedingJoinPoint joinPoint, RequestMapping requestMapping, AuthReq authReq) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        String packageName = "org.ewlive.controller";
        String controllerName = method.getDeclaringClass().getName().substring(packageName.length());
        String requestUrl = "/" + CommonUtil.toLowerCaseFirstOne(controllerName.substring(1, controllerName.indexOf("Controller"))) + requestMapping.value()[0];
        log.info("控制器: " + controllerName);
        log.info("函数名: " + method.getName());
        log.info("请求路径: " + requestUrl);

        Class reqObj = (joinPoint.getArgs()[0]).getClass().getSuperclass();
        Field tokenFiled = reqObj.getDeclaredField("token");
        tokenFiled.setAccessible(true);
        String token = tokenFiled.get(joinPoint.getArgs()[0]) + "";
        //若前台传参token为空，则抛出异常
        if (CommonUtil.isStringEmpty(token) || token.equals("null")) {
            throw new ServiceException(ResultConstants.TOKEN_TIME_OUT_CODE, ExceptionConstants.TOKEN_NOT_NULL);
        }

        //获取当前登录用户对象
        SysUser sysUser = JSON.parseObject(CommonConstants.map.get(token), SysUser.class);
        if (Objects.isNull(sysUser)) {
            throw new ServiceException(ResultConstants.TOKEN_TIME_OUT_CODE, ExceptionConstants.AUTH_TOKEN_FAIL);
        }
        return joinPoint.proceed(); // 返回调用方法的结果
    }
}
