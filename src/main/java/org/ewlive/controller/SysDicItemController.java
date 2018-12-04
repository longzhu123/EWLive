package org.ewlive.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.ewlive.aop.AuthReq;
import org.ewlive.entity.SysDicItem;
import org.ewlive.result.ResultData;
import org.ewlive.service.SysDicItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典项Controller
 * Create by yangjie on 2018/11/16
 */
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
    @RequestMapping("/getSysDicItemById")
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
    @RequestMapping("/getSysDicItemByParams")
    public ResultData<List<SysDicItem>> getSysDicItemByParams(@RequestBody SysDicItem request) {
        return sysDicItemService.getSysDicItemByParams(request);
    }

    /**
     * 模糊查询字典项(分页)
     *
     * @param request
     * @return
     */
    @RequestMapping("/likeSearchSysDicItemByPage")
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
    @RequestMapping("/addSysDicItem")
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
    @RequestMapping("/updateSysDicItemById")
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
    @RequestMapping("/deleteBatchSysDicItemByIds")
    public ResultData deleteBatchSysDicItemByIds(@RequestBody SysDicItem request) {
        return sysDicItemService.deleteBatchSysDicItemByIds(request);
    }

}