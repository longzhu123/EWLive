package org.ewlive.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.ewlive.aop.Dic;
import org.ewlive.entity.common.Base;

import java.sql.Timestamp;

/**
 * 系统测试Bean 
 * Create by yangjie on 2019/05/18 
 */
@TableName("sys_test")
@Getter
@Setter
public class SysTest extends Base {


	/**
	 * 编号
	 */
	@TableField("id")
	private String id;


	/**
	 * 姓名
	 */
	@TableField("name")
	private String name;


	/**
	 * 昵称
	 */
	@TableField("nick_name")
	private String nickName;


	/**
	 * 开播状态
	 */
	@Dic(dicId = "4783fd16d2bc4015be3f35e60f970c87")
	@TableField("play_state")
	private String playState;


	/**
	 * 开播时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@TableField("play_time")
	private Timestamp playTime;


	/**
	 * 相关附件
	 */
	@TableField("about_file")
	private String aboutFile;


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


	/**
	 * 开播状态描述
	 */
	@TableField(exist = false)
	private String playStateDesc;

}