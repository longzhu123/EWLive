package org.ewlive.entity.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.ewlive.entity.common.Base;

import java.sql.Timestamp;
import java.util.List;

/**
 * 角色菜单权限Bean 
 * Create by yangjie on 2019/01/08 
 */
@TableName("sys_role_menu_authority")
@Getter
@Setter
public class SysRoleMenuAuthority extends Base {


	/**
	 * 编号
	 */
	@TableField("id")
	private String id;


	/**
	 * 菜单编号
	 */
	@TableField("menu_id")
	private String menuId;


	/**
	 * 用户角色编号
	 */
	@TableField("user_role_id")
	private String userRoleId;


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


	//业务逻辑字段

	/**
	 * 用户菜单编号集合
	 */
	@TableField(exist = false)
	private List<String> userRoleMenuIds;

}