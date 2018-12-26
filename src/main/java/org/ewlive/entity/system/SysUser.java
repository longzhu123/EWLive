package org.ewlive.entity.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.ewlive.entity.common.Base;

import java.sql.Timestamp;

/**
 * 用户Bean
 * Create by yangjie on 2018/09/28
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
     * 优币(刷礼物用的)
     */
    @TableField("ew_coin")
    private Integer ewCoin;


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