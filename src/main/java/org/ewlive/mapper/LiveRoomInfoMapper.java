package org.ewlive.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.ewlive.entity.LiveRoomInfo;


/**
 * 直播间信息Mapper
 * Create by yangjie on 2018/11/16
 */
public interface LiveRoomInfoMapper extends BaseMapper<LiveRoomInfo> {

    /**
     * 添加直播间信息
     *
     * @param liveroominfo
     * @return
     */
    int addLiveRoomInfo(LiveRoomInfo liveroominfo);

    /**
     * 根据Id修改直播间信息
     *
     * @param liveroominfo
     * @return
     */
    int updateLiveRoomInfoById(LiveRoomInfo liveroominfo);

}