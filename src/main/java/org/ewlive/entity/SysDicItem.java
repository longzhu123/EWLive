package org.ewlive.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * 字典项Bean
 * Create by yangjie on 2018/11/16
 */
@TableName("sys_dic_item")
@Getter
@Setter
public class SysDicItem extends Base {


    /**
     * 编号
     */
    @TableField("id")
    private String id;


    /**
     * 字典编号
     */
    @TableField("dic_id")
    private String dicId;


    /**
     * 字典项名称
     */
    @TableField("dic_item_name")
    private String dicItemName;


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