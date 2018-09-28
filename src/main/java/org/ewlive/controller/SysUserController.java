package org.ewlive.controller;

import org.ewlive.aop.AuthReq;
import org.ewlive.entity.SysUser;
import org.ewlive.result.ResultData;
import org.ewlive.service.SysUserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户Controller
 * Create by yangjie on 2018/06/07
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 根据id查询用户
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/getSysUserById")
    public ResultData<SysUser> getSysUserById(@RequestBody SysUser request) {
        return sysUserService.getSysUserById(request);
    }

    /**
     * 多条件查询用户
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/getSysUserByParams")
    public ResultData<List<SysUser>> getSysUserByParams(@RequestBody SysUser request) {
        return sysUserService.getSysUserByParams(request);
    }

    /**
     * 添加用户
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/addSysUser")
    public ResultData addSysUser(@RequestBody SysUser request) {
        return sysUserService.addSysUser(request);
    }

    /**
     * 根据id修改用户
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/updateSysUserById")
    public ResultData updateSysUserById(@RequestBody SysUser request) {
        return sysUserService.updateSysUserById(request);
    }

    /**
     * 根据ids批量删除用户
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/deleteBatchSysUserByIds")
    public ResultData deleteBatchSysUserByIds(@RequestBody SysUser request) {
        return sysUserService.deleteBatchSysUserByIds(request);
    }

    /**
     * 用户登录
     * @param request
     * @return
     */
    @RequestMapping("/authLogin")
    public  ResultData<SysUser> authLogin(@RequestBody SysUser request){
        return sysUserService.authLogin(request);
    }

}