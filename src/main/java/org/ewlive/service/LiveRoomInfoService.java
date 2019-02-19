package org.ewlive.service;

import javax.annotation.Resource;

import org.ewlive.entity.system.SysUser;
import org.ewlive.util.DicConvertUtil;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.ewlive.constants.ExceptionConstants;
import org.ewlive.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.ewlive.result.ResultData;
import org.ewlive.util.CommonUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.ewlive.entity.LiveRoomInfo;
import org.ewlive.mapper.LiveRoomInfoMapper;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.plugins.Page;
import com.alibaba.fastjson.JSON;

/**
 * 直播房间信息Service 
 * Create by yangjie on 2019/01/14 
 */
@Slf4j
@Service
public class LiveRoomInfoService{

	@Resource
	private LiveRoomInfoMapper liveRoomInfoMapper;

	/**
	 * 根据id查询直播房间信息
	 * @param request
	 * @return
	 */
	public  ResultData<LiveRoomInfo> getLiveRoomInfoById(LiveRoomInfo request){
		//检查参数Id是否为空
		checkParamsId(request);
		log.info("根据id查询直播房间信息:请求参数=====>"+JSON.toJSONString(request));
		ResultData<LiveRoomInfo> data= new ResultData<>();
		//根据id查询直播房间信息
		LiveRoomInfo liveRoomInfo = liveRoomInfoMapper.selectById(request.getId());
		LiveRoomInfo dataObj = DicConvertUtil.convertObjDicDesc(liveRoomInfo,LiveRoomInfo.class);
		data.setData(dataObj);
		log.info("数据请求成功,=====>返回:"+JSON.toJSONString(dataObj));
		return data;
	}


	/**
	 * 多条件查询直播房间信息
	 * @param request
	 * @return
	 */
	public ResultData<List<LiveRoomInfo>> getLiveRoomInfoByParams(LiveRoomInfo request){
		log.info("多条件查询直播房间信息信息:请求参数=====>"+JSON.toJSONString(request));
		ResultData<List<LiveRoomInfo>> data= new ResultData<>();
		//多条件查询直播房间信息信息
		List<LiveRoomInfo> liveRoomInfoList = liveRoomInfoMapper.selectList(new EntityWrapper<>(request));
		List<LiveRoomInfo> dataObj = DicConvertUtil.convertArrayDicDesc(liveRoomInfoList,LiveRoomInfo.class);
		data.setData(dataObj);
		log.info("数据请求成功,=====>返回:"+JSON.toJSONString(dataObj));
		return data;
	}


	/**
	 * 模糊查询直播房间信息(分页)
	 * @param request
	 * @return
	 */
	public ResultData<Page<LiveRoomInfo>> likeSearchLiveRoomInfoByPage(LiveRoomInfo request){
		log.info("模糊查询直播房间信息(分页):请求参数=====>"+JSON.toJSONString(request));
		ResultData<Page<LiveRoomInfo>> data= new ResultData<>();
		if(!Objects.isNull(request.getSearchPlayTime())&&request.getSearchPlayTime().length>0){
			Timestamp begin = Timestamp.valueOf(CommonUtil.formateDateTZ(request.getSearchPlayTime()[0]));
			Timestamp end = Timestamp.valueOf(CommonUtil.formateDateTZ(request.getSearchPlayTime()[1]));
			request.setBeginPlayTime(begin);
			request.setEndPlayTime(end);
		}

		Page<LiveRoomInfo> page = new Page<>(request.getCurrent(),request.getSize());
		//模糊查询直播房间信息(分页)
		List<LiveRoomInfo> liveRoomInfoList = liveRoomInfoMapper.likeSearchLiveRoomInfoByPage(page,request);
		List<LiveRoomInfo> dataObj = DicConvertUtil.convertArrayDicDesc(liveRoomInfoList,LiveRoomInfo.class);
		page.setRecords(dataObj);
		data.setData(page);
		log.info("数据请求成功,=====>返回:"+JSON.toJSONString(liveRoomInfoList));
		return data;
	}


