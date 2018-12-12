package org.ewlive.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.ewlive.aop.AuthReq;
import org.ewlive.entity.SysUserRoleRealtion;
import org.ewlive.result.ResultData;
import org.ewlive.service.SysUserRoleRealtionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户角色关系Controller
 * Create by yangjie on 2018/12/12
 */
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
    @RequestMapping("/getSysUserRoleRealtionById")
    public ResultData<SysUserRoleRealtion> getSysUserRoleRealtionById(@RequestBody SysUserRoleRealtion request) {
        return sysUserRoleRealtionService.getSysUserRoleRealtionById(request);
    }

    /**
     * 模糊查询用户角色关系(分页)
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/likeSearchSysUserRoleRealtionByPage")
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
    @RequestMapping("/addSysUserRoleRealtion")
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
    @RequestMapping("/updateSysUserRoleRealtionById")
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
    @RequestMapping("/deleteBatchSysUserRoleRealtionByIds")
    public ResultData deleteBatchSysUserRoleRealtionByIds(@RequestBody SysUserRoleRealtion request) {
        return sysUserRoleRealtionService.deleteBatchSysUserRoleRealtionByIds(request);
    }

}