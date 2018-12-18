package org.ewlive.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * 菜单Bean 
 * Create by yangjie on 2018/12/18 
 */
@TableName("sys_menu")
@Getter
@Setter
public class SysMenu extends Base{


	/**
	 * 编号
	 */
	@TableField("id")
	private String id;


	/**
	 * 菜单名称
	 */
	@TableField("menu_name")
	private String menuName;


	/**
	 * 菜单Url
	 */
	@TableField("menu_url")
	private String menuUrl;


	/**
	 * 菜单图标
	 */
	@TableField("menu_icon")
	private String menuIcon;


	/**
	 * 父菜单编号
	 */
	@TableField("parent_id")
	private String parentId;


	/**
	 * 菜单排序号
	 */
	@TableField("menu_sort")
	private Integer menuSort;


	/**
	 * 创建人编号
	 */
	@TableField("create_user_id")
	private String createUserId;


	/**
	 * 创建时间
	 */
	@TableField("create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp updateTime;


	/**
	 * 备注
	 */
	@TableField("comment")
	private String comment;


}