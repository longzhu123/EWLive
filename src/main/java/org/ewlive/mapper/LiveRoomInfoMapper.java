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
     * @param liveRoomInfo
     * @return
     */
    int addLiveRoomInfo(LiveRoomInfo liveRoomInfo);

    /**
     * 根据Id修改直播间信息
     *
     * @param liveRoomInfo
     * @return
     */
    int updateLiveRoomInfoById(LiveRoomInfo liveRoomInfo);

}