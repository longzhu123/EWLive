package org.ewlive.controller.system;

import com.baomidou.mybatisplus.plugins.Page;
import org.ewlive.aop.AuthReq;
import org.ewlive.entity.system.SysLogLogin;
import org.ewlive.result.ResultData;
import org.ewlive.service.system.SysLogLoginService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 登录日志Controller
 * Create by yangjie on 2019/01/09
 */
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
    @RequestMapping("/getSysLogLoginById")
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
    @RequestMapping("/getSysLogLoginByParams")
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
    @RequestMapping("/likeSearchSysLogLoginByPage")
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
    @RequestMapping("/addSysLogLogin")
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
    @RequestMapping("/updateSysLogLoginById")
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
    @RequestMapping("/deleteBatchSysLogLoginByIds")
    public ResultData deleteBatchSysLogLoginByIds(@RequestBody SysLogLogin request) {
        return sysLogLoginService.deleteBatchSysLogLoginByIds(request);
    }

}