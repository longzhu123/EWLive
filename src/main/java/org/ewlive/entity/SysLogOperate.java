package org.ewlive.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * 操作日志Bean
 * Create by yangjie on 2018/12/26
 */
@TableName("sys_log_operate")
@Getter
@Setter
public class SysLogOperate extends Base {


    /**
     * 编号
     */
    @TableField("id")
    private String id;


    /**
     * 操作IP
     */
    @TableField("ip")
    private String ip;


    /**
     * 操作内容
     */
    @TableField("oper_content")
    private String operContent;


    /**
     * 操作耗时
     */
    @TableField("task_time_span")
    private Integer taskTimeSpan;


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