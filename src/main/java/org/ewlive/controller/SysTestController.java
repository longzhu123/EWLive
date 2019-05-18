package org.ewlive.controller;
import java.util.List;

import org.ewlive.aop.AuthReq;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

import org.ewlive.result.ResultData;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.ewlive.entity.SysTest;
import org.ewlive.service.SysTestService;
import org.ewlive.aop.SysLog;

/**
 * 系统测试Controller
 * Create by yangjie on 2019/05/18
 */
@RestController
@RequestMapping("/sysTest")
public class SysTestController{

	@Resource
	private SysTestService sysTestService;

	/**
	 * 根据id查询系统测试
	 * @param request
	 * @return
	 */
	@AuthReq
	@SysLog(description = "根据id修改系统测试",syslog = true)
	@RequestMapping("/getSysTestById")
	public ResultData<SysTest> getSysTestById(@RequestBody SysTest request){
		return sysTestService.getSysTestById(request);
	}

	/**
	 * 多条件查询系统测试
	 * @param request
	 * @return
	 */
	@AuthReq
	@SysLog(description = "多条件查询系统测试",syslog = true)
	@RequestMapping("/getSysTestByParams")
	public ResultData<List<SysTest>> getSysTestByParams(@RequestBody SysTest request){
		return sysTestService.getSysTestByParams(request);
	}

	/**
	 * 模糊查询系统测试(分页)
	 * @param request
	 * @return
	 */
	@AuthReq
	@SysLog(description = "模糊查询系统测试",syslog = true)
	@RequestMapping("/likeSearchSysTestByPage")
	public ResultData<Page<SysTest>> likeSearchSysTestByPage(@RequestBody SysTest request){
		return sysTestService.likeSearchSysTestByPage(request);
	}

	/**
	 * 添加系统测试
	 * @param request
	 * @return
	 */
	@AuthReq
	@SysLog(description = "添加系统测试",syslog = true)
	@RequestMapping("/addSysTest")
	public ResultData addSysTest(@RequestBody SysTest request){
		return sysTestService.addSysTest(request);
	}

	/**
	 * 根据id修改系统测试
	 * @param request
	 * @return
	 */
	@AuthReq
	@SysLog(description = "根据id修改系统测试",syslog = true)
	@RequestMapping("/updateSysTestById")
	public ResultData updateSysTestById(@RequestBody SysTest request){
		return sysTestService.updateSysTestById(request);
	}

	/**
	 * 根据ids批量删除系统测试
	 * @param request
	 * @return
	 */
	@AuthReq
	@SysLog(description = "根据ids批量删除系统测试",syslog = true)
	@RequestMapping("/deleteBatchSysTestByIds")
	public ResultData deleteBatchSysTestByIds(@RequestBody SysTest request){
		return sysTestService.deleteBatchSysTestByIds(request);
	}

}