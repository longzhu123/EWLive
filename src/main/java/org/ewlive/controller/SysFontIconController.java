package org.ewlive.controller;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import org.ewlive.result.ResultData;
import com.baomidou.mybatisplus.plugins.Page;import org.springframework.web.bind.annotation.RequestBody;
import org.ewlive.entity.SysFontIcon;
import org.ewlive.service.SysFontIconService;

/**
 * 系统字体图标Controller
 * Create by yangjie on 2018/12/17
 */
@RestController
@RequestMapping("/sysFontIcon")
public class SysFontIconController{

	@Resource
	private SysFontIconService sysFontIconService;

	/**
	 * 根据id查询系统字体图标
	 * @param request
	 * @return
	 */
	@RequestMapping("/getSysFontIconById")
	public ResultData<SysFontIcon> getSysFontIconById(@RequestBody SysFontIcon request){
		return sysFontIconService.getSysFontIconById(request);
	}

	/**
	 * 多条件查询系统字体图标
	 * @param request
	 * @return
	 */
	@RequestMapping("/getSysFontIconByParams")
	public ResultData<List<SysFontIcon>> getSysFontIconByParams(@RequestBody SysFontIcon request){
		return sysFontIconService.getSysFontIconByParams(request);
	}

	/**
	 * 模糊查询系统字体图标(分页)
	 * @param request
	 * @return
	 */
	@RequestMapping("/likeSearchSysFontIconByPage")
	public ResultData<Page<SysFontIcon>> likeSearchSysFontIconByPage(@RequestBody SysFontIcon request){
		return sysFontIconService.likeSearchSysFontIconByPage(request);
	}

	/**
	 * 添加系统字体图标
	 * @param request
	 * @return
	 */
	@RequestMapping("/addSysFontIcon")
	public ResultData addSysFontIcon(@RequestBody SysFontIcon request){
		return sysFontIconService.addSysFontIcon(request);
	}

	/**
	 * 根据id修改系统字体图标
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateSysFontIconById")
	public ResultData updateSysFontIconById(@RequestBody SysFontIcon request){
		return sysFontIconService.updateSysFontIconById(request);
	}

	/**
	 * 根据ids批量删除系统字体图标
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteBatchSysFontIconByIds")
	public ResultData deleteBatchSysFontIconByIds(@RequestBody SysFontIcon request){
		return sysFontIconService.deleteBatchSysFontIconByIds(request);
	}
}