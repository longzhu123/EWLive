package org.ewlive.entity.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.ewlive.entity.common.Base;

import java.sql.Timestamp;

/**
 * 异常日志Bean
 * Create by yangjie on 2019/01/09
 */
@TableName("sys_log_error")
@Getter
@Setter
public class SysLogError extends Base {


    /**
     * 编号
     */
    @TableField("id")
    private String id;


    /**
     * 异常方法
     */
    @TableField("function")
    private String function;


    /**
     * 方法描述
     */
    @TableField("fun_description")
    private String funDescription;


    /**
     * 请求人
     */
    @TableField("req_person")
    private String reqPerson;


    /**
     * 请求IP
     */
    @TableField("req_ip")
    private String reqIp;


    /**
     * 请求参数
     */
    @TableField("req_params")
    private String reqParams;


    /**
     * 异常信息
     */
    @TableField("error_msg")
    private String errorMsg;


    /**
     * 创建人编号
     */
    @TableField("create_user_id")
    private String createUserId;


    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private Timestamp createTime;


    /**
     * 修改人编号
     */
    @TableField("update_user_id")
    private String updateUserId;


    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("update_time")
    private Timestamp updateTime;


    /**
     * 备注
     */
    @TableField("comment")
    private String comment;


}