	/**
	 * 添加直播房间信息
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData addLiveRoomInfo(LiveRoomInfo request){
		log.info("添加直播房间信息,请求参数====>"+JSON.toJSONString(request));
		//检查必填参数项是否空
		checkParamsForAdd(request);
		log.info("添加====>参数校验成功");
		ResultData data = new ResultData();
		//获取当前用户
		SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
		request.setId(CommonUtil.createUUID());
		request.setCreateTime(new Timestamp(System.currentTimeMillis()));
		request.setCreateUserId(currentUser.getId());
		//添加直播房间信息
		int i = liveRoomInfoMapper.addLiveRoomInfo(request);
		if(i == 0){
			throw  new ServiceException(ExceptionConstants.ADD_FAIL);
		}
		log.info("添加成功");
		return data;
	}


	/**
	 * 根据id修改直播房间信息
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData updateLiveRoomInfoById(LiveRoomInfo request){
		log.info("修改直播房间信息,请求参数====>"+JSON.toJSONString(request));
		//检查id是否为空
		checkParamsId(request);
		log.info("参数校验成功,id不为空");
		ResultData data = new ResultData();
		//获取当前用户
		SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
		request.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		request.setUpdateUserId(currentUser.getId());
		//根据Id修改直播房间信息
		int i = liveRoomInfoMapper.updateLiveRoomInfoById(request);
		if(i == 0){
			throw  new ServiceException(ExceptionConstants.UPDATE_FAIL);
		}
		log.info("修改成功");
		return data;
	}


	/**
	 * 根据ids批量删除直播房间信息
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData deleteBatchLiveRoomInfoByIds(LiveRoomInfo request){
		log.info("根据ids批量删除直播房间信息,请求参数====>"+JSON.toJSONString(request));
		//检查ids是否为空
		checkParamsIds(request);
		log.info("参数校验成功,ids不为空");
		ResultData data = new ResultData();
		//根据ids批量删除直播房间信息
		int i = liveRoomInfoMapper.deleteBatchIds(request.getIds());
		if(i == 0){
			throw  new ServiceException(ExceptionConstants.DELTE_FAIL);
		}
		log.info("删除成功");
		return data;
	}

	/**
	 * 检查参数中的id是否为空
	 * @param request
	 */
	public void checkParamsId(LiveRoomInfo request){
		 if(CommonUtil.isStringEmpty(request.getId())){
            throw  new ServiceException(ExceptionConstants.ID_NOT_NULL);
        }
	}

	/**
	 * 检查参数中的ids是否为空
	 * @param request
	 */
	public void  checkParamsIds(LiveRoomInfo request) {
		if (CommonUtil.isCollectionEmpty(request.getIds())) {
			throw new ServiceException(ExceptionConstants.IDS_NOT_NULL);
		}
	}

	/**
	 * 检查添加参数是否齐全
	 * @param request
	 */
	public void checkParamsForAdd(LiveRoomInfo request){
		//判断房间名称是否为空
		if(CommonUtil.isStringEmpty(request.getRoomName())){
            throw  new ServiceException(ExceptionConstants.ROOMNAME_NOT_NULL);
        }
		//判断开播时间是否为空
		if(Objects.isNull(request.getPlayTime())){
            throw  new ServiceException(ExceptionConstants.PLAYTIME_NOT_NULL);
        }
		//判断开播状态是否为空
		if(CommonUtil.isStringEmpty(request.getPlayState())){
            throw  new ServiceException(ExceptionConstants.PLAYSTATE_NOT_NULL);
        }
		//判断房间封面是否为空
		if(CommonUtil.isStringEmpty(request.getRoomImg())){
            throw  new ServiceException(ExceptionConstants.ROOMIMG_NOT_NULL);
        }
	}

}