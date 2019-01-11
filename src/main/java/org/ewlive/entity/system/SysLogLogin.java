package org.ewlive.entity.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.ewlive.entity.common.Base;

import java.sql.Timestamp;

/**
 * 登录日志Bean 
 * Create by yangjie on 2019/01/09 
 */
@TableName("sys_log_login")
@Getter
@Setter
public class SysLogLogin extends Base {


	/**
	 * 编号
	 */
	@TableField("id")
	private String id;


	/**
	 * 用户编号
	 */
	@TableField("user_id")
	private String userId;


	/**
	 * 昵称
	 */
	@TableField("nick_name")
	private  String nickName;


	/**
	 * 登录IP
	 */
	@TableField("login_ip")
	private String loginIp;


	/**
	 * 登录时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@TableField("login_time")
	private Timestamp loginTime;


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