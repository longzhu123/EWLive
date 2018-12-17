package org.ewlive.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

/**
 * 系统字体图标Bean 
 * Create by yangjie on 2018/12/17 
 */
@TableName("sys_font_icon")
@Getter
@Setter
public class SysFontIcon extends Base{


	/**
	 * 编号
	 */
	@TableField("id")
	private String id;


	/**
	 * 字体图标名称
	 */
	@TableField("font_name")
	private String fontName;


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

	@TableField(exist = false)
	private List<String> sysFontIconNames;

}