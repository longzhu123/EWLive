package org.ewlive.controller;

import org.ewlive.aop.AuthReq;
import org.ewlive.entity.LiveRoomInfo;
import org.ewlive.result.ResultData;
import org.ewlive.service.LiveRoomInfoService;
import org.ewlive.util.FileUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * 直播间信息Controller
 * Create by yangjie on 2018/11/16
 */
@RestController
@RequestMapping("/liveRoomInfo")
public class LiveRoomInfoController {

    @Resource
    private LiveRoomInfoService liveRoomInfoService;

    /**
     * 根据id查询直播间信息
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/getLiveRoomInfoById")
    public ResultData<LiveRoomInfo> getLiveRoomInfoById(@RequestBody LiveRoomInfo request) {
        return liveRoomInfoService.getLiveRoomInfoById(request);
    }

    /**
     * 多条件查询直播间信息
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/getLiveRoomInfoByParams")
    public ResultData<List<LiveRoomInfo>> getLiveRoomInfoByParams(@RequestBody LiveRoomInfo request) {
        return liveRoomInfoService.getLiveRoomInfoByParams(request);
    }

    /**
     * 添加直播间信息
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/addLiveRoomInfo")
    public ResultData addLiveRoomInfo(@RequestBody LiveRoomInfo request) {
        return liveRoomInfoService.addLiveRoomInfo(request);
    }

    /**
     * 根据id修改直播间信息
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/updateLiveRoomInfoById")
    public ResultData updateLiveRoomInfoById(@RequestBody LiveRoomInfo request) {
        return liveRoomInfoService.updateLiveRoomInfoById(request);
    }

    /**
     * 根据ids批量删除直播间信息
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/deleteBatchLiveRoomInfoByIds")
    public ResultData deleteBatchLiveRoomInfoByIds(@RequestBody LiveRoomInfo request) {
        return liveRoomInfoService.deleteBatchLiveRoomInfoByIds(request);
    }

    //ToDo
    @RequestMapping("/testDownLoad")
    public  void testDownLoad(HttpServletResponse response){
        FileUtil.createZip("D:\\WorkSpace\\IDEA WorkSpace\\EWLive\\target\\upload\\专家端","D:\\WorkSpace\\IDEA WorkSpace\\EWLive\\target\\upload\\a.zip",true);
        FileUtil.downloadFile(new File("D:\\WorkSpace\\IDEA WorkSpace\\EWLive\\target\\upload\\a.zip"),response);
    }

}