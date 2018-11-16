package org.ewlive.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.ewlive.aop.Dic;

import java.sql.Timestamp;

/**
 * 直播间信息Bean
 * Create by yangjie on 2018/11/16
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
     * 房间编号
     */
    @TableField("room_id")
    private String roomId;


    /**
     * 用户编号
     */
    @TableField("user_id")
    private String userId;


    /**
     * 开播状态
     */
    @TableField("play_state")
    @Dic(dicId = "4783fd16d2bc4015be3f35e60f970c87")
    private String playState;


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
     * 开播状态中文描述
     */
    @TableField(exist = false)
    private  String playStateDesc;

}