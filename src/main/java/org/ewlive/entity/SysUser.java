package org.ewlive.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * 用户Bean
 * Create by yangjie on 2018/06/07
 */
@TableName("sys_user")
@Getter
@Setter
public class SysUser extends Base {


    /**
     * 编号
     */
    @TableField("id")
    private String id;


    /**
     * 邮箱
     */
    @TableField("email")
    private String email;


    /**
     * 密码
     */
    @TableField("password")
    private String password;


    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;


    /**
     * 创建人编号
     */
    @TableField("create_user_id")
    private String createUserId;


    /**
     * 创建时间
     */
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
    @TableField("update_time")
    private Timestamp updateTime;


    /**
     * 排序号
     */
    @TableField("sort")
    private Integer sort;


    /**
     * 备注
     */
    @TableField("comment")
    private String comment;


}