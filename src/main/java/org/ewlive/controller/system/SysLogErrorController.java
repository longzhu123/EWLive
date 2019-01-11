package org.ewlive.controller.system;

import com.baomidou.mybatisplus.plugins.Page;
import org.ewlive.aop.AuthReq;
import org.ewlive.aop.SysLog;
import org.ewlive.entity.system.SysLogError;
import org.ewlive.result.ResultData;
import org.ewlive.service.system.SysLogErrorService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 异常日志Controller
 * Create by yangjie on 2019/01/09
 */
@RestController
@RequestMapping("/sysLogError")
public class SysLogErrorController {

    @Resource
    private SysLogErrorService sysLogErrorService;

    /**
     * 根据id查询异常日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id查询异常日志",syslog = true)
    @RequestMapping("/getSysLogErrorById")
    public ResultData<SysLogError> getSysLogErrorById(@RequestBody SysLogError request) {
        return sysLogErrorService.getSysLogErrorById(request);
    }

    /**
     * 多条件查询异常日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "多条件查询异常日志",syslog = true)
    @RequestMapping("/getSysLogErrorByParams")
    public ResultData<List<SysLogError>> getSysLogErrorByParams(@RequestBody SysLogError request) {
        return sysLogErrorService.getSysLogErrorByParams(request);
    }

    /**
     * 模糊查询异常日志(分页)
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "模糊查询异常日志(分页)",syslog = true)
    @RequestMapping("/likeSearchSysLogErrorByPage")
    public ResultData<Page<SysLogError>> likeSearchSysLogErrorByPage(@RequestBody SysLogError request) {
        return sysLogErrorService.likeSearchSysLogErrorByPage(request);
    }

    /**
     * 添加异常日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "添加异常日志",syslog = true)
    @RequestMapping("/addSysLogError")
    public ResultData addSysLogError(@RequestBody SysLogError request) {
        return sysLogErrorService.addSysLogError(request);
    }

    /**
     * 根据id修改异常日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id修改异常日志",syslog = true)
    @RequestMapping("/updateSysLogErrorById")
    public ResultData updateSysLogErrorById(@RequestBody SysLogError request) {
        return sysLogErrorService.updateSysLogErrorById(request);
    }

    /**
     * 根据ids批量删除异常日志
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据ids批量删除异常日志",syslog = true)
    @RequestMapping("/deleteBatchSysLogErrorByIds")
    public ResultData deleteBatchSysLogErrorByIds(@RequestBody SysLogError request) {
        return sysLogErrorService.deleteBatchSysLogErrorByIds(request);
    }

}