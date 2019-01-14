package org.ewlive.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.ewlive.entity.LiveRoomInfo;

import java.util.List;


/**
 * 直播房间信息Mapper
 * Create by yangjie on 2019/01/14
 */
public interface LiveRoomInfoMapper extends BaseMapper<LiveRoomInfo> {

    /**
     * 模糊查询直播房间信息(分页)
     *
     * @param pagination
     * @param liveRoomInfo
     * @return
     */
    List<LiveRoomInfo> likeSearchLiveRoomInfoByPage(Pagination pagination, LiveRoomInfo liveRoomInfo);

    /**
     * 添加直播房间信息
     *
     * @param liveRoomInfo
     * @return
     */
    int addLiveRoomInfo(LiveRoomInfo liveRoomInfo);

    /**
     * 根据Id修改直播房间信息
     *
     * @param liveRoomInfo
     * @return
     */
    int updateLiveRoomInfoById(LiveRoomInfo liveRoomInfo);


}