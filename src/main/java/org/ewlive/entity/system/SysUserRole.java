package org.ewlive.entity.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.ewlive.entity.common.Base;

import java.sql.Timestamp;

/**
 * 用户角色Bean
 * Create by yangjie on 2018/12/11
 */
@TableName("sys_user_role")
@Getter
@Setter
public class SysUserRole extends Base {


    /**
     * 编号
     */
    @TableField("id")
    private String id;


    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;


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