package org.ewlive.controller.system;

import com.baomidou.mybatisplus.plugins.Page;
import org.ewlive.aop.AuthReq;
import org.ewlive.aop.SysLog;
import org.ewlive.entity.system.SysUser;
import org.ewlive.result.ResultData;
import org.ewlive.service.system.SysUserService;
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
    @SysLog(description = "根据id查询用户",syslog = true)
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
    @SysLog(description = "多条件查询用户",syslog = true)
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
    @SysLog(description = "添加用户",syslog = true)
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
    @SysLog(description = "根据id修改用户",syslog = true)
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
    @SysLog(description = "根据ids批量删除用户",syslog = true)
    @RequestMapping("/deleteBatchSysUserByIds")
    public ResultData deleteBatchSysUserByIds(@RequestBody SysUser request) {
        return sysUserService.deleteBatchSysUserByIds(request);
    }

    /**
     * 用户登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/authLogin")
    public ResultData<SysUser> authLogin(@RequestBody SysUser request) {
        return sysUserService.authLogin(request);
    }

    /**
     * 用户登出
     *
     * @param sysUser
     * @return
     */
    @AuthReq
    @SysLog(description = "用户登出",syslog = true)
    @RequestMapping("/loginOut")
    public ResultData loginOut(@RequestBody SysUser sysUser) {
        return sysUserService.loginOut(sysUser);
    }

    /**
     * 验证token是否有效
     *
     * @param sysUser
     * @return
     */
    @RequestMapping("/validateToken")
    public ResultData validateToken(@RequestBody SysUser sysUser) {
        return sysUserService.validateToken(sysUser);
    }


    /**
     * 模糊查询用户(分页)
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "模糊查询用户(分页)",syslog = true)
    @RequestMapping("/likeSearchSysUserByPage")
    public ResultData<Page<SysUser>> likeSearchSysUserByPage(@RequestBody SysUser request){
        return sysUserService.likeSearchSysUserByPage(request);
    }

}