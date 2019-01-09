package org.ewlive.service.system;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.ewlive.constants.ExceptionConstants;
import org.ewlive.entity.system.SysLogLogin;
import org.ewlive.entity.system.SysUser;
import org.ewlive.exception.ServiceException;
import org.ewlive.mapper.system.SysLogLoginMapper;
import org.ewlive.result.ResultData;
import org.ewlive.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * 登录日志Service 
 * Create by yangjie on 2019/01/09 
 */
@Slf4j
@Service
public class SysLogLoginService{

	@Resource
	private SysLogLoginMapper sysLogLoginMapper;

	/**
	 * 根据id查询登录日志
	 * @param request
	 * @return
	 */
	public  ResultData<SysLogLogin> getSysLogLoginById(SysLogLogin request){
		//检查参数Id是否为空
		checkParamsId(request);
		log.info("根据id查询登录日志:请求参数=====>"+JSON.toJSONString(request));
		ResultData<SysLogLogin> data= new ResultData<>();
		//根据id查询登录日志
		SysLogLogin sysLogLogin = sysLogLoginMapper.selectById(request.getId());
		data.setData(sysLogLogin);
		log.info("数据请求成功,=====>返回:"+JSON.toJSONString(sysLogLogin));
		return data;
	}


	/**
	 * 多条件查询登录日志
	 * @param request
	 * @return
	 */
	public ResultData<List<SysLogLogin>> getSysLogLoginByParams(SysLogLogin request){
		log.info("多条件查询登录日志信息:请求参数=====>"+JSON.toJSONString(request));
		ResultData<List<SysLogLogin>> data= new ResultData<>();
		//多条件查询登录日志信息
		List<SysLogLogin> sysLogLoginList = sysLogLoginMapper.selectList(new EntityWrapper<>(request));
		data.setData(sysLogLoginList);
		log.info("数据请求成功,=====>返回:"+JSON.toJSONString(sysLogLoginList));
		return data;
	}


	/**
	 * 模糊查询登录日志(分页)
	 * @param request
	 * @return
	 */
	public ResultData<Page<SysLogLogin>> likeSearchSysLogLoginByPage(SysLogLogin request){
		log.info("模糊查询登录日志(分页):请求参数=====>"+JSON.toJSONString(request));
		ResultData<Page<SysLogLogin>> data= new ResultData<>();
		Page<SysLogLogin> page = new Page<>(request.getCurrent(),request.getSize());
		//模糊查询登录日志(分页)
		List<SysLogLogin> sysLogLoginList = sysLogLoginMapper.likeSearchSysLogLoginByPage(page,request);
		page.setRecords(sysLogLoginList);
		data.setData(page);
		log.info("数据请求成功,=====>返回:"+JSON.toJSONString(sysLogLoginList));
		return data;
	}


	/**
	 * 添加登录日志
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData addSysLogLogin(SysLogLogin request){
		log.info("添加登录日志,请求参数====>"+JSON.toJSONString(request));
		//检查必填参数项是否空
		checkParamsForAdd(request);
		log.info("添加====>参数校验成功");
		ResultData data = new ResultData();
		//获取当前用户
		SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
		request.setId(CommonUtil.createUUID());
		request.setCreateTime(new Timestamp(System.currentTimeMillis()));
		request.setCreateUserId(currentUser.getId());
		//添加登录日志
		int i = sysLogLoginMapper.addSysLogLogin(request);
		if(i == 0){
			throw  new ServiceException(ExceptionConstants.ADD_FAIL);
		}
		log.info("添加成功");
		return data;
	}


	/**
	 * 根据id修改登录日志
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData updateSysLogLoginById(SysLogLogin request){
		log.info("修改登录日志,请求参数====>"+JSON.toJSONString(request));
		//检查id是否为空
		checkParamsId(request);
		log.info("参数校验成功,id不为空");
		ResultData data = new ResultData();
		//获取当前用户
		SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
		request.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		request.setUpdateUserId(currentUser.getId());
		//根据Id修改登录日志
		int i = sysLogLoginMapper.updateSysLogLoginById(request);
		if(i == 0){
			throw  new ServiceException(ExceptionConstants.UPDATE_FAIL);
		}
		log.info("修改成功");
		return data;
	}


	/**
	 * 根据ids批量删除登录日志
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData deleteBatchSysLogLoginByIds(SysLogLogin request){
		log.info("根据ids批量删除登录日志,请求参数====>"+JSON.toJSONString(request));
		//检查ids是否为空
		checkParamsIds(request);
		log.info("参数校验成功,ids不为空");
		ResultData data = new ResultData();
		//根据ids批量删除登录日志
		int i = sysLogLoginMapper.deleteBatchIds(request.getIds());
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
	public void checkParamsId(SysLogLogin request){
		 if(CommonUtil.isStringEmpty(request.getId())){
            throw  new ServiceException(ExceptionConstants.ID_NOT_NULL);
        }
	}

	/**
	 * 检查参数中的ids是否为空
	 * @param request
	 */
	public void  checkParamsIds(SysLogLogin request) {
		if (CommonUtil.isCollectionEmpty(request.getIds())) {
			throw new ServiceException(ExceptionConstants.IDS_NOT_NULL);
		}
	}

	/**
	 * 检查添加参数是否齐全
	 * @param request
	 */
	public void checkParamsForAdd(SysLogLogin request){
		//判断用户编号是否为空
		if(CommonUtil.isStringEmpty(request.getUserId())){
            throw  new ServiceException(ExceptionConstants.USERID_NOT_NULL);
        }
		//判断登录IP是否为空
		if(CommonUtil.isStringEmpty(request.getLoginIp())){
            throw  new ServiceException(ExceptionConstants.LOGINIP_NOT_NULL);
        }
		//判断登录时间是否为空
		if(Objects.isNull(request.getLoginTime())){
            throw  new ServiceException(ExceptionConstants.LOGINTIME_NOT_NULL);
        }
	}

}