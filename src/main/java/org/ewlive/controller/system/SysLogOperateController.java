package org.ewlive.controller.system;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.ewlive.aop.AuthReq;
import org.ewlive.aop.SysLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import org.ewlive.result.ResultData;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.ewlive.entity.system.SysLogOperate;
import org.ewlive.service.system.SysLogOperateService;

/**
 * 操作日志Controller
 * Create by yangjie on 2018/12/26
 */
@RestController
@RequestMapping("/sysLogOperate")
public class SysLogOperateController {

    @Resource
    private SysLogOperateService sysLogOperateService;

    /**
     * 根据id查询操作日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id查询操作日志",syslog = true)
    @ApiOperation(value = "根据id查询操作日志")
    @RequestMapping(value = "/getSysLogOperateById", method = RequestMethod.POST)
    public ResultData<SysLogOperate> getSysLogOperateById(@RequestBody SysLogOperate request) {
        return sysLogOperateService.getSysLogOperateById(request);
    }

    /**
     * 多条件查询操作日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "多条件查询操作日志",syslog = true)
    @ApiOperation(value = "多条件查询操作日志")
    @RequestMapping(value = "/getSysLogOperateByParams", method = RequestMethod.POST)
    public ResultData<List<SysLogOperate>> getSysLogOperateByParams(@RequestBody SysLogOperate request) {
        return sysLogOperateService.getSysLogOperateByParams(request);
    }

    /**
     * 模糊查询操作日志(分页)
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "模糊查询操作日志(分页)",syslog = true)
    @ApiOperation(value = "模糊查询操作日志(分页)")
    @RequestMapping(value = "/likeSearchSysLogOperateByPage", method = RequestMethod.POST)
    public ResultData<Page<SysLogOperate>> likeSearchSysLogOperateByPage(@RequestBody SysLogOperate request) {
        return sysLogOperateService.likeSearchSysLogOperateByPage(request);
    }

    /**
     * 添加操作日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "添加操作日志",syslog = true)
    @ApiOperation(value = "添加操作日志")
    @RequestMapping(value = "/addSysLogOperate", method = RequestMethod.POST)
    public ResultData addSysLogOperate(@RequestBody SysLogOperate request) {
        return sysLogOperateService.addSysLogOperate(request);
    }

    /**
     * 根据id修改操作日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id修改操作日志",syslog = true)
    @ApiOperation(value = "根据id修改操作日志")
    @RequestMapping(value = "/updateSysLogOperateById", method = RequestMethod.POST)
    public ResultData updateSysLogOperateById(@RequestBody SysLogOperate request) {
        return sysLogOperateService.updateSysLogOperateById(request);
    }

    /**
     * 根据ids批量删除操作日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据ids批量删除操作日志",syslog = true)
    @ApiOperation(value = "根据ids批量删除操作日志")
    @RequestMapping(value = "/deleteBatchSysLogOperateByIds", method = RequestMethod.POST)
    public ResultData deleteBatchSysLogOperateByIds(@RequestBody SysLogOperate request) {
        return sysLogOperateService.deleteBatchSysLogOperateByIds(request);
    }

}