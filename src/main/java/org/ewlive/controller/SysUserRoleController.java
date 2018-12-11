package org.ewlive.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.ewlive.entity.SysUserRole;
import org.ewlive.result.ResultData;
import org.ewlive.service.SysUserRoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    @RequestMapping("/getSysUserRoleById")
    public ResultData<SysUserRole> getSysUserRoleById(@RequestBody SysUserRole request) {
        return sysUserRoleService.getSysUserRoleById(request);
    }

    /**
     * 模糊查询用户角色(分页)
     *
     * @param request
     * @return
     */
    @RequestMapping("/likeSearchSysUserRoleByPage")
    public ResultData<Page<SysUserRole>> likeSearchSysUserRoleByPage(@RequestBody SysUserRole request) {
        return sysUserRoleService.likeSearchSysUserRoleByPage(request);
    }

    /**
     * 添加用户角色
     *
     * @param request
     * @return
     */
    @RequestMapping("/addSysUserRole")
    public ResultData addSysUserRole(@RequestBody SysUserRole request) {
        return sysUserRoleService.addSysUserRole(request);
    }

    /**
     * 根据id修改用户角色
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateSysUserRoleById")
    public ResultData updateSysUserRoleById(@RequestBody SysUserRole request) {
        return sysUserRoleService.updateSysUserRoleById(request);
    }

    /**
     * 根据ids批量删除用户角色
     *
     * @param request
     * @return
     */
    @RequestMapping("/deleteBatchSysUserRoleByIds")
    public ResultData deleteBatchSysUserRoleByIds(@RequestBody SysUserRole request) {
        return sysUserRoleService.deleteBatchSysUserRoleByIds(request);
    }

}