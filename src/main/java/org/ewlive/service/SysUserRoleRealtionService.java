package org.ewlive.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.ewlive.constants.CommonConstants;
import org.ewlive.constants.ExceptionConstants;
import org.ewlive.entity.SysUser;
import org.ewlive.entity.SysUserRoleRealtion;
import org.ewlive.exception.ServiceException;
import org.ewlive.mapper.SysUserRoleRealtionMapper;
import org.ewlive.result.ResultData;
import org.ewlive.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色关系Service
 * Create by yangjie on 2018/12/12
 */
@Slf4j
@Service
public class SysUserRoleRealtionService {

    @Resource
    private SysUserRoleRealtionMapper sysUserRoleRealtionMapper;

    /**
     * 根据id查询用户角色关系
     *
     * @param request
     * @return
     */
    public ResultData<SysUserRoleRealtion> getSysUserRoleRealtionById(SysUserRoleRealtion request) {
        //检查参数Id是否为空
        checkParamsId(request);
        log.info("根据id查询用户角色关系:请求参数=====>" + JSON.toJSONString(request));
        ResultData<SysUserRoleRealtion> data = new ResultData<>();
        //根据id查询用户角色关系
        SysUserRoleRealtion sysUserRoleRealtion = sysUserRoleRealtionMapper.selectById(request.getId());
        data.setData(sysUserRoleRealtion);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysUserRoleRealtion));
        return data;
    }

    /**
     * 多条件查询用户角色关系
     * @param request
     * @return
     */
    public ResultData<List<SysUserRoleRealtion>> getSysUserRoleRealtionByParams(SysUserRoleRealtion request){
        log.info("多条件查询用户角色关系信息:请求参数=====>"+JSON.toJSONString(request));
        ResultData<List<SysUserRoleRealtion>> data= new ResultData<>();
        //多条件查询用户角色关系信息
        List<SysUserRoleRealtion> sysUserRoleRealtionList = sysUserRoleRealtionMapper.selectList(new EntityWrapper<>(request));
        data.setData(sysUserRoleRealtionList);
        log.info("数据请求成功,=====>返回:"+JSON.toJSONString(sysUserRoleRealtionList));
        return data;
    }


    /**
     * 模糊查询用户角色关系(分页)
     *
     * @param request
     * @return
     */
    public ResultData<Page<SysUserRoleRealtion>> likeSearchSysUserRoleRealtionByPage(SysUserRoleRealtion request) {
        log.info("模糊查询用户角色关系(分页):请求参数=====>" + JSON.toJSONString(request));
        ResultData<Page<SysUserRoleRealtion>> data = new ResultData<>();
        Page<SysUserRoleRealtion> page = new Page<>(request.getCurrent(), request.getSize());
        //模糊查询用户角色关系(分页)
        List<SysUserRoleRealtion> sysUserRoleRealtionList = sysUserRoleRealtionMapper.likeSearchSysUserRoleRealtionByPage(page, request);
        page.setRecords(sysUserRoleRealtionList);
        data.setData(page);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysUserRoleRealtionList));
        return data;
    }


    /**
     * 添加用户角色关系
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData addSysUserRoleRealtion(SysUserRoleRealtion request) {
        log.info("添加用户角色关系,请求参数====>" + JSON.toJSONString(request));
        //检查必填参数项是否空
        checkParamsForAdd(request);
        log.info("添加====>参数校验成功");

        //查询该用户有对应的角色数据(有:删除在添加,没有就添加)
        SysUserRoleRealtion searchReq = new SysUserRoleRealtion();
        searchReq.setUserId(request.getUserId());
        List<SysUserRoleRealtion> searchList = sysUserRoleRealtionMapper.selectList(new EntityWrapper<>(searchReq));

        //查出list有数据,删除用户对应的角色数据
        if(CommonUtil.isCollectionNotEmpty(searchList)){
            List<String> delIds = searchList.parallelStream().map(SysUserRoleRealtion::getId).collect(Collectors.toList());
            SysUserRoleRealtion delIdsReq = new SysUserRoleRealtion();
            delIdsReq.setIds(delIds);
            deleteBatchSysUserRoleRealtionByIds(delIdsReq);
        }


        List<SysUserRoleRealtion> addList = new ArrayList<>();
        request.getUserRoleIds().forEach(item->{
            SysUserRoleRealtion sysUserRoleRealtion = new SysUserRoleRealtion();
            sysUserRoleRealtion.setId(CommonUtil.createUUID());
            sysUserRoleRealtion.setUserId(request.getUserId());
            sysUserRoleRealtion.setUserRoleId(item);
            sysUserRoleRealtion.setCreateTime(new Timestamp(System.currentTimeMillis()));
            //获取当前用户
            SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
            sysUserRoleRealtion.setCreateUserId(currentUser.getId());
            addList.add(sysUserRoleRealtion);
        });
        ResultData data = new ResultData();
        //添加用户角色关系
        int i = sysUserRoleRealtionMapper.insertBatchSysUserRoleRealtion(addList);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.ADD_FAIL);
        }
        log.info("添加成功");
        return data;
    }


    /**
     * 根据id修改用户角色关系
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData updateSysUserRoleRealtionById(SysUserRoleRealtion request) {
        log.info("修改用户角色关系,请求参数====>" + JSON.toJSONString(request));
        //检查id是否为空
        checkParamsId(request);
        log.info("参数校验成功,id不为空");
        ResultData data = new ResultData();
        request.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        //获取当前用户
        SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
        request.setUpdateUserId(currentUser.getId());
        //根据Id修改用户角色关系
        int i = sysUserRoleRealtionMapper.updateSysUserRoleRealtionById(request);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.UPDATE_FAIL);
        }
        log.info("修改成功");
        return data;
    }


    /**
     * 根据ids批量删除用户角色关系
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData deleteBatchSysUserRoleRealtionByIds(SysUserRoleRealtion request) {
        log.info("根据ids批量删除用户角色关系,请求参数====>" + JSON.toJSONString(request));
        //检查ids是否为空
        checkParamsIds(request);
        log.info("参数校验成功,ids不为空");
        ResultData data = new ResultData();
        //根据ids批量删除用户角色关系
        int i = sysUserRoleRealtionMapper.deleteBatchIds(request.getIds());
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
    public void checkParamsId(SysUserRoleRealtion request) {
        if (CommonUtil.isStringEmpty(request.getId())) {
            throw new ServiceException(ExceptionConstants.ID_NOT_NULL);
        }
    }

    /**
     * 检查参数中的ids是否为空
     *
     * @param request
     */
    public void checkParamsIds(SysUserRoleRealtion request) {
        if (CommonUtil.isCollectionEmpty(request.getIds())) {
            throw new ServiceException(ExceptionConstants.IDS_NOT_NULL);
        }
    }

    /**
     * 检查添加参数是否齐全
     *
     * @param request
     */
    public void checkParamsForAdd(SysUserRoleRealtion request) {
        //判断用户编号是否为空
        if (CommonUtil.isStringEmpty(request.getUserId())) {
            throw new ServiceException(ExceptionConstants.USERID_NOT_NULL);
        }
        //判断用户角色编号是否为空
        if (CommonUtil.isCollectionEmpty(request.getUserRoleIds())) {
            throw new ServiceException(ExceptionConstants.USERROLEID_NOT_NULL);
        }
    }

}