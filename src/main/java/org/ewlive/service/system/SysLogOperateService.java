package org.ewlive.service.system;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.ewlive.constants.ExceptionConstants;
import org.ewlive.entity.system.SysLogOperate;
import org.ewlive.entity.system.SysUser;
import org.ewlive.exception.ServiceException;
import org.ewlive.mapper.system.SysLogOperateMapper;
import org.ewlive.result.ResultData;
import org.ewlive.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * 操作日志Service
 * Create by yangjie on 2018/12/26
 */
@Slf4j
@Service
public class SysLogOperateService {

    @Resource
    private SysLogOperateMapper sysLogOperateMapper;

    /**
     * 根据id查询操作日志
     *
     * @param request
     * @return
     */
    public ResultData<SysLogOperate> getSysLogOperateById(SysLogOperate request) {
        //检查参数Id是否为空
        checkParamsId(request);
        log.info("根据id查询操作日志:请求参数=====>" + JSON.toJSONString(request));
        ResultData<SysLogOperate> data = new ResultData<>();
        //根据id查询操作日志
        SysLogOperate sysLogOperate = sysLogOperateMapper.selectById(request.getId());
        data.setData(sysLogOperate);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysLogOperate));
        return data;
    }


    /**
     * 多条件查询操作日志
     *
     * @param request
     * @return
     */
    public ResultData<List<SysLogOperate>> getSysLogOperateByParams(SysLogOperate request) {
        log.info("多条件查询操作日志信息:请求参数=====>" + JSON.toJSONString(request));
        ResultData<List<SysLogOperate>> data = new ResultData<>();
        //多条件查询操作日志信息
        List<SysLogOperate> sysLogOperateList = sysLogOperateMapper.selectList(new EntityWrapper<>(request));
        data.setData(sysLogOperateList);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysLogOperateList));
        return data;
    }


    /**
     * 模糊查询操作日志(分页)
     *
     * @param request
     * @return
     */
    public ResultData<Page<SysLogOperate>> likeSearchSysLogOperateByPage(SysLogOperate request) {
        log.info("模糊查询操作日志(分页):请求参数=====>" + JSON.toJSONString(request));
        ResultData<Page<SysLogOperate>> data = new ResultData<>();
        Page<SysLogOperate> page = new Page<>(request.getCurrent(), request.getSize());
        //模糊查询操作日志(分页)
        List<SysLogOperate> sysLogOperateList = sysLogOperateMapper.likeSearchSysLogOperateByPage(page, request);
        page.setRecords(sysLogOperateList);
        data.setData(page);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysLogOperateList));
        return data;
    }


    /**
     * 添加操作日志
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData addSysLogOperate(SysLogOperate request) {
        log.info("添加操作日志,请求参数====>" + JSON.toJSONString(request));
        //检查必填参数项是否空
        checkParamsForAdd(request);
        log.info("添加====>参数校验成功");
        ResultData data = new ResultData();
        //获取当前用户
        SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
        request.setId(CommonUtil.createUUID());
        request.setCreateTime(new Timestamp(System.currentTimeMillis()));
        request.setCreateUserId(currentUser.getId());
        //添加操作日志
        int i = sysLogOperateMapper.addSysLogOperate(request);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.ADD_FAIL);
        }
        log.info("添加成功");
        return data;
    }


    /**
     * 根据id修改操作日志
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData updateSysLogOperateById(SysLogOperate request) {
        log.info("修改操作日志,请求参数====>" + JSON.toJSONString(request));
        //检查id是否为空
        checkParamsId(request);
        log.info("参数校验成功,id不为空");
        ResultData data = new ResultData();
        //获取当前用户
        SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
        request.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        request.setUpdateUserId(currentUser.getId());
        //根据Id修改操作日志
        int i = sysLogOperateMapper.updateSysLogOperateById(request);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.UPDATE_FAIL);
        }
        log.info("修改成功");
        return data;
    }


    /**
     * 根据ids批量删除操作日志
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData deleteBatchSysLogOperateByIds(SysLogOperate request) {
        log.info("根据ids批量删除操作日志,请求参数====>" + JSON.toJSONString(request));
        //检查ids是否为空
        checkParamsIds(request);
        log.info("参数校验成功,ids不为空");
        ResultData data = new ResultData();
        //根据ids批量删除操作日志
        int i = sysLogOperateMapper.deleteBatchIds(request.getIds());
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
    public void checkParamsId(SysLogOperate request) {
        if (CommonUtil.isStringEmpty(request.getId())) {
            throw new ServiceException(ExceptionConstants.ID_NOT_NULL);
        }
    }

    /**
     * 检查参数中的ids是否为空
     *
     * @param request
     */
    public void checkParamsIds(SysLogOperate request) {
        if (CommonUtil.isCollectionEmpty(request.getIds())) {
            throw new ServiceException(ExceptionConstants.IDS_NOT_NULL);
        }
    }

    /**
     * 检查添加参数是否齐全
     *
     * @param request
     */
    public void checkParamsForAdd(SysLogOperate request) {
        //判断操作IP是否为空
        if (CommonUtil.isStringEmpty(request.getIp())) {
            throw new ServiceException(ExceptionConstants.IP_NOT_NULL);
        }
        //判断操作内容是否为空
        if (CommonUtil.isStringEmpty(request.getOperContent())) {
            throw new ServiceException(ExceptionConstants.OPERCONTENT_NOT_NULL);
        }
        //判断操作耗时是否为空
        if (Objects.isNull(request.getTaskTimeSpan())) {
            throw new ServiceException(ExceptionConstants.TASKTIMESPAN_NOT_NULL);
        }
    }

}