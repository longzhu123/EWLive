
package org.yj.auto.produce.mvc.util;

import java.io.*;
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


    /**
     * 获取一个文件里面的所有文本内容
     * @param fileName
     * @return
     */
    public static  String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

}
