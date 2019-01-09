package org.ewlive.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.ewlive.constants.CommonConstants;
import org.ewlive.entity.system.SysUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.UUID;

/**
 * 公用工具类
 */
@Slf4j
public class CommonUtil {

    /**
     * 判断字符串是否为空
     *
     * @param strValue
     * @return
     */
    public static boolean isStringEmpty(String strValue) {
        if (strValue == null || "".equals(strValue)) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param strValue
     * @return
     */
    public static boolean isStringNotEmpty(String strValue) {
        if (strValue != null && !"".equals(strValue)) {
            return true;
        }
        return false;
    }

    /**
     * 判断集合是否为空
     *
     * @param colValue
     * @return
     */
    public static boolean isCollectionEmpty(Collection colValue) {
        if (colValue == null || colValue.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断集合是否不为空
     *
     * @param colValue
     * @return
     */
    public static boolean isCollectionNotEmpty(Collection colValue) {
        if (colValue != null && colValue.size() > 0) {
            return true;
        }
        return false;
    }


    /**
     * @return
     * @description 创建UUID唯一键
     */
    public static String createUUID() {
        UUID uuid = UUID.randomUUID();
        String uu = uuid.toString().replaceAll("-", "");
        return uu;
    }

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }


    /**
     * 首字母转大写
     *
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 根据token获取当前用户对象
     *
     * @param token
     * @return
     */
    public static SysUser getCurrentSysUserByToken(String token) {
        String userJsonStr = CommonConstants.map.get(token);
        SysUser sysUser = JSON.parseObject(userJsonStr, SysUser.class);
        return sysUser;
    }


    /**
     * 获取客户端ip
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip == null || ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error(getErrorInfoFromException(e));
                }
                ip = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    /**
     * 将异常Exception 或 错误Error 的堆栈信息转为字符串
     *
     * @param e 异常或错误
     * @return 堆栈
     */
    public static String getErrorInfoFromException(Throwable e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        } catch (Throwable e2) {
            return "";
        }
    }

    /**
     * 获取HttpServletRequest
     *
     * @return
     */
    public static HttpServletRequest getHttpSerlvetRequest() {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return httpServletRequest;
    }

}
