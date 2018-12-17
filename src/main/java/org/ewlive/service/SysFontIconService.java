package org.ewlive.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;
import org.ewlive.constants.ExceptionConstants;
import org.ewlive.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.ewlive.result.ResultData;
import org.ewlive.util.CommonUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.ewlive.entity.SysFontIcon;
import org.ewlive.mapper.SysFontIconMapper;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.plugins.Page;
import com.alibaba.fastjson.JSON;

/**
 * 系统字体图标Service 
 * Create by yangjie on 2018/12/17 
 */
@Slf4j
@Service
public class SysFontIconService{

	@Resource
	private SysFontIconMapper sysFontIconMapper;

	/**
	 * 根据id查询系统字体图标
	 * @param request
	 * @return
	 */
	public  ResultData<SysFontIcon> getSysFontIconById(SysFontIcon request){
		//检查参数Id是否为空
		checkParamsId(request);
		log.info("根据id查询系统字体图标:请求参数=====>"+JSON.toJSONString(request));
		ResultData<SysFontIcon> data= new ResultData<>();
		//根据id查询系统字体图标
		SysFontIcon sysFontIcon = sysFontIconMapper.selectById(request.getId());
		data.setData(sysFontIcon);
		log.info("数据请求成功,=====>返回:"+JSON.toJSONString(sysFontIcon));
		return data;
	}


	/**
	 * 多条件查询系统字体图标
	 * @param request
	 * @return
	 */
	public ResultData<List<SysFontIcon>> getSysFontIconByParams(SysFontIcon request){
		log.info("多条件查询系统字体图标信息:请求参数=====>"+JSON.toJSONString(request));
		ResultData<List<SysFontIcon>> data= new ResultData<>();
		//多条件查询系统字体图标信息
		List<SysFontIcon> sysFontIconList = sysFontIconMapper.selectList(new EntityWrapper<>(request));
		data.setData(sysFontIconList);
		log.info("数据请求成功,=====>返回:"+JSON.toJSONString(sysFontIconList));
		return data;
	}


	/**
	 * 模糊查询系统字体图标(分页)
	 * @param request
	 * @return
	 */
	public ResultData<Page<SysFontIcon>> likeSearchSysFontIconByPage(SysFontIcon request){
		log.info("模糊查询系统字体图标(分页):请求参数=====>"+JSON.toJSONString(request));
		ResultData<Page<SysFontIcon>> data= new ResultData<>();
		Page<SysFontIcon> page = new Page<>(request.getCurrent(),request.getSize());
		//模糊查询系统字体图标(分页)
		List<SysFontIcon> sysFontIconList = sysFontIconMapper.likeSearchSysFontIconByPage(page,request);
		page.setRecords(sysFontIconList);
		data.setData(page);
		log.info("数据请求成功,=====>返回:"+JSON.toJSONString(sysFontIconList));
		return data;
	}


	/**
	 * 添加系统字体图标
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData addSysFontIcon(SysFontIcon request){
		log.info("添加系统字体图标,请求参数====>"+JSON.toJSONString(request));
		//检查必填参数项是否空
		checkParamsForAdd(request);
		log.info("添加====>参数校验成功");
		ResultData data = new ResultData();
		request.setId(CommonUtil.createUUID());
		request.setCreateTime(new Timestamp(System.currentTimeMillis()));
		request.setCreateUserId(request.getId());
		//添加系统字体图标
		int i = sysFontIconMapper.addSysFontIcon(request);
		if(i == 0){
			throw  new ServiceException(ExceptionConstants.ADD_FAIL);
		}
		log.info("添加成功");
		return data;
	}


	/**
	 * 根据id修改系统字体图标
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData updateSysFontIconById(SysFontIcon request){
		log.info("修改系统字体图标,请求参数====>"+JSON.toJSONString(request));
		//检查id是否为空
		checkParamsId(request);
		log.info("参数校验成功,id不为空");
		ResultData data = new ResultData();
		request.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		request.setUpdateUserId(request.getId());
		//根据Id修改系统字体图标
		int i = sysFontIconMapper.updateSysFontIconById(request);
		if(i == 0){
			throw  new ServiceException(ExceptionConstants.UPDATE_FAIL);
		}
		log.info("修改成功");
		return data;
	}


	/**
	 * 根据ids批量删除系统字体图标
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData deleteBatchSysFontIconByIds(SysFontIcon request){
		log.info("根据ids批量删除系统字体图标,请求参数====>"+JSON.toJSONString(request));
		//检查ids是否为空
		checkParamsIds(request);
		log.info("参数校验成功,ids不为空");
		ResultData data = new ResultData();
		//根据ids批量删除系统字体图标
		int i = sysFontIconMapper.deleteBatchIds(request.getIds());
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
	public void checkParamsId(SysFontIcon request){
		 if(CommonUtil.isStringEmpty(request.getId())){
            throw  new ServiceException(ExceptionConstants.ID_NOT_NULL);
        }
	}

	/**
	 * 检查参数中的ids是否为空
	 * @param request
	 */
	public void  checkParamsIds(SysFontIcon request) {
		if (CommonUtil.isCollectionEmpty(request.getIds())) {
			throw new ServiceException(ExceptionConstants.IDS_NOT_NULL);
		}
	}

	/**
	 * 检查添加参数是否齐全
	 * @param request
	 */
	public void checkParamsForAdd(SysFontIcon request){
		//判断操作IP是否为空
		if(CommonUtil.isStringEmpty(request.getFontName())){
            throw  new ServiceException(ExceptionConstants.FONTNAME_NOT_NULL);
        }
	}

}