package org.ewlive.controller;

import org.ewlive.entity.SysDic;
import org.ewlive.result.ResultData;
import org.ewlive.service.SysDicService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典Controller
 * Create by yangjie on 2018/11/16
 */
@RestController
@RequestMapping("/sysDic")
public class SysDicController {

    @Resource
    private SysDicService sysDicService;

    /**
     * 根据id查询字典
     *
     * @param request
     * @return
     */
    @RequestMapping("/getSysDicById")
    public ResultData<SysDic> getSysDicById(@RequestBody SysDic request) {
        return sysDicService.getSysDicById(request);
    }

    /**
     * 多条件查询字典
     *
     * @param request
     * @return
     */
    @RequestMapping("/getSysDicByParams")
    public ResultData<List<SysDic>> getSysDicByParams(@RequestBody SysDic request) {
        return sysDicService.getSysDicByParams(request);
    }

    /**
     * 添加字典
     *
     * @param request
     * @return
     */
    @RequestMapping("/addSysDic")
    public ResultData addSysDic(@RequestBody SysDic request) {
        return sysDicService.addSysDic(request);
    }

    /**
     * 根据id修改字典
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateSysDicById")
    public ResultData updateSysDicById(@RequestBody SysDic request) {
        return sysDicService.updateSysDicById(request);
    }

    /**
     * 根据ids批量删除字典
     *
     * @param request
     * @return
     */
    @RequestMapping("/deleteBatchSysDicByIds")
    public ResultData deleteBatchSysDicByIds(@RequestBody SysDic request) {
        return sysDicService.deleteBatchSysDicByIds(request);
    }

}