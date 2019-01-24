package org.ewlive.controller.system;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ewlive.aop.AuthReq;
import org.ewlive.aop.SysLog;
import org.ewlive.entity.system.SysMenu;
import org.ewlive.result.ResultData;
import org.ewlive.service.system.SysMenuService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单Controller
 * Create by yangjie on 2018/12/18
 */
@Api(description = "系统菜单")
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
    @SysLog(description = "根据id查询菜单",syslog = true)
    @ApiOperation(value = "根据id查询菜单")
    @RequestMapping(value = "/getSysMenuById", method = RequestMethod.POST)
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
    @SysLog(description = "多条件查询菜单",syslog = true)
    @ApiOperation(value = "多条件查询菜单")
    @RequestMapping(value = "/getSysMenuByParams", method = RequestMethod.POST)
    public ResultData<List<SysMenu>> getSysMenuByParams(@RequestBody SysMenu request) {
        return sysMenuService.getSysMenuByParams(request);
    }

    /**
     * 查询菜单(层级展示)
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "查询菜单(层级展示)",syslog = true)
    @ApiOperation(value = "查询菜单(层级展示)")
    @RequestMapping(value = "/getSysMenuTree", method = RequestMethod.POST)
    public ResultData<List<SysMenu>> getSysMenuTree(@RequestBody SysMenu request) {
        return sysMenuService.getSysMenuTree(request);
    }



    /**
     * 模糊查询菜单(分页)
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "模糊查询菜单(分页)",syslog = true)
    @ApiOperation(value = "模糊查询菜单(分页)")
    @RequestMapping(value = "/likeSearchSysMenuByPage", method = RequestMethod.POST)
    public ResultData<Page<SysMenu>> likeSearchSysMenuByPage(@RequestBody SysMenu request) {
        return sysMenuService.likeSearchSysMenuByPage(request);
    }

    /**
     * 模糊查询菜单(层级展示,分页)
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "模糊查询菜单(层级展示,分页)",syslog = true)
    @ApiOperation(value = "模糊查询菜单(层级展示,分页)")
    @RequestMapping(value = "/likeSearchSysMenuTreeByPage", method = RequestMethod.POST)
    public ResultData<Page<SysMenu>> likeSearchSysMenuTreeByPage(@RequestBody SysMenu request) {
        return sysMenuService.likeSearchSysMenuTreeByPage(request);
    }

    /**
     * 添加菜单
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "添加菜单",syslog = true)
    @ApiOperation(value = "添加菜单")
    @RequestMapping(value = "/addSysMenu", method = RequestMethod.POST)
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
    @SysLog(description = "根据id修改菜单",syslog = true)
    @ApiOperation(value = "根据id修改菜单")
    @RequestMapping(value = "/updateSysMenuById", method = RequestMethod.POST)
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
    @SysLog(description = "根据ids批量删除菜单",syslog = true)
    @ApiOperation(value = "根据ids批量删除菜单")
    @RequestMapping(value = "/deleteBatchSysMenuByIds", method = RequestMethod.POST)
    public ResultData deleteBatchSysMenuByIds(@RequestBody SysMenu request) {
        return sysMenuService.deleteBatchSysMenuByIds(request);
    }

}