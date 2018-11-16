package org.ewlive.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.extern.slf4j.Slf4j;
import org.ewlive.constants.ExceptionConstants;
import org.ewlive.entity.LiveRoomInfo;
import org.ewlive.exception.ServiceException;
import org.ewlive.mapper.LiveRoomInfoMapper;
import org.ewlive.result.ResultData;
import org.ewlive.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * 直播间信息Service
 * Create by yangjie on 2018/11/16
 */
@Slf4j
@Service
public class LiveRoomInfoService {

    @Resource
    private LiveRoomInfoMapper liveRoomInfoMapper;

    /**
     * 根据id查询直播间信息
     *
     * @param request
     * @return
     */
    public ResultData<LiveRoomInfo> getLiveRoomInfoById(LiveRoomInfo request) {
        //检查参数Id是否为空
        checkParamsId(request);
        log.info("根据id查询直播间信息:请求参数=====>" + JSON.toJSONString(request));
        ResultData<LiveRoomInfo> data = new ResultData<>();
        //根据id查询直播间信息
        LiveRoomInfo liveRoomInfo = liveRoomInfoMapper.selectById(request.getId());
        data.setData(liveRoomInfo);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(liveRoomInfo));
        return data;
    }


    /**
     * 多条件查询直播间信息
     *
     * @param request
     * @return
     */
    public ResultData<List<LiveRoomInfo>> getLiveRoomInfoByParams(LiveRoomInfo request) {
        log.info("多条件查询直播间信息信息:请求参数=====>" + JSON.toJSONString(request));
        ResultData<List<LiveRoomInfo>> data = new ResultData<>();
        //多条件查询直播间信息信息
        List<LiveRoomInfo> liveRoomInfoList = liveRoomInfoMapper.selectList(new EntityWrapper<>(request));
        data.setData(liveRoomInfoList);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(liveRoomInfoList));
        return data;
    }


    /**
     * 添加直播间信息
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData addLiveRoomInfo(LiveRoomInfo request) {
        log.info("添加直播间信息,请求参数====>" + JSON.toJSONString(request));
        //检查必填参数项是否空
        checkParamsForAdd(request);
        log.info("添加====>参数校验成功");
        ResultData data = new ResultData();

        request.setId(CommonUtil.createUUID());
        request.setCreateTime(new Timestamp(System.currentTimeMillis()));
        request.setCreateUserId(request.getId());


        //添加直播间信息
        int i = liveRoomInfoMapper.addLiveRoomInfo(request);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.ADD_FAIL);
        }
        log.info("添加成功");
        return data;
    }


    /**
     * 根据id修改直播间信息
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData updateLiveRoomInfoById(LiveRoomInfo request) {
        log.info("修改直播间信息,请求参数====>" + JSON.toJSONString(request));
        //检查id是否为空
        checkParamsId(request);
        log.info("参数校验成功,id不为空");
        ResultData data = new ResultData();
        //根据Id修改直播间信息
        int i = liveRoomInfoMapper.updateLiveRoomInfoById(request);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.UPDATE_FAIL);
        }
        log.info("修改成功");
        return data;
    }


    /**
     * 根据ids批量删除直播间信息
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData deleteBatchLiveRoomInfoByIds(LiveRoomInfo request) {
        log.info("根据ids批量删除直播间信息,请求参数====>" + JSON.toJSONString(request));
        //检查ids是否为空
        checkParamsIds(request);
        log.info("参数校验成功,ids不为空");
        ResultData data = new ResultData();
        //根据ids批量删除直播间信息
        int i = liveRoomInfoMapper.deleteBatchIds(request.getIds());
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.DELTE_FAIL);
        }
        log.info("删除成功");
        return data;
    }

    /**
     * 检查参数中的id是否为空
     *
     * @param request
     */
    public void checkParamsId(LiveRoomInfo request) {
        if (CommonUtil.isStringEmpty(request.getId())) {
            throw new ServiceException(ExceptionConstants.ID_NOT_NULL);
        }
    }

    /**
     * 检查参数中的ids是否为空
     *
     * @param request
     */
    public void checkParamsIds(LiveRoomInfo request) {
        if (CommonUtil.isCollectionEmpty(request.getIds())) {
            throw new ServiceException(ExceptionConstants.IDS_NOT_NULL);
        }
    }

    /**
     * 检查添加参数是否齐全
     *
     * @param request
     */
    public void checkParamsForAdd(LiveRoomInfo request) {
        //判断房间编号是否为空
        if (CommonUtil.isStringEmpty(request.getRoomId())) {
            throw new ServiceException(ExceptionConstants.ROOMID_NOT_NULL);
        }
        //判断用户编号是否为空
        if (CommonUtil.isStringEmpty(request.getUserId())) {
            throw new ServiceException(ExceptionConstants.USERID_NOT_NULL);
        }
        //判断开播状态是否为空
        if (CommonUtil.isStringEmpty(request.getPlayState())) {
            throw new ServiceException(ExceptionConstants.PLAYSTATE_NOT_NULL);
        }
    }

}