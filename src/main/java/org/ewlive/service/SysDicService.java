package org.ewlive.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.extern.slf4j.Slf4j;
import org.ewlive.constants.ExceptionConstants;
import org.ewlive.entity.SysDic;
import org.ewlive.exception.ServiceException;
import org.ewlive.mapper.SysDicMapper;
import org.ewlive.result.ResultData;
import org.ewlive.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典Service
 * Create by yangjie on 2018/11/16
 */
@Slf4j
@Service
public class SysDicService {

    @Resource
    private SysDicMapper sysDicMapper;

    /**
     * 根据id查询字典
     *
     * @param request
     * @return
     */
    public ResultData<SysDic> getSysDicById(SysDic request) {
        //检查参数Id是否为空
        checkParamsId(request);
        log.info("根据id查询字典:请求参数=====>" + JSON.toJSONString(request));
        ResultData<SysDic> data = new ResultData<>();
        //根据id查询字典
        SysDic sysDic = sysDicMapper.selectById(request.getId());
        data.setData(sysDic);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysDic));
        return data;
    }


    /**
     * 多条件查询字典
     *
     * @param request
     * @return
     */
    public ResultData<List<SysDic>> getSysDicByParams(SysDic request) {
        log.info("多条件查询字典信息:请求参数=====>" + JSON.toJSONString(request));
        ResultData<List<SysDic>> data = new ResultData<>();
        //多条件查询字典信息
        List<SysDic> sysDicList = sysDicMapper.selectList(new EntityWrapper<>(request));
        data.setData(sysDicList);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysDicList));
        return data;
    }


    /**
     * 添加字典
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData addSysDic(SysDic request) {
        log.info("添加字典,请求参数====>" + JSON.toJSONString(request));
        //检查必填参数项是否空
        checkParamsForAdd(request);
        log.info("添加====>参数校验成功");
        ResultData data = new ResultData();
        //添加字典
        int i = sysDicMapper.addSysDic(request);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.ADD_FAIL);
        }
        log.info("添加成功");
        return data;
    }


    /**
     * 根据id修改字典
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData updateSysDicById(SysDic request) {
        log.info("修改字典,请求参数====>" + JSON.toJSONString(request));
        //检查id是否为空
        checkParamsId(request);
        log.info("参数校验成功,id不为空");
        ResultData data = new ResultData();
        //根据Id修改字典
        int i = sysDicMapper.updateSysDicById(request);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.UPDATE_FAIL);
        }
        log.info("修改成功");
        return data;
    }


    /**
     * 根据ids批量删除字典
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData deleteBatchSysDicByIds(SysDic request) {
        log.info("根据ids批量删除字典,请求参数====>" + JSON.toJSONString(request));
        //检查ids是否为空
        checkParamsIds(request);
        log.info("参数校验成功,ids不为空");
        ResultData data = new ResultData();
        //根据ids批量删除字典
        int i = sysDicMapper.deleteBatchIds(request.getIds());
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.DELTE_FAIL);
        }
        log.info("删除成功");
        return data;
    }

    /**
     * 检查参数中的id是否为空
     *
     * @param request
     */
    public void checkParamsId(SysDic request) {
        if (CommonUtil.isStringEmpty(request.getId())) {
            throw new ServiceException(ExceptionConstants.ID_NOT_NULL);
        }
    }

    /**
     * 检查参数中的ids是否为空
     *
     * @param request
     */
    public void checkParamsIds(SysDic request) {
        if (CommonUtil.isCollectionEmpty(request.getIds())) {
            throw new ServiceException(ExceptionConstants.IDS_NOT_NULL);
        }
    }

    /**
     * 检查添加参数是否齐全
     *
     * @param request
     */
    public void checkParamsForAdd(SysDic request) {
        //判断字典名称是否为空
        if (CommonUtil.isStringEmpty(request.getDicName())) {
            throw new ServiceException(ExceptionConstants.DICNAME_NOT_NULL);
        }
    }

}