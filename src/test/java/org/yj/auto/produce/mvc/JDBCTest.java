package org.yj.auto.produce.mvc;

import org.yj.auto.produce.mvc.util.CommonUtils;
import org.yj.auto.produce.mvc.util.DbUtil;

import java.util.Random;

public class JDBCTest {
    public static void main(String[] args) {
        String sql = "insert into sdk_test(id,username,age,password,borth) values ";
        for (int i = 1; i <= 1000; i++) {
            int age = new Random().nextInt(100)+1;
            sql += " ('" + CommonUtils.createUUID() + "',substring(MD5(RAND()),1,20),"+age+",substring(MD5(RAND()),1,20),date(from_unixtime(\n" +
                    " unix_timestamp('2017-01-01') \n" +
                    " + floor(\n" +
                    "   rand() * ( unix_timestamp('2018-08-08') - unix_timestamp('2017-01-01') + 1 )\n" +
                    " )\n" +
                    "))),";
        }
        int i = DbUtil.executeUpdate(sql.substring(0,sql.length() - 1), new Object[]{});
        System.out.println(i);

    }
}
