package org.yj.auto.produce.mvc;

import org.yj.auto.produce.mvc.util.CommonUtils;
import org.yj.auto.produce.mvc.util.DbUtil;

public class JDBCTest {
    public static void main(String[] args) {
        String sql = "insert into sys_log_operate(id,ip,oper_content,task_time_span) values ";
        for (int i = 1; i <=1000; i++) {
            if(i!=1000){
                sql+=" ('"+CommonUtils.createUUID()+"',substring(MD5(RAND()),1,20),substring(MD5(RAND()),1,20),23),";
            }else{
                sql+=" ('"+CommonUtils.createUUID()+"',substring(MD5(RAND()),1,20),substring(MD5(RAND()),1,20),12)";
            }
        }
        int i = DbUtil.executeUpdate(sql, new Object[]{});
        System.out.println(i);


    }
}
