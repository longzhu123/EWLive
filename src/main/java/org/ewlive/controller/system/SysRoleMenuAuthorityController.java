package org.ewlive.controller.system;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.ewlive.aop.AuthReq;
import org.ewlive.aop.SysLog;
import org.ewlive.entity.system.SysRoleMenuAuthority;
import org.ewlive.result.ResultData;
import org.ewlive.service.system.SysRoleMenuAuthorityService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色菜单权限Controller
 * Create by yangjie on 2019/01/08
 */
@RestController
@RequestMapping("/sysRoleMenuAuthority")
public class SysRoleMenuAuthorityController {

    @Resource
    private SysRoleMenuAuthorityService sysRoleMenuAuthorityService;

    /**
     * 根据id查询角色菜单权限
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id查询角色菜单权限",syslog = true)
    @ApiOperation(value = "根据id查询角色菜单权限")
    @RequestMapping(value = "/getSysRoleMenuAuthorityById", method = RequestMethod.POST)
    public ResultData<SysRoleMenuAuthority> getSysRoleMenuAuthorityById(@RequestBody SysRoleMenuAuthority request) {
        return sysRoleMenuAuthorityService.getSysRoleMenuAuthorityById(request);
    }

    /**
     * 多条件查询角色菜单权限
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "多条件查询角色菜单权限",syslog = true)
    @ApiOperation(value = "多条件查询角色菜单权限")
    @RequestMapping(value = "/getSysRoleMenuAuthorityByParams", method = RequestMethod.POST)
    public ResultData<List<SysRoleMenuAuthority>> getSysRoleMenuAuthorityByParams(@RequestBody SysRoleMenuAuthority request) {
        return sysRoleMenuAuthorityService.getSysRoleMenuAuthorityByParams(request);
    }

    /**
     * 模糊查询角色菜单权限(分页)
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "模糊查询角色菜单权限(分页)",syslog = true)
    @ApiOperation(value = "模糊查询角色菜单权限(分页)")
    @RequestMapping(value = "/likeSearchSysRoleMenuAuthorityByPage", method = RequestMethod.POST)
    public ResultData<Page<SysRoleMenuAuthority>> likeSearchSysRoleMenuAuthorityByPage(@RequestBody SysRoleMenuAuthority request) {
        return sysRoleMenuAuthorityService.likeSearchSysRoleMenuAuthorityByPage(request);
    }

    /**
     * 添加角色菜单权限
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "添加角色菜单权限",syslog = true)
    @ApiOperation(value = "添加角色菜单权限")
    @RequestMapping(value = "/addSysRoleMenuAuthority", method = RequestMethod.POST)
    public ResultData addSysRoleMenuAuthority(@RequestBody SysRoleMenuAuthority request) {
        return sysRoleMenuAuthorityService.addSysRoleMenuAuthority(request);
    }

    /**
     * 根据id修改角色菜单权限
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id修改角色菜单权限",syslog = true)
    @ApiOperation(value = "根据id修改角色菜单权限")
    @RequestMapping(value = "/updateSysRoleMenuAuthorityById", method = RequestMethod.POST)
    public ResultData updateSysRoleMenuAuthorityById(@RequestBody SysRoleMenuAuthority request) {
        return sysRoleMenuAuthorityService.updateSysRoleMenuAuthorityById(request);
    }

    /**
     * 根据ids批量删除角色菜单权限
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据ids批量删除角色菜单权限",syslog = true)
    @ApiOperation(value = "根据ids批量删除角色菜单权限")
    @RequestMapping(value = "/deleteBatchSysRoleMenuAuthorityByIds", method = RequestMethod.POST)
    public ResultData deleteBatchSysRoleMenuAuthorityByIds(@RequestBody SysRoleMenuAuthority request) {
        return sysRoleMenuAuthorityService.deleteBatchSysRoleMenuAuthorityByIds(request);
    }

}