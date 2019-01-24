package org.ewlive.controller.system;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.ewlive.aop.AuthReq;
import org.ewlive.aop.SysLog;
import org.ewlive.entity.system.SysUserRole;
import org.ewlive.result.ResultData;
import org.ewlive.service.system.SysUserRoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色Controller
 * Create by yangjie on 2018/12/11
 */
@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleController {

    @Resource
    private SysUserRoleService sysUserRoleService;

    /**
     * 根据id查询用户角色
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id查询用户角色",syslog = true)
    @ApiOperation(value = "根据id查询用户角色")
    @RequestMapping(value = "/getSysUserRoleById", method = RequestMethod.POST)
    public ResultData<SysUserRole> getSysUserRoleById(@RequestBody SysUserRole request) {
        return sysUserRoleService.getSysUserRoleById(request);
    }

    /**
     * 多条件查询用户角色
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "多条件查询用户角色",syslog = true)
    @ApiOperation(value = "多条件查询用户角色")
    @RequestMapping(value = "/getSysUserRoleByParams", method = RequestMethod.POST)
    public ResultData<List<SysUserRole>> getSysUserRoleByParams(@RequestBody SysUserRole request) {
        return sysUserRoleService.getSysUserRoleByParams(request);
    }

    /**
     * 模糊查询用户角色(分页)
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "模糊查询用户角色(分页)",syslog = true)
    @ApiOperation(value = "模糊查询用户角色(分页)")
    @RequestMapping(value = "/likeSearchSysUserRoleByPage", method = RequestMethod.POST)
    public ResultData<Page<SysUserRole>> likeSearchSysUserRoleByPage(@RequestBody SysUserRole request) {
        return sysUserRoleService.likeSearchSysUserRoleByPage(request);
    }

    /**
     * 添加用户角色
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "添加用户角色",syslog = true)
    @ApiOperation(value = "添加用户角色")
    @RequestMapping(value = "/addSysUserRole", method = RequestMethod.POST)
    public ResultData addSysUserRole(@RequestBody SysUserRole request) {
        return sysUserRoleService.addSysUserRole(request);
    }

    /**
     * 根据id修改用户角色
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id修改用户角色",syslog = true)
    @ApiOperation(value = "根据id修改用户角色")
    @RequestMapping(value = "/updateSysUserRoleById", method = RequestMethod.POST)
    public ResultData updateSysUserRoleById(@RequestBody SysUserRole request) {
        return sysUserRoleService.updateSysUserRoleById(request);
    }

    /**
     * 根据ids批量删除用户角色
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据ids批量删除用户角色",syslog = true)
    @ApiOperation(value = "根据ids批量删除用户角色")
    @RequestMapping(value = "/deleteBatchSysUserRoleByIds", method = RequestMethod.POST)
    public ResultData deleteBatchSysUserRoleByIds(@RequestBody SysUserRole request) {
        return sysUserRoleService.deleteBatchSysUserRoleByIds(request);
    }

}