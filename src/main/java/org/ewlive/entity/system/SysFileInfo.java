package org.ewlive.entity.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.ewlive.entity.common.Base;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

/**
 * 附件信息Bean 
 * Create by yangjie on 2019/03/14 
 */
@TableName("sys_file_info")
@Getter
@Setter
public class SysFileInfo extends Base {


	/**
	 * 编号
	 */
	@TableField("id")
	private String id;


	/**
	 * 业务关联编号
	 */
	@TableField("fk_id")
	private String fkId;


	/**
	 * 文件名称
	 */
	@TableField("name")
	private String name;


	/**
	 * 文件在服务器的名称
	 */
	@TableField("real_name")
	private String realName;


	/**
	 * 文件大小
	 */
	@TableField("file_size")
	private Long fileSize;


	/**
	 * 文件类型
	 */
	@TableField("extension")
	private String extension;


	/**
	 * 文件后缀类型
	 */
	@TableField("content_type")
	private String contentType;


	/**
	 * 存储路径
	 */
	@TableField("directory")
	private String directory;


	/**
	 * 说明
	 */
	@TableField("remark")
	private String remark;


	/**
	 * 版本号(用于移动端的apk版本查看)
	 */
	@TableField("version")
	private String version;


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
	 * 附件集合
	 */
	@TableField(exist = false)
	private MultipartFile [] files;

}