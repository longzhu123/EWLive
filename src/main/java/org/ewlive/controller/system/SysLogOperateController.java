package org.ewlive.controller.system;

import java.util.List;

import org.ewlive.aop.AuthReq;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import org.ewlive.result.ResultData;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.ewlive.entity.system.SysLogOperate;
import org.ewlive.service.system.SysLogOperateService;

/**
 * 操作日志Controller
 * Create by yangjie on 2018/12/26
 */
@RestController
@RequestMapping("/sysLogOperate")
public class SysLogOperateController {

    @Resource
    private SysLogOperateService sysLogOperateService;

    /**
     * 根据id查询操作日志
     *
     * @param request
     * @return
     */
    @RequestMapping("/getSysLogOperateById")
    public ResultData<SysLogOperate> getSysLogOperateById(@RequestBody SysLogOperate request) {
        return sysLogOperateService.getSysLogOperateById(request);
    }

    /**
     * 多条件查询操作日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/getSysLogOperateByParams")
    public ResultData<List<SysLogOperate>> getSysLogOperateByParams(@RequestBody SysLogOperate request) {
        return sysLogOperateService.getSysLogOperateByParams(request);
    }

    /**
     * 模糊查询操作日志(分页)
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/likeSearchSysLogOperateByPage")
    public ResultData<Page<SysLogOperate>> likeSearchSysLogOperateByPage(@RequestBody SysLogOperate request) {
        return sysLogOperateService.likeSearchSysLogOperateByPage(request);
    }

    /**
     * 添加操作日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/addSysLogOperate")
    public ResultData addSysLogOperate(@RequestBody SysLogOperate request) {
        return sysLogOperateService.addSysLogOperate(request);
    }

    /**
     * 根据id修改操作日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/updateSysLogOperateById")
    public ResultData updateSysLogOperateById(@RequestBody SysLogOperate request) {
        return sysLogOperateService.updateSysLogOperateById(request);
    }

    /**
     * 根据ids批量删除操作日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @RequestMapping("/deleteBatchSysLogOperateByIds")
    public ResultData deleteBatchSysLogOperateByIds(@RequestBody SysLogOperate request) {
        return sysLogOperateService.deleteBatchSysLogOperateByIds(request);
    }

}