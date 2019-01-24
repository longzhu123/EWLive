package org.ewlive.controller.system;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ewlive.aop.AuthReq;
import org.ewlive.aop.SysLog;
import org.ewlive.entity.system.SysDicItem;
import org.ewlive.result.ResultData;
import org.ewlive.service.system.SysDicItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典项Controller
 * Create by yangjie on 2018/11/16
 */
@Api(description = "系统字典项")
@RestController
@RequestMapping("/sysDicItem")
public class SysDicItemController {

    @Resource
    private SysDicItemService sysDicItemService;

    /**
     * 根据id查询字典项
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id查询字典项",syslog = true)
    @ApiOperation(value = "根据id查询字典项")
    @RequestMapping(value = "/getSysDicItemById", method = RequestMethod.POST)
    public ResultData<SysDicItem> getSysDicItemById(@RequestBody SysDicItem request) {
        return sysDicItemService.getSysDicItemById(request);
    }

    /**
     * 多条件查询字典项
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "多条件查询字典项",syslog = true)
    @ApiOperation(value = "多条件查询字典项")
    @RequestMapping(value = "/getSysDicItemByParams", method = RequestMethod.POST)
    public ResultData<List<SysDicItem>> getSysDicItemByParams(@RequestBody SysDicItem request) {
        return sysDicItemService.getSysDicItemByParams(request);
    }

    /**
     * 模糊查询字典项(分页)
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "模糊查询字典项(分页)",syslog = true)
    @ApiOperation(value = "模糊查询字典项(分页)")
    @RequestMapping(value = "/likeSearchSysDicItemByPage", method = RequestMethod.POST)
    public ResultData<Page<SysDicItem>> likeSearchSysDicItemByPage(@RequestBody SysDicItem request) {
        return sysDicItemService.likeSearchSysDicItemByPage(request);
    }

    /**
     * 添加字典项
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "添加字典项",syslog = true)
    @ApiOperation(value = "添加字典项")
    @RequestMapping(value = "/addSysDicItem", method = RequestMethod.POST)
    public ResultData addSysDicItem(@RequestBody SysDicItem request) {
        return sysDicItemService.addSysDicItem(request);
    }

    /**
     * 根据id修改字典项
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据id修改字典项",syslog = true)
    @ApiOperation(value = "根据id修改字典项")
    @RequestMapping(value = "/updateSysDicItemById", method = RequestMethod.POST)
    public ResultData updateSysDicItemById(@RequestBody SysDicItem request) {
        return sysDicItemService.updateSysDicItemById(request);
    }

    /**
     * 根据ids批量删除字典项
     *
     * @param request
     * @return
     */
    @AuthReq
    @SysLog(description = "根据ids批量删除字典项",syslog = true)
    @ApiOperation(value = "根据ids批量删除字典项")
    @RequestMapping(value = "/deleteBatchSysDicItemByIds", method = RequestMethod.POST)
    public ResultData deleteBatchSysDicItemByIds(@RequestBody SysDicItem request) {
        return sysDicItemService.deleteBatchSysDicItemByIds(request);
    }

}