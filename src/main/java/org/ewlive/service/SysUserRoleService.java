package org.ewlive.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.ewlive.constants.ExceptionConstants;
import org.ewlive.entity.SysUserRole;
import org.ewlive.exception.ServiceException;
import org.ewlive.mapper.SysUserRoleMapper;
import org.ewlive.result.ResultData;
import org.ewlive.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * 用户角色Service
 * Create by yangjie on 2018/12/11
 */
@Slf4j
@Service
public class SysUserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 根据id查询用户角色
     *
     * @param request
     * @return
     */
    public ResultData<SysUserRole> getSysUserRoleById(SysUserRole request) {
        //检查参数Id是否为空
        checkParamsId(request);
        log.info("根据id查询用户角色:请求参数=====>" + JSON.toJSONString(request));
        ResultData<SysUserRole> data = new ResultData<>();
        //根据id查询用户角色
        SysUserRole sysUserRole = sysUserRoleMapper.selectById(request.getId());
        data.setData(sysUserRole);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysUserRole));
        return data;
    }


    /**
     * 多条件查询用户角色
     * @param request
     * @return
     */
    public ResultData<List<SysUserRole>> getSysUserRoleByParams(SysUserRole request){
        log.info("多条件查询用户角色信息:请求参数=====>"+JSON.toJSONString(request));
        ResultData<List<SysUserRole>> data= new ResultData<>();
        //多条件查询用户角色信息
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.selectList(new EntityWrapper<>(request));
        data.setData(sysUserRoleList);
        log.info("数据请求成功,=====>返回:"+JSON.toJSONString(sysUserRoleList));
        return data;
    }


    /**
     * 模糊查询用户角色(分页)
     *
     * @param request
     * @return
     */
    public ResultData<Page<SysUserRole>> likeSearchSysUserRoleByPage(SysUserRole request) {
        log.info("模糊查询用户角色(分页):请求参数=====>" + JSON.toJSONString(request));
        ResultData<Page<SysUserRole>> data = new ResultData<>();
        Page<SysUserRole> page = new Page<>(request.getCurrent(), request.getSize());
        //模糊查询用户角色(分页)
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.likeSearchSysUserRoleByPage(page, request);
        page.setRecords(sysUserRoleList);
        data.setData(page);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysUserRoleList));
        return data;
    }


    /**
     * 添加用户角色
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData addSysUserRole(SysUserRole request) {
        log.info("添加用户角色,请求参数====>" + JSON.toJSONString(request));
        //检查必填参数项是否空
        checkParamsForAdd(request);
        log.info("添加====>参数校验成功");
        ResultData data = new ResultData();
        request.setId(CommonUtil.createUUID());
        request.setCreateTime(new Timestamp(System.currentTimeMillis()));
        request.setCreateUserId(request.getId());
        //添加用户角色
        int i = sysUserRoleMapper.addSysUserRole(request);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.ADD_FAIL);
        }
        log.info("添加成功");
        return data;
    }


    /**
     * 根据id修改用户角色
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData updateSysUserRoleById(SysUserRole request) {
        log.info("修改用户角色,请求参数====>" + JSON.toJSONString(request));
        //检查id是否为空
        checkParamsId(request);
        log.info("参数校验成功,id不为空");
        ResultData data = new ResultData();
        request.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        request.setUpdateUserId(request.getId());
        //根据Id修改用户角色
        int i = sysUserRoleMapper.updateSysUserRoleById(request);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.UPDATE_FAIL);
        }
        log.info("修改成功");
        return data;
    }


    /**
     * 根据ids批量删除用户角色
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData deleteBatchSysUserRoleByIds(SysUserRole request) {
        log.info("根据ids批量删除用户角色,请求参数====>" + JSON.toJSONString(request));
        //检查ids是否为空
        checkParamsIds(request);
        log.info("参数校验成功,ids不为空");
        ResultData data = new ResultData();
        //根据ids批量删除用户角色
        int i = sysUserRoleMapper.deleteBatchIds(request.getIds());
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
    public void checkParamsId(SysUserRole request) {
        if (CommonUtil.isStringEmpty(request.getId())) {
            throw new ServiceException(ExceptionConstants.ID_NOT_NULL);
        }
    }

    /**
     * 检查参数中的ids是否为空
     *
     * @param request
     */
    public void checkParamsIds(SysUserRole request) {
        if (CommonUtil.isCollectionEmpty(request.getIds())) {
            throw new ServiceException(ExceptionConstants.IDS_NOT_NULL);
        }
    }

    /**
     * 检查添加参数是否齐全
     *
     * @param request
     */
    public void checkParamsForAdd(SysUserRole request) {
        //判断角色名称是否为空
        if (CommonUtil.isStringEmpty(request.getRoleName())) {
            throw new ServiceException(ExceptionConstants.ROLENAME_NOT_NULL);
        }
    }

}