package org.ewlive.controller;

import java.util.List;

import org.ewlive.aop.AuthReq;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import org.ewlive.result.ResultData;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.ewlive.entity.SysMenu;
import org.ewlive.service.SysMenuService;

/**
 * 菜单Controller
 * Create by yangjie on 2018/12/18
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    /**
     * 根据id查询菜单
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/getSysMenuById")
    public ResultData<SysMenu> getSysMenuById(@RequestBody SysMenu request) {
        return sysMenuService.getSysMenuById(request);
    }

    /**
     * 多条件查询菜单
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/getSysMenuByParams")
    public ResultData<List<SysMenu>> getSysMenuByParams(@RequestBody SysMenu request) {
        return sysMenuService.getSysMenuByParams(request);
    }

    /**
     * 添加菜单
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/addSysMenu")
    public ResultData addSysMenu(@RequestBody SysMenu request) {
        return sysMenuService.addSysMenu(request);
    }

    /**
     * 根据id修改菜单
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/updateSysMenuById")
    public ResultData updateSysMenuById(@RequestBody SysMenu request) {
        return sysMenuService.updateSysMenuById(request);
    }

    /**
     * 根据ids批量删除菜单
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/deleteBatchSysMenuByIds")
    public ResultData deleteBatchSysMenuByIds(@RequestBody SysMenu request) {
        return sysMenuService.deleteBatchSysMenuByIds(request);
    }

}