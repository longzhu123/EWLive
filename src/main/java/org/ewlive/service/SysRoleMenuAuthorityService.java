package org.ewlive.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.ewlive.constants.ExceptionConstants;
import org.ewlive.entity.system.SysRoleMenuAuthority;
import org.ewlive.entity.system.SysUser;
import org.ewlive.entity.system.SysUserRoleRealtion;
import org.ewlive.exception.ServiceException;
import org.ewlive.mapper.system.SysRoleMenuAuthorityMapper;
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
 * 角色菜单权限Service
 * Create by yangjie on 2019/01/08
 */
@Slf4j
@Service
public class SysRoleMenuAuthorityService {

    @Resource
    private SysRoleMenuAuthorityMapper sysRoleMenuAuthorityMapper;

    /**
     * 根据id查询角色菜单权限
     *
     * @param request
     * @return
     */
    public ResultData<SysRoleMenuAuthority> getSysRoleMenuAuthorityById(SysRoleMenuAuthority request) {
        //检查参数Id是否为空
        checkParamsId(request);
        log.info("根据id查询角色菜单权限:请求参数=====>" + JSON.toJSONString(request));
        ResultData<SysRoleMenuAuthority> data = new ResultData<>();
        //根据id查询角色菜单权限
        SysRoleMenuAuthority sysRoleMenuAuthority = sysRoleMenuAuthorityMapper.selectById(request.getId());
        data.setData(sysRoleMenuAuthority);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysRoleMenuAuthority));
        return data;
    }


    /**
     * 多条件查询角色菜单权限
     *
     * @param request
     * @return
     */
    public ResultData<List<SysRoleMenuAuthority>> getSysRoleMenuAuthorityByParams(SysRoleMenuAuthority request) {
        log.info("多条件查询角色菜单权限信息:请求参数=====>" + JSON.toJSONString(request));
        ResultData<List<SysRoleMenuAuthority>> data = new ResultData<>();
        //多条件查询角色菜单权限信息
        List<SysRoleMenuAuthority> sysRoleMenuAuthorityList = sysRoleMenuAuthorityMapper.selectList(new EntityWrapper<>(request));
        data.setData(sysRoleMenuAuthorityList);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysRoleMenuAuthorityList));
        return data;
    }


    /**
     * 模糊查询角色菜单权限(分页)
     *
     * @param request
     * @return
     */
    public ResultData<Page<SysRoleMenuAuthority>> likeSearchSysRoleMenuAuthorityByPage(SysRoleMenuAuthority request) {
        log.info("模糊查询角色菜单权限(分页):请求参数=====>" + JSON.toJSONString(request));
        ResultData<Page<SysRoleMenuAuthority>> data = new ResultData<>();
        Page<SysRoleMenuAuthority> page = new Page<>(request.getCurrent(), request.getSize());
        //模糊查询角色菜单权限(分页)
        List<SysRoleMenuAuthority> sysRoleMenuAuthorityList = sysRoleMenuAuthorityMapper.likeSearchSysRoleMenuAuthorityByPage(page, request);
        page.setRecords(sysRoleMenuAuthorityList);
        data.setData(page);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysRoleMenuAuthorityList));
        return data;
    }


    /**
     * 添加角色菜单权限
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData addSysRoleMenuAuthority(SysRoleMenuAuthority request) {
        log.info("添加角色菜单权限,请求参数====>" + JSON.toJSONString(request));
        //检查必填参数项是否空
        checkParamsForAdd(request);
        log.info("添加====>参数校验成功");

        //查询该角色有对应的菜单数据(有:删除在添加,没有就添加)
        SysRoleMenuAuthority searchReq = new SysRoleMenuAuthority();
        searchReq.setUserRoleId(request.getUserRoleId());
        List<SysRoleMenuAuthority> searchList = sysRoleMenuAuthorityMapper.selectList(new EntityWrapper<>(searchReq));

        //查出list有数据,删除用户对应的角色数据
        if(CommonUtil.isCollectionNotEmpty(searchList)){
            List<String> delIds = searchList.parallelStream().map(SysRoleMenuAuthority::getId).collect(Collectors.toList());
            SysRoleMenuAuthority delIdsReq = new SysRoleMenuAuthority();
            delIdsReq.setIds(delIds);
            deleteBatchSysRoleMenuAuthorityByIds(delIdsReq);
        }

        List<SysRoleMenuAuthority> addList = new ArrayList<>();
        request.getUserRoleMenuIds().forEach(item->{
            SysRoleMenuAuthority sysRoleMenuAuthority = new SysRoleMenuAuthority();
            sysRoleMenuAuthority.setId(CommonUtil.createUUID());
            sysRoleMenuAuthority.setMenuId(item);
            sysRoleMenuAuthority.setUserRoleId(request.getUserRoleId());
            sysRoleMenuAuthority.setCreateTime(new Timestamp(System.currentTimeMillis()));
            //获取当前用户
            SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
            sysRoleMenuAuthority.setCreateUserId(currentUser.getId());
            addList.add(sysRoleMenuAuthority);
        });


        ResultData data = new ResultData();

        //添加角色菜单权限
        int i = sysRoleMenuAuthorityMapper.insertBatchSysRoleMenuAuthority(addList);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.ADD_FAIL);
        }
        log.info("添加成功");
        return data;
    }


    /**
     * 根据id修改角色菜单权限
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData updateSysRoleMenuAuthorityById(SysRoleMenuAuthority request) {
        log.info("修改角色菜单权限,请求参数====>" + JSON.toJSONString(request));
        //检查id是否为空
        checkParamsId(request);
        log.info("参数校验成功,id不为空");
        ResultData data = new ResultData();
        //获取当前用户
        SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
        request.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        request.setUpdateUserId(currentUser.getId());
        //根据Id修改角色菜单权限
        int i = sysRoleMenuAuthorityMapper.updateSysRoleMenuAuthorityById(request);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.UPDATE_FAIL);
        }
        log.info("修改成功");
        return data;
    }


    /**
     * 根据ids批量删除角色菜单权限
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData deleteBatchSysRoleMenuAuthorityByIds(SysRoleMenuAuthority request) {
        log.info("根据ids批量删除角色菜单权限,请求参数====>" + JSON.toJSONString(request));
        //检查ids是否为空
        checkParamsIds(request);
        log.info("参数校验成功,ids不为空");
        ResultData data = new ResultData();
        //根据ids批量删除角色菜单权限
        int i = sysRoleMenuAuthorityMapper.deleteBatchIds(request.getIds());
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
    public void checkParamsId(SysRoleMenuAuthority request) {
        if (CommonUtil.isStringEmpty(request.getId())) {
            throw new ServiceException(ExceptionConstants.ID_NOT_NULL);
        }
    }

    /**
     * 检查参数中的ids是否为空
     *
     * @param request
     */
    public void checkParamsIds(SysRoleMenuAuthority request) {
        if (CommonUtil.isCollectionEmpty(request.getIds())) {
            throw new ServiceException(ExceptionConstants.IDS_NOT_NULL);
        }
    }

    /**
     * 检查添加参数是否齐全
     *
     * @param request
     */
    public void checkParamsForAdd(SysRoleMenuAuthority request) {
        //判断橘色编号是否为空
        if (CommonUtil.isStringEmpty(request.getUserRoleId())) {
            throw new ServiceException(ExceptionConstants.ROLEID_NOT_NULL);
        }
        //判断菜单编号集合是否为空
        if (CommonUtil.isCollectionEmpty(request.getUserRoleMenuIds())) {
            throw new ServiceException(ExceptionConstants.MENUIDS_NOT_NULL);
        }
    }

}