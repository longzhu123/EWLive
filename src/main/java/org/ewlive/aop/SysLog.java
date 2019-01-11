package org.ewlive.aop;

import java.lang.annotation.*;

/**
 * 系统日志注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    //方法描述
    String description()  default "";

    //是否记录系统操作日志，默认是false
    boolean syslog()  default false;
}