package org.ewlive.controller.system;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ewlive.aop.AuthReq;
import org.ewlive.aop.SysLog;
import org.ewlive.entity.system.SysLogLogin;
import org.ewlive.result.ResultData;
import org.ewlive.service.system.SysLogLoginService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 登录日志Controller
 * Create by yangjie on 2019/01/09
 */
@Api(description = "系统登录日志")
@RestController
@RequestMapping("/sysLogLogin")
public class SysLogLoginController {

    @Resource
    private SysLogLoginService sysLogLoginService;

    /**
     * 根据id查询登录日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id查询登录日志",syslog = true)
    @ApiOperation(value = "根据id查询登录日志")
    @RequestMapping(value = "/getSysLogLoginById", method = RequestMethod.POST)
    public ResultData<SysLogLogin> getSysLogLoginById(@RequestBody SysLogLogin request) {
        return sysLogLoginService.getSysLogLoginById(request);
    }

    /**
     * 多条件查询登录日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "多条件查询登录日志",syslog = true)
    @ApiOperation(value = "多条件查询登录日志")
    @RequestMapping(value = "/getSysLogLoginByParams", method = RequestMethod.POST)
    public ResultData<List<SysLogLogin>> getSysLogLoginByParams(@RequestBody SysLogLogin request) {
        return sysLogLoginService.getSysLogLoginByParams(request);
    }

    /**
     * 模糊查询登录日志(分页)
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "模糊查询登录日志(分页)",syslog = true)
    @ApiOperation(value = "模糊查询登录日志(分页)")
    @RequestMapping(value = "/likeSearchSysLogLoginByPage", method = RequestMethod.POST)
    public ResultData<Page<SysLogLogin>> likeSearchSysLogLoginByPage(@RequestBody SysLogLogin request) {
        return sysLogLoginService.likeSearchSysLogLoginByPage(request);
    }

    /**
     * 添加登录日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "添加登录日志",syslog = true)
    @ApiOperation(value = "添加登录日志")
    @RequestMapping(value = "/addSysLogLogin", method = RequestMethod.POST)
    public ResultData addSysLogLogin(@RequestBody SysLogLogin request) {
        return sysLogLoginService.addSysLogLogin(request);
    }

    /**
     * 根据id修改登录日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id修改登录日志",syslog = true)
    @ApiOperation(value = "根据id修改登录日志")
    @RequestMapping(value = "/updateSysLogLoginById", method = RequestMethod.POST)
    public ResultData updateSysLogLoginById(@RequestBody SysLogLogin request) {
        return sysLogLoginService.updateSysLogLoginById(request);
    }

    /**
     * 根据ids批量删除登录日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据ids批量删除登录日志",syslog = true)
    @ApiOperation(value = "根据ids批量删除登录日志")
    @RequestMapping(value = "/deleteBatchSysLogLoginByIds", method = RequestMethod.POST)
    public ResultData deleteBatchSysLogLoginByIds(@RequestBody SysLogLogin request) {
        return sysLogLoginService.deleteBatchSysLogLoginByIds(request);
    }

}