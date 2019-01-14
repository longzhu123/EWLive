package org.ewlive.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.ewlive.entity.common.Base;

import java.sql.Timestamp;

/**
 * 直播房间信息Bean
 * Create by yangjie on 2019/01/14
 */
@TableName("live_room_info")
@Getter
@Setter
public class LiveRoomInfo extends Base {


    /**
     * 编号
     */
    @TableField("id")
    private String id;


    /**
     * 房间名称
     */
    @TableField("room_name")
    private String roomName;


    /**
     * 开播时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("play_time")
    private Timestamp playTime;


    /**
     * 开播状态
     */
    @TableField("play_state")
    private String playState;


    /**
     * 房间封面
     */
    @TableField("room_img")
    private String roomImg;


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