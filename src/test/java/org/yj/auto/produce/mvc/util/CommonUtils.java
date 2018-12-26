
package org.yj.auto.produce.mvc.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author yejiabin
 * @date 2016-03-02
 * @desc 工具类
 */
public class CommonUtils {


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


    public static String getNowDateStr(String pattern) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String dateStr = format.format(date);
        return dateStr;
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
     * @return
     * @description 创建UUID唯一键
     */
    public static String createUUID() {
        UUID uuid = UUID.randomUUID();
        String uu = uuid.toString().replaceAll("-", "");
        return uu;
    }


}
