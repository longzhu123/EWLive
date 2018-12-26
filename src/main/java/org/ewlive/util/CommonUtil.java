package org.ewlive.util;

import com.alibaba.fastjson.JSON;
import org.ewlive.constants.CommonConstants;
import org.ewlive.entity.system.SysUser;

import java.util.Collection;
import java.util.UUID;

/**
 * 公用工具类
 */
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

}
