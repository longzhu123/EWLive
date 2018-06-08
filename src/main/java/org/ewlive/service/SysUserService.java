package org.ewlive.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.extern.slf4j.Slf4j;
import org.ewlive.constants.ExceptionConstants;
import org.ewlive.entity.SysUser;
import org.ewlive.exception.ServiceException;
import org.ewlive.mapper.SysUserMapper;
import org.ewlive.result.ResultData;
import org.ewlive.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * 用户Service
 * Create by yangjie on 2018/06/07
 */
@Slf4j
@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 根据id查询用户
     *
     * @param request
     * @return
     */
    public ResultData<SysUser> getSysUserById(SysUser request) {
        //检查参数Id是否为空
        checkParamsId(request);
        log.info("根据id查询用户:请求参数=====>" + JSON.toJSONString(request));
        ResultData<SysUser> data = new ResultData<>();
        //根据id查询用户
        SysUser sysUser = sysUserMapper.selectById(request.getId());
        data.setData(sysUser);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysUser));
        return data;
    }


    /**
     * 多条件查询用户
     *
     * @param request
     * @return
     */
    public ResultData<List<SysUser>> getSysUserByParams(SysUser request) {
        log.info("多条件查询用户信息:请求参数=====>" + JSON.toJSONString(request));
        ResultData<List<SysUser>> data = new ResultData<>();
        //多条件查询用户信息
        List<SysUser> sysUserList = sysUserMapper.selectList(new EntityWrapper<>(request));
        data.setData(sysUserList);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysUserList));
        return data;
    }


    /**
     * 添加用户
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData addSysUser(SysUser request) {
        log.info("添加用户,请求参数====>" + JSON.toJSONString(request));
        //检查必填参数项是否空
        checkParamsForAdd(request);
        log.info("添加====>参数校验成功");
        ResultData data = new ResultData();
        request.setId(CommonUtil.createUUID());
        request.setCreateTime(new Timestamp(System.currentTimeMillis()));
        request.setCreateUserId(request.getId());


        //添加用户,判断邮箱是否重复
        SysUser validateEmailUser = new SysUser();
        validateEmailUser.setEmail(request.getEmail());
        List<SysUser> emailUsers = sysUserMapper.selectList(new EntityWrapper<>(validateEmailUser));
        if(CommonUtil.isCollectionNotEmpty(emailUsers)){
            //邮箱重复
            throw  new ServiceException(ExceptionConstants.EAMIL_ALREADY_EXISTS);
        }

        //添加用户,判断昵称是否重复
        SysUser validateNickNameUser = new SysUser();
        validateNickNameUser.setNickName(request.getNickName());
        List<SysUser> nickNameUsers = sysUserMapper.selectList(new EntityWrapper<>(validateNickNameUser));
        if(CommonUtil.isCollectionNotEmpty(nickNameUsers)){
            //昵称重复
            throw  new ServiceException(ExceptionConstants.NICKNAME_ALREADY_EXISTS);
        }

        //添加用户
        int i = sysUserMapper.insert(request);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.ADD_FAIL);
        }
        log.info("添加成功");
        return data;
    }


    /**
     * 根据id修改用户
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData updateSysUserById(SysUser request) {
        log.info("修改用户,请求参数====>" + JSON.toJSONString(request));
        //检查id是否为空
        checkParamsId(request);
        log.info("参数校验成功,id不为空");
        ResultData data = new ResultData();
        request.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        request.setUpdateUserId(request.getId());
        //根据Id修改用户
        int i = sysUserMapper.updateById(request);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.UPDATE_FAIL);
        }
        log.info("修改成功");
        return data;
    }


    /**
     * 根据ids批量删除用户
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData deleteBatchSysUserByIds(SysUser request) {
        log.info("根据ids批量删除用户,请求参数====>" + JSON.toJSONString(request));
        //检查ids是否为空
        checkParamsIds(request);
        log.info("参数校验成功,ids不为空");
        ResultData data = new ResultData();
        //根据ids批量删除用户
        int i = sysUserMapper.deleteBatchIds(request.getIds());
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
    public void checkParamsId(SysUser request) {
        if (CommonUtil.isStringEmpty(request.getId())) {
            throw new ServiceException(ExceptionConstants.ID_NOT_NULL);
        }
    }

    /**
     * 检查参数中的ids是否为空
     *
     * @param request
     */
    public void checkParamsIds(SysUser request) {
        if (CommonUtil.isCollectionEmpty(request.getIds())) {
            throw new ServiceException(ExceptionConstants.IDS_NOT_NULL);
        }
    }

    /**
     * 检查添加参数是否齐全
     *
     * @param request
     */
    public void checkParamsForAdd(SysUser request) {
        //判断邮箱是否为空
        if (CommonUtil.isStringEmpty(request.getEmail())) {
            throw new ServiceException(ExceptionConstants.EMAIL_NOT_NULL);
        }
        //判断密码是否为空
        if (CommonUtil.isStringEmpty(request.getPassword())) {
            throw new ServiceException(ExceptionConstants.PASSWORD_NOT_NULL);
        }
        //判断是否为空
        if (CommonUtil.isStringEmpty(request.getNickName())) {
            throw new ServiceException(ExceptionConstants.NICKNAME_NOT_NULL);
        }
    }

}