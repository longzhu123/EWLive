package org.ewlive.service;

import javax.annotation.Resource;

import org.ewlive.entity.system.SysFileInfo;
import org.ewlive.entity.system.SysUser;
import org.ewlive.mapper.system.SysFileInfoMapper;
import org.ewlive.service.system.SysFileInfoService;
import org.ewlive.util.DicConvertUtil;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.ewlive.constants.ExceptionConstants;
import org.ewlive.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.ewlive.result.ResultData;
import org.ewlive.util.CommonUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.ewlive.entity.SysTest;
import org.ewlive.mapper.SysTestMapper;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.plugins.Page;
import com.alibaba.fastjson.JSON;

/**
 * 系统测试Service 
 * Create by yangjie on 2019/05/18 
 */
@Slf4j
@Service
public class SysTestService{

	@Resource
	private SysTestMapper sysTestMapper;

	@Resource
	private SysFileInfoService sysFileInfoService;
	/**
	 * 根据id查询系统测试
	 * @param request
	 * @return
	 */
	public  ResultData<SysTest> getSysTestById(SysTest request){
		//检查参数Id是否为空
		checkParamsId(request);
		log.info("根据id查询系统测试:请求参数=====>"+JSON.toJSONString(request));
		ResultData<SysTest> data= new ResultData<>();
		//根据id查询系统测试
		SysTest sysTest = sysTestMapper.selectById(request.getId());
		SysTest dataObj = DicConvertUtil.convertObjDicDesc(sysTest,SysTest.class);
		data.setData(dataObj);
		log.info("数据请求成功,=====>返回:"+JSON.toJSONString(dataObj));
		return data;
	}


	/**
	 * 多条件查询系统测试
	 * @param request
	 * @return
	 */
	public ResultData<List<SysTest>> getSysTestByParams(SysTest request){
		log.info("多条件查询系统测试信息:请求参数=====>"+JSON.toJSONString(request));
		ResultData<List<SysTest>> data= new ResultData<>();
		//多条件查询系统测试信息
		List<SysTest> sysTestList = sysTestMapper.selectList(new EntityWrapper<>(request));
		List<SysTest> dataObj = DicConvertUtil.convertArrayDicDesc(sysTestList,SysTest.class);
		data.setData(dataObj);
		log.info("数据请求成功,=====>返回:"+JSON.toJSONString(dataObj));
		return data;
	}


	/**
	 * 模糊查询系统测试(分页)
	 * @param request
	 * @return
	 */
	public ResultData<Page<SysTest>> likeSearchSysTestByPage(SysTest request){
		log.info("模糊查询系统测试(分页):请求参数=====>"+JSON.toJSONString(request));
		ResultData<Page<SysTest>> data= new ResultData<>();
		Page<SysTest> page = new Page<>(request.getCurrent(),request.getSize());
		//模糊查询系统测试(分页)
		List<SysTest> sysTestList = sysTestMapper.likeSearchSysTestByPage(page,request);
		List<SysTest> dataObj = DicConvertUtil.convertArrayDicDesc(sysTestList,SysTest.class);
		page.setRecords(dataObj);
		data.setData(page);
		log.info("数据请求成功,=====>返回:"+JSON.toJSONString(sysTestList));
		return data;
	}


	/**
	 * 添加系统测试
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData addSysTest(SysTest request){
		log.info("添加系统测试,请求参数====>"+JSON.toJSONString(request));
		//检查必填参数项是否空
		checkParamsForAdd(request);
		log.info("添加====>参数校验成功");
		ResultData data = new ResultData();
		//获取当前用户
		SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
		request.setId(CommonUtil.createUUID());
		request.setCreateTime(new Timestamp(System.currentTimeMillis()));
		request.setCreateUserId(currentUser.getId());
		request.setAboutFile(CommonUtil.TrimEnd(request.getAboutFile()));
		//添加系统测试
		int i = sysTestMapper.addSysTest(request);

		//附件级联映射
		SysFileInfo fileReq = new SysFileInfo();
		fileReq.setIds(Arrays.asList(request.getAboutFile().split(",")));
		fileReq.setFkId(request.getId());
		sysFileInfoService.updateFkIdByIds(fileReq);

		if(i == 0){
			throw  new ServiceException(ExceptionConstants.ADD_FAIL);
		}
		log.info("添加成功");
		return data;
	}


	/**
	 * 根据id修改系统测试
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData updateSysTestById(SysTest request){
		log.info("修改系统测试,请求参数====>"+JSON.toJSONString(request));
		//检查id是否为空
		checkParamsId(request);
		log.info("参数校验成功,id不为空");
		ResultData data = new ResultData();
		//获取当前用户
		SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
		request.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		request.setUpdateUserId(currentUser.getId());
		//根据Id修改系统测试
		int i = sysTestMapper.updateSysTestById(request);
		if(i == 0){
			throw  new ServiceException(ExceptionConstants.UPDATE_FAIL);
		}
		log.info("修改成功");
		return data;
	}


	/**
	 * 根据ids批量删除系统测试
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResultData deleteBatchSysTestByIds(SysTest request){
		log.info("根据ids批量删除系统测试,请求参数====>"+JSON.toJSONString(request));
		//检查ids是否为空
		checkParamsIds(request);
		log.info("参数校验成功,ids不为空");
		ResultData data = new ResultData();
		//根据ids批量删除系统测试
		int i = sysTestMapper.deleteBatchIds(request.getIds());
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
	public void checkParamsId(SysTest request){
		 if(CommonUtil.isStringEmpty(request.getId())){
            throw  new ServiceException(ExceptionConstants.ID_NOT_NULL);
        }
	}

	/**
	 * 检查参数中的ids是否为空
	 * @param request
	 */
	public void  checkParamsIds(SysTest request) {
		if (CommonUtil.isCollectionEmpty(request.getIds())) {
			throw new ServiceException(ExceptionConstants.IDS_NOT_NULL);
		}
	}

	/**
	 * 检查添加参数是否齐全
	 * @param request
	 */
	public void checkParamsForAdd(SysTest request){
		//判断姓名是否为空
		if(CommonUtil.isStringEmpty(request.getName())){
            throw  new ServiceException("姓名不能为空");
        }
		//判断昵称是否为空
		if(CommonUtil.isStringEmpty(request.getNickName())){
            throw  new ServiceException("昵称不能为空");
        }
		//判断开播状态是否为空
		if(CommonUtil.isStringEmpty(request.getPlayState())){
            throw  new ServiceException("开播状态不能为空");
        }
		//判断开播时间是否为空
		if(Objects.isNull(request.getPlayTime())){
            throw  new ServiceException("开播时间不能为空");
        }
		//判断相关附件是否为空
		if(CommonUtil.isStringEmpty(request.getAboutFile())){
            throw  new ServiceException("相关附件不能为空");
        }
	}

}