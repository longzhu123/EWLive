package org.ewlive.controller.system;

import com.baomidou.mybatisplus.plugins.Page;
import org.ewlive.aop.AuthReq;
import org.ewlive.aop.SysLog;
import org.ewlive.entity.system.SysFileInfo;
import org.ewlive.result.ResultData;
import org.ewlive.service.system.SysFileInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 附件信息Controller
 * Create by yangjie on 2019/03/14
 */
@RestController
@RequestMapping("/sysFileInfo")
public class SysFileInfoController {

    @Resource
    private SysFileInfoService sysFileInfoService;

    /**
     * 根据id查询附件信息
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id修改附件信息", syslog = true)
    @RequestMapping("/getSysFileInfoById")
    public ResultData<SysFileInfo> getSysFileInfoById(@RequestBody SysFileInfo request) {
        return sysFileInfoService.getSysFileInfoById(request);
    }

    /**
     * 多条件查询附件信息
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "多条件查询附件信息", syslog = true)
    @RequestMapping("/getSysFileInfoByParams")
    public ResultData<List<SysFileInfo>> getSysFileInfoByParams(@RequestBody SysFileInfo request) {
        return sysFileInfoService.getSysFileInfoByParams(request);
    }

    /**
     * 模糊查询附件信息(分页)
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "模糊查询附件信息", syslog = true)
    @RequestMapping("/likeSearchSysFileInfoByPage")
    public ResultData<Page<SysFileInfo>> likeSearchSysFileInfoByPage(@RequestBody SysFileInfo request) {
        return sysFileInfoService.likeSearchSysFileInfoByPage(request);
    }

    /**
     * 添加附件信息
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "添加附件信息", syslog = true)
    @RequestMapping("/addSysFileInfo")
    public ResultData addSysFileInfo(@RequestBody SysFileInfo request) {
        return sysFileInfoService.addSysFileInfo(request);
    }

    /**
     * 根据id修改附件信息
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id修改附件信息", syslog = true)
    @RequestMapping("/updateSysFileInfoById")
    public ResultData updateSysFileInfoById(@RequestBody SysFileInfo request) {
        return sysFileInfoService.updateSysFileInfoById(request);
    }

    /**
     * 根据ids批量删除附件信息
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据ids批量删除附件信息", syslog = true)
    @RequestMapping("/deleteBatchSysFileInfoByIds")
    public ResultData deleteBatchSysFileInfoByIds(@RequestBody SysFileInfo request) {
        return sysFileInfoService.deleteBatchSysFileInfoByIds(request);
    }

}