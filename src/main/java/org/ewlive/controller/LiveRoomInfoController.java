package org.ewlive.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.ewlive.aop.AuthReq;
import org.ewlive.aop.SysLog;
import org.ewlive.entity.LiveRoomInfo;
import org.ewlive.result.ResultData;
import org.ewlive.service.LiveRoomInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 直播房间信息Controller
 * Create by yangjie on 2019/01/14
 */
@RestController
@RequestMapping("/liveRoomInfo")
public class LiveRoomInfoController {

    @Resource
    private LiveRoomInfoService liveRoomInfoService;

    /**
     * 根据id查询直播房间信息
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id修改直播房间信息", syslog = true)
    @RequestMapping("/getLiveRoomInfoById")
    public ResultData<LiveRoomInfo> getLiveRoomInfoById(@RequestBody LiveRoomInfo request) {
        return liveRoomInfoService.getLiveRoomInfoById(request);
    }

    /**
     * 多条件查询直播房间信息
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "多条件查询直播房间信息", syslog = true)
    @RequestMapping("/getLiveRoomInfoByParams")
    public ResultData<List<LiveRoomInfo>> getLiveRoomInfoByParams(@RequestBody LiveRoomInfo request) {
        return liveRoomInfoService.getLiveRoomInfoByParams(request);
    }

    /**
     * 模糊查询直播房间信息(分页)
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "模糊查询直播房间信息", syslog = true)
    @RequestMapping("/likeSearchLiveRoomInfoByPage")
    public ResultData<Page<LiveRoomInfo>> likeSearchLiveRoomInfoByPage(@RequestBody LiveRoomInfo request) {
        return liveRoomInfoService.likeSearchLiveRoomInfoByPage(request);
    }

    /**
     * 添加直播房间信息
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "添加直播房间信息", syslog = true)
    @RequestMapping("/addLiveRoomInfo")
    public ResultData addLiveRoomInfo(@RequestBody LiveRoomInfo request) {
        return liveRoomInfoService.addLiveRoomInfo(request);
    }

    /**
     * 根据id修改直播房间信息
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id修改直播房间信息", syslog = true)
    @RequestMapping("/updateLiveRoomInfoById")
    public ResultData updateLiveRoomInfoById(@RequestBody LiveRoomInfo request) {
        return liveRoomInfoService.updateLiveRoomInfoById(request);
    }

    /**
     * 根据ids批量删除直播房间信息
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据ids批量删除直播房间信息", syslog = true)
    @RequestMapping("/deleteBatchLiveRoomInfoByIds")
    public ResultData deleteBatchLiveRoomInfoByIds(@RequestBody LiveRoomInfo request) {
        return liveRoomInfoService.deleteBatchLiveRoomInfoByIds(request);
    }

}