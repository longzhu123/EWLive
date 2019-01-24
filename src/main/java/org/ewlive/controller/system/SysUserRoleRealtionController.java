package org.ewlive.controller.system;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ewlive.aop.AuthReq;
import org.ewlive.aop.SysLog;
import org.ewlive.entity.system.SysUserRoleRealtion;
import org.ewlive.result.ResultData;
import org.ewlive.service.system.SysUserRoleRealtionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色关系Controller
 * Create by yangjie on 2018/12/12
 */
@Api(description = "系统用户角色关系")
@RestController
@RequestMapping("/sysUserRoleRealtion")
public class SysUserRoleRealtionController {

    @Resource
    private SysUserRoleRealtionService sysUserRoleRealtionService;

    /**
     * 根据id查询用户角色关系
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id查询用户角色关系",syslog = true)
    @ApiOperation(value = "根据id查询用户角色关系")
    @RequestMapping(value = "/getSysUserRoleRealtionById", method = RequestMethod.POST)
    public ResultData<SysUserRoleRealtion> getSysUserRoleRealtionById(@RequestBody SysUserRoleRealtion request) {
        return sysUserRoleRealtionService.getSysUserRoleRealtionById(request);
    }

    /**
     * 多条件查询用户角色关系
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "多条件查询用户角色关系",syslog = true)
    @ApiOperation(value = "多条件查询用户角色关系")
    @RequestMapping(value = "/getSysUserRoleRealtionByParams", method = RequestMethod.POST)
    public ResultData<List<SysUserRoleRealtion>> getSysUserRoleRealtionByParams(@RequestBody SysUserRoleRealtion request) {
        return sysUserRoleRealtionService.getSysUserRoleRealtionByParams(request);
    }


    /**
     * 模糊查询用户角色关系(分页)
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "模糊查询用户角色关系(分页)",syslog = true)
    @ApiOperation(value = "模糊查询用户角色关系(分页)")
    @RequestMapping(value = "/likeSearchSysUserRoleRealtionByPage", method = RequestMethod.POST)
    public ResultData<Page<SysUserRoleRealtion>> likeSearchSysUserRoleRealtionByPage(@RequestBody SysUserRoleRealtion request) {
        return sysUserRoleRealtionService.likeSearchSysUserRoleRealtionByPage(request);
    }

    /**
     * 添加用户角色关系
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "添加用户角色关系",syslog = true)
    @ApiOperation(value = "添加用户角色关系")
    @RequestMapping(value = "/addSysUserRoleRealtion", method = RequestMethod.POST)
    public ResultData addSysUserRoleRealtion(@RequestBody SysUserRoleRealtion request) {
        return sysUserRoleRealtionService.addSysUserRoleRealtion(request);
    }

    /**
     * 根据id修改用户角色关系
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id修改用户角色关系",syslog = true)
    @ApiOperation(value = "根据id修改用户角色关系")
    @RequestMapping(value = "/updateSysUserRoleRealtionById", method = RequestMethod.POST)
    public ResultData updateSysUserRoleRealtionById(@RequestBody SysUserRoleRealtion request) {
        return sysUserRoleRealtionService.updateSysUserRoleRealtionById(request);
    }

    /**
     * 根据ids批量删除用户角色关系
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据ids批量删除用户角色关系",syslog = true)
    @ApiOperation(value = "根据ids批量删除用户角色关系")
    @RequestMapping(value = "/deleteBatchSysUserRoleRealtionByIds", method = RequestMethod.POST)
    public ResultData deleteBatchSysUserRoleRealtionByIds(@RequestBody SysUserRoleRealtion request) {
        return sysUserRoleRealtionService.deleteBatchSysUserRoleRealtionByIds(request);
    }

}