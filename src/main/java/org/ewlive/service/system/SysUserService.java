package org.ewlive.service.system;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.ewlive.constants.CommonConstants;
import org.ewlive.constants.ExceptionConstants;
import org.ewlive.constants.ResultConstants;
import org.ewlive.entity.system.SysLogLogin;
import org.ewlive.entity.system.SysUser;
import org.ewlive.exception.ServiceException;
import org.ewlive.mapper.system.SysLogLoginMapper;
import org.ewlive.mapper.system.SysUserMapper;
import org.ewlive.result.ResultData;
import org.ewlive.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * 用户Service
 * Create by yangjie on 2018/06/07
 */
@Slf4j
@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysLogLoginMapper sysLogLoginMapper;

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
        //获取当前用户
        SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
        request.setId(CommonUtil.createUUID());
        request.setCreateTime(new Timestamp(System.currentTimeMillis()));
        request.setCreateUserId(currentUser.getId());
        //添加用户,判断邮箱是否重复
        SysUser validateEmailUser = new SysUser();
        validateEmailUser.setEmail(request.getEmail());
        List<SysUser> emailUsers = sysUserMapper.selectList(new EntityWrapper<>(validateEmailUser));
        if (CommonUtil.isCollectionNotEmpty(emailUsers)) {
            //邮箱重复
            throw new ServiceException(ExceptionConstants.EAMIL_ALREADY_EXISTS);
        }

        //添加用户,判断昵称是否重复
        SysUser validateNickNameUser = new SysUser();
        validateNickNameUser.setNickName(request.getNickName());
        List<SysUser> nickNameUsers = sysUserMapper.selectList(new EntityWrapper<>(validateNickNameUser));
        if (CommonUtil.isCollectionNotEmpty(nickNameUsers)) {
            //昵称重复
            throw new ServiceException(ExceptionConstants.NICKNAME_ALREADY_EXISTS);
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
        //获取当前用户
        SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
        request.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        request.setUpdateUserId(currentUser.getId());
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
        checkEmailAndPwdIsNull(request);
        //判断昵称是否为空
        if (CommonUtil.isStringEmpty(request.getNickName())) {
            throw new ServiceException(ExceptionConstants.NICKNAME_NOT_NULL);
        }
    }

    /**
     * 检查邮箱和密码是否为空
     *
     * @param request
     */
    public void checkEmailAndPwdIsNull(SysUser request) {
        //判断邮箱是否为空
        if (CommonUtil.isStringEmpty(request.getEmail())) {
            throw new ServiceException(ExceptionConstants.EMAIL_NOT_NULL);
        }
        //判断密码是否为空
        if (CommonUtil.isStringEmpty(request.getPassword())) {
            throw new ServiceException(ExceptionConstants.PASSWORD_NOT_NULL);
        }
    }

    /**
     * 用户登录
     *
     * @param request
     * @return
     */
    public ResultData<SysUser> authLogin(SysUser request) {
        log.info("用户登录,请求参数====>" + JSON.toJSONString(request));
        //检查邮箱和密码是否为空
        checkEmailAndPwdIsNull(request);
        log.info("参数校验成功");
        ResultData<SysUser> data = new ResultData();
        SysUser sysUser = sysUserMapper.authLogin(request);

        //登录失败
        if (Objects.isNull(sysUser)) {
            throw new ServiceException(ExceptionConstants.SYS_USER_LOGIN_FAIL);
        }

        //登录成功,将用户信息写进缓存Map,并生成token
        String token = CommonUtil.createUUID();
        sysUser.setPassword(null);
        sysUser.setToken(token);

        //用户异地登录,踢下线的处理
        if (!Objects.isNull(CommonConstants.map.get(sysUser.getId()))) {
            String preToken = CommonConstants.map.get(sysUser.getId());
            CommonConstants.map.remove(preToken);
        }
        CommonConstants.map.put(sysUser.getToken(), JSON.toJSONString(sysUser));
        CommonConstants.map.put(sysUser.getId(), sysUser.getToken());
        data.setData(sysUser);
        //记录用户登录日志
        recordSysUserLogLogin(sysUser);
        return data;
    }

    /**
     * 用户登出
     *
     * @param sysUser
     * @return
     */
    public ResultData loginOut(SysUser sysUser) {
        log.info("用户登出,请求参数====>" + JSON.toJSONString(sysUser));
        ResultData resultData = new ResultData();
        SysUser delRedisUser = JSON.parseObject(CommonConstants.map.get(sysUser.getToken()), SysUser.class);
        CommonConstants.map.remove(sysUser.getToken());
        CommonConstants.map.remove(delRedisUser.getId());
        return resultData;
    }

    /**
     * 模糊查询用户(分页)
     *
     * @param request
     * @return
     */
    public ResultData<Page<SysUser>> likeSearchSysUserByPage(SysUser request) {
        log.info("模糊查询用户(分页):请求参数=====>" + JSON.toJSONString(request));
        if (Objects.isNull(request.getCurrent()) || Objects.isNull(request.getSize())) {
            throw new ServiceException(ExceptionConstants.PAGE_LESS_PARAM);
        }
        ResultData<Page<SysUser>> data = new ResultData<>();
        Page<SysUser> page = new Page<>(request.getCurrent(), request.getSize());
        //模糊查询用户(分页)
        List<SysUser> sysUserList = sysUserMapper.likeSearchSysUserByPage(page, request);
        page.setRecords(sysUserList);
        data.setData(page);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysUserList));
        return data;
    }

    /**
     * 验证token是否有效
     *
     * @param sysUser
     * @return
     */
    public ResultData validateToken(SysUser sysUser) {
        log.info("验证token是否有效,请求参数====>" + JSON.toJSONString(sysUser));
        ResultData resultData = new ResultData();
        String token = sysUser.getToken();
        if (CommonUtil.isStringEmpty(token)) {
            throw new ServiceException(ResultConstants.TOKEN_TIME_OUT_CODE, ExceptionConstants.TOKEN_NOT_NULL);
        }
        String user = CommonConstants.map.get(token);
        if (CommonUtil.isStringEmpty(user)) {
            throw new ServiceException(ResultConstants.TOKEN_TIME_OUT_CODE, ExceptionConstants.AUTH_TOKEN_FAIL);
        }
        return resultData;
    }


    /**
     * 记录用户的登录日志
     *
     * @param sysUser
     */
    public void recordSysUserLogLogin(SysUser sysUser) {
        Timestamp curTimeStamp = new Timestamp(System.currentTimeMillis());
        SysLogLogin sysLogLogin = new SysLogLogin();
        sysLogLogin.setId(CommonUtil.createUUID());
        sysLogLogin.setCreateTime(curTimeStamp);
        sysLogLogin.setCreateUserId(sysUser.getId());
        sysLogLogin.setLoginIp(CommonUtil.getIpAddr(CommonUtil.getHttpSerlvetRequest()));
        sysLogLogin.setLoginTime(curTimeStamp);
        sysLogLogin.setUserId(sysUser.getId());
        sysLogLogin.setNickName(sysUser.getNickName());
        int i = sysLogLoginMapper.addSysLogLogin(sysLogLogin);
        if (i == 0) {
            log.error("记录用户日志失败");
            throw new ServiceException(ExceptionConstants.ADD_FAIL);
        }
        log.error("记录用户日志成功");
    }
}
