package org.ewlive.controller.system;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ewlive.aop.AuthReq;
import org.ewlive.aop.SysLog;
import org.ewlive.entity.system.SysDic;
import org.ewlive.result.ResultData;
import org.ewlive.service.system.SysDicService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典Controller
 * Create by yangjie on 2018/11/16
 */
@Api(description = "系统字典")
@RestController
@RequestMapping("/sysDic")
public class SysDicController {

    @Resource
    private SysDicService sysDicService;

    /**
     * 根据id查询字典
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id查询字典", syslog = true)
    @ApiOperation(value = "根据id查询字典")
    @RequestMapping(value = "/getSysDicById", method = RequestMethod.POST)
    public ResultData<SysDic> getSysDicById(@RequestBody SysDic request) {
        return sysDicService.getSysDicById(request);
    }

    /**
     * 多条件查询字典
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "多条件查询字典", syslog = true)
    @ApiOperation(value = "多条件查询字典")
    @RequestMapping(value = "/getSysDicByParams", method = RequestMethod.POST)
    public ResultData<List<SysDic>> getSysDicByParams(@RequestBody SysDic request) {
        return sysDicService.getSysDicByParams(request);
    }

    /**
     * 模糊查询字典(分页)
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "模糊查询字典(分页)", syslog = true)
    @ApiOperation(value = "模糊查询字典(分页)")
    @RequestMapping(value = "/likeSearchSysDicByPage", method = RequestMethod.POST)
    public ResultData<Page<SysDic>> likeSearchSysDicByPage(@RequestBody SysDic request) {
        return sysDicService.likeSearchSysDicByPage(request);
    }


    /**
     * 添加字典
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "添加字典", syslog = true)
    @ApiOperation(value = "添加字典")
    @RequestMapping(value = "/addSysDic", method = RequestMethod.POST)
    public ResultData addSysDic(@RequestBody SysDic request) {
        return sysDicService.addSysDic(request);
    }

    /**
     * 根据id修改字典
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id修改字典", syslog = true)
    @ApiOperation(value = "根据id修改字典")
    @RequestMapping(value = "/updateSysDicById", method = RequestMethod.POST)
    public ResultData updateSysDicById(@RequestBody SysDic request) {
        return sysDicService.updateSysDicById(request);
    }

    /**
     * 根据ids批量删除字典
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据ids批量删除字典", syslog = true)
    @ApiOperation(value = "根据ids批量删除字典")
    @RequestMapping(value = "/deleteBatchSysDicByIds", method = RequestMethod.POST)
    public ResultData deleteBatchSysDicByIds(@RequestBody SysDic request) {
        return sysDicService.deleteBatchSysDicByIds(request);
    }

}