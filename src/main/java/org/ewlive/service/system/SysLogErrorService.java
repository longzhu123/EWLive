package org.ewlive.service.system;

import javax.annotation.Resource;

import org.ewlive.entity.system.SysUser;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import org.ewlive.constants.ExceptionConstants;
import org.ewlive.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.ewlive.result.ResultData;
import org.ewlive.util.CommonUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.ewlive.entity.system.SysLogError;
import org.ewlive.mapper.system.SysLogErrorMapper;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.plugins.Page;
import com.alibaba.fastjson.JSON;

/**
 * 异常日志Service 
 * Create by yangjie on 2019/01/09 
 */
@Slf4j
@Service
public class SysLogErrorService{

	@Resource
	private SysLogErrorMapper sysLogErrorMapper;

	/**
	 * 根据id查询异常日志
	 * @param request
	 * @return
	 */
	public  ResultData<SysLogError> getSysLogErrorById(SysLogError request){
		//检查参数Id是否为空
		checkParamsId(request);
		log.info("根据id查询异常日志:请求参数=====>"+JSON.toJSONString(request));
		ResultData<SysLogError> data= new ResultData<>();
		//根据id查询异常日志
		SysLogError sysLogError = sysLogErrorMapper.selectById(request.getId());
		data.setData(sysLogError);
		log.info("数据请求成功,=====>返回:"+JSON.toJSONString(sysLogError));
		return data;
	}


	/**
	 * 多条件查询异常日志
	 * @param request
	 * @return
	 */
	public ResultData<List<SysLogError>> getSysLogErrorByParams(SysLogError request){
		log.info("多条件查询异常日志信息:请求参数=====>"+JSON.toJSONString(request));
		ResultData<List<SysLogError>> data= new ResultData<>();
		//多条件查询异常日志信息
		List<SysLogError> sysLogErrorList = sysLogErrorMapper.selectList(new EntityWrapper<>(request));
		data.setData(sysLogErrorList);
		log.info("数据请求成功,=====>返回:"+JSON.toJSONString(sysLogErrorList));
		return data;
	}


	/**
	 * 模糊查询异常日志(分页)
	 * @param request
	 * @return
	 */
	public ResultData<Page<SysLogError>> likeSearchSysLogErrorByPage(SysLogError request){
		log.info("模糊查询异常日志(分页):请求参数=====>"+JSON.toJSONString(request));
		ResultData<Page<SysLogError>> data= new ResultData<>();
		Page<SysLogError> page = new Page<>(request.getCurrent(),request.getSize());
		//模糊查询异常日志(分页)
		List<SysLogError> sysLogErrorList = sysLogErrorMapper.likeSearchSysLogErrorByPage(page,request);
		page.setRecords(sysLogErrorList);
		data.setData(page);
		log.info("数据请求成功,=====>返回:"+JSON.toJSONString(sysLogErrorList));
		return data;
	}


	/**
	 * 添加异常日志
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData addSysLogError(SysLogError request){
		log.info("添加异常日志,请求参数====>"+JSON.toJSONString(request));
		//检查必填参数项是否空
		checkParamsForAdd(request);
		log.info("添加====>参数校验成功");
		ResultData data = new ResultData();
		//获取当前用户
		SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
		request.setId(CommonUtil.createUUID());
		request.setCreateTime(new Timestamp(System.currentTimeMillis()));
		request.setCreateUserId(currentUser.getId());
		//添加异常日志
		int i = sysLogErrorMapper.addSysLogError(request);
		if(i == 0){
			throw  new ServiceException(ExceptionConstants.ADD_FAIL);
		}
		log.info("添加成功");
		return data;
	}


	/**
	 * 根据id修改异常日志
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData updateSysLogErrorById(SysLogError request){
		log.info("修改异常日志,请求参数====>"+JSON.toJSONString(request));
		//检查id是否为空
		checkParamsId(request);
		log.info("参数校验成功,id不为空");
		ResultData data = new ResultData();
		//获取当前用户
		SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
		request.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		request.setUpdateUserId(currentUser.getId());
		//根据Id修改异常日志
		int i = sysLogErrorMapper.updateSysLogErrorById(request);
		if(i == 0){
			throw  new ServiceException(ExceptionConstants.UPDATE_FAIL);
		}
		log.info("修改成功");
		return data;
	}


	/**
	 * 根据ids批量删除异常日志
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData deleteBatchSysLogErrorByIds(SysLogError request){
		log.info("根据ids批量删除异常日志,请求参数====>"+JSON.toJSONString(request));
		//检查ids是否为空
		checkParamsIds(request);
		log.info("参数校验成功,ids不为空");
		ResultData data = new ResultData();
		//根据ids批量删除异常日志
		int i = sysLogErrorMapper.deleteBatchIds(request.getIds());
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
	public void checkParamsId(SysLogError request){
		 if(CommonUtil.isStringEmpty(request.getId())){
            throw  new ServiceException(ExceptionConstants.ID_NOT_NULL);
        }
	}

	/**
	 * 检查参数中的ids是否为空
	 * @param request
	 */
	public void  checkParamsIds(SysLogError request) {
		if (CommonUtil.isCollectionEmpty(request.getIds())) {
			throw new ServiceException(ExceptionConstants.IDS_NOT_NULL);
		}
	}

	/**
	 * 检查添加参数是否齐全
	 * @param request
	 */
	public void checkParamsForAdd(SysLogError request){
		//判断异常方法是否为空
		if(CommonUtil.isStringEmpty(request.getFunction())){
            throw  new ServiceException(ExceptionConstants.FUNCTION_NOT_NULL);
        }
		//判断方法描述是否为空
		if(CommonUtil.isStringEmpty(request.getFunDescription())){
            throw  new ServiceException(ExceptionConstants.FUNDESCRIPTION_NOT_NULL);
        }
		//判断请求人是否为空
		if(CommonUtil.isStringEmpty(request.getReqPerson())){
            throw  new ServiceException(ExceptionConstants.REQPERSON_NOT_NULL);
        }
		//判断请求IP是否为空
		if(CommonUtil.isStringEmpty(request.getReqIp())){
            throw  new ServiceException(ExceptionConstants.REQIP_NOT_NULL);
        }
		//判断请求参数是否为空
		if(CommonUtil.isStringEmpty(request.getReqParams())){
            throw  new ServiceException(ExceptionConstants.REQPARAMS_NOT_NULL);
        }
		//判断异常信息是否为空
		if(CommonUtil.isStringEmpty(request.getErrorMsg())){
            throw  new ServiceException(ExceptionConstants.ERRORMSG_NOT_NULL);
        }
	}

}