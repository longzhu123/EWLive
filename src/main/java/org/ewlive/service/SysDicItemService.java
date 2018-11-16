package org.ewlive.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import org.ewlive.constants.ExceptionConstants;
import org.ewlive.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.ewlive.result.ResultData;
import org.ewlive.util.CommonUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.ewlive.entity.SysDicItem;
import org.ewlive.mapper.SysDicItemMapper;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.plugins.Page;
import com.alibaba.fastjson.JSON;

/**
 * 字典项Service 
 * Create by yangjie on 2018/11/16 
 */
@Slf4j
@Service
public class SysDicItemService{

	@Resource
	private SysDicItemMapper sysDicItemMapper;

	/**
	 * 根据id查询字典项
	 * @param request
	 * @return
	 */
	public  ResultData<SysDicItem> getSysDicItemById(SysDicItem request){
		//检查参数Id是否为空
		checkParamsId(request);
		log.info("根据id查询字典项:请求参数=====>"+JSON.toJSONString(request));
		ResultData<SysDicItem> data= new ResultData<>();
		//根据id查询字典项
		SysDicItem sysDicItem = sysDicItemMapper.selectById(request.getId());
		data.setData(sysDicItem);
		log.info("数据请求成功,=====>返回:"+JSON.toJSONString(sysDicItem));
		return data;
	}


	/**
	 * 多条件查询字典项
	 * @param request
	 * @return
	 */
	public ResultData<List<SysDicItem>> getSysDicItemByParams(SysDicItem request){
		log.info("多条件查询字典项信息:请求参数=====>"+JSON.toJSONString(request));
		ResultData<List<SysDicItem>> data= new ResultData<>();
		//多条件查询字典项信息
		List<SysDicItem> sysDicItemList = sysDicItemMapper.selectList(new EntityWrapper<>(request));
		data.setData(sysDicItemList);
		log.info("数据请求成功,=====>返回:"+JSON.toJSONString(sysDicItemList));
		return data;
	}


	/**
	 * 添加字典项
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData addSysDicItem(SysDicItem request){
		log.info("添加字典项,请求参数====>"+JSON.toJSONString(request));
		//检查必填参数项是否空
		checkParamsForAdd(request);
		log.info("添加====>参数校验成功");
		ResultData data = new ResultData();

		request.setId(CommonUtil.createUUID());
		request.setCreateTime(new Timestamp(System.currentTimeMillis()));
		request.setCreateUserId(request.getId());

		//添加字典项
		int i = sysDicItemMapper.addSysDicItem(request);
		if(i == 0){
			throw  new ServiceException(ExceptionConstants.ADD_FAIL);
		}
		log.info("添加成功");
		return data;
	}


	/**
	 * 根据id修改字典项
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData updateSysDicItemById(SysDicItem request){
		log.info("修改字典项,请求参数====>"+JSON.toJSONString(request));
		//检查id是否为空
		checkParamsId(request);
		log.info("参数校验成功,id不为空");
		ResultData data = new ResultData();
		//根据Id修改字典项
		int i = sysDicItemMapper.updateSysDicItemById(request);
		if(i == 0){
			throw  new ServiceException(ExceptionConstants.UPDATE_FAIL);
		}
		log.info("修改成功");
		return data;
	}


	/**
	 * 根据ids批量删除字典项
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData deleteBatchSysDicItemByIds(SysDicItem request){
		log.info("根据ids批量删除字典项,请求参数====>"+JSON.toJSONString(request));
		//检查ids是否为空
		checkParamsIds(request);
		log.info("参数校验成功,ids不为空");
		ResultData data = new ResultData();
		//根据ids批量删除字典项
		int i = sysDicItemMapper.deleteBatchIds(request.getIds());
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
	public void checkParamsId(SysDicItem request){
		 if(CommonUtil.isStringEmpty(request.getId())){
            throw  new ServiceException(ExceptionConstants.ID_NOT_NULL);
        }
	}

	/**
	 * 检查参数中的ids是否为空
	 * @param request
	 */
	public void  checkParamsIds(SysDicItem request) {
		if (CommonUtil.isCollectionEmpty(request.getIds())) {
			throw new ServiceException(ExceptionConstants.IDS_NOT_NULL);
		}
	}

	/**
	 * 检查添加参数是否齐全
	 * @param request
	 */
	public void checkParamsForAdd(SysDicItem request){
		//判断字典编号是否为空
		if(CommonUtil.isStringEmpty(request.getDicId())){
            throw  new ServiceException(ExceptionConstants.DICID_NOT_NULL);
        }
		//判断字典项名称是否为空
		if(CommonUtil.isStringEmpty(request.getDicItemName())){
            throw  new ServiceException(ExceptionConstants.DICITEMNAME_NOT_NULL);
        }
	}

}