package org.ewlive.service.system;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.ewlive.constants.ExceptionConstants;
import org.ewlive.entity.system.SysFileInfo;
import org.ewlive.entity.system.SysUser;
import org.ewlive.exception.ServiceException;
import org.ewlive.mapper.system.SysFileInfoMapper;
import org.ewlive.result.ResultData;
import org.ewlive.util.CommonUtil;
import org.ewlive.util.DicConvertUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * 附件信息Service
 * Create by yangjie on 2019/03/14
 */
@Slf4j
@Service
public class SysFileInfoService {

    @Resource
    private SysFileInfoMapper sysFileInfoMapper;

    /**
     * 根据id查询附件信息
     *
     * @param request
     * @return
     */
    public ResultData<SysFileInfo> getSysFileInfoById(SysFileInfo request) {
        //检查参数Id是否为空
        checkParamsId(request);
        log.info("根据id查询附件信息:请求参数=====>" + JSON.toJSONString(request));
        ResultData<SysFileInfo> data = new ResultData<>();
        //根据id查询附件信息
        SysFileInfo sysFileInfo = sysFileInfoMapper.selectById(request.getId());
        SysFileInfo dataObj = DicConvertUtil.convertObjDicDesc(sysFileInfo, SysFileInfo.class);
        data.setData(dataObj);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(dataObj));
        return data;
    }


    /**
     * 多条件查询附件信息
     *
     * @param request
     * @return
     */
    public ResultData<List<SysFileInfo>> getSysFileInfoByParams(SysFileInfo request) {
        log.info("多条件查询附件信息信息:请求参数=====>" + JSON.toJSONString(request));
        ResultData<List<SysFileInfo>> data = new ResultData<>();
        //多条件查询附件信息信息
        List<SysFileInfo> sysFileInfoList = sysFileInfoMapper.selectList(new EntityWrapper<>(request));
        List<SysFileInfo> dataObj = DicConvertUtil.convertArrayDicDesc(sysFileInfoList, SysFileInfo.class);
        data.setData(dataObj);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(dataObj));
        return data;
    }


    /**
     * 模糊查询附件信息(分页)
     *
     * @param request
     * @return
     */
    public ResultData<Page<SysFileInfo>> likeSearchSysFileInfoByPage(SysFileInfo request) {
        log.info("模糊查询附件信息(分页):请求参数=====>" + JSON.toJSONString(request));
        ResultData<Page<SysFileInfo>> data = new ResultData<>();
        Page<SysFileInfo> page = new Page<>(request.getCurrent(), request.getSize());
        //模糊查询附件信息(分页)
        List<SysFileInfo> sysFileInfoList = sysFileInfoMapper.likeSearchSysFileInfoByPage(page, request);
        List<SysFileInfo> dataObj = DicConvertUtil.convertArrayDicDesc(sysFileInfoList, SysFileInfo.class);
        page.setRecords(dataObj);
        data.setData(page);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysFileInfoList));
        return data;
    }


    /**
     * 添加附件信息
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData addSysFileInfo(SysFileInfo request) {
        log.info("添加附件信息,请求参数====>" + JSON.toJSONString(request));
        //检查必填参数项是否空
        checkParamsForAdd(request);
        log.info("添加====>参数校验成功");
        ResultData data = new ResultData();
        //获取当前用户
        SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
        request.setId(CommonUtil.createUUID());
        request.setCreateTime(new Timestamp(System.currentTimeMillis()));
        request.setCreateUserId(currentUser.getId());
        //添加附件信息
        int i = sysFileInfoMapper.addSysFileInfo(request);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.ADD_FAIL);
        }
        log.info("添加成功");
        return data;
    }


    /**
     * 根据id修改附件信息
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData updateSysFileInfoById(SysFileInfo request) {
        log.info("修改附件信息,请求参数====>" + JSON.toJSONString(request));
        //检查id是否为空
        checkParamsId(request);
        log.info("参数校验成功,id不为空");
        ResultData data = new ResultData();
        //获取当前用户
        SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
        request.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        request.setUpdateUserId(currentUser.getId());
        //根据Id修改附件信息
        int i = sysFileInfoMapper.updateSysFileInfoById(request);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.UPDATE_FAIL);
        }
        log.info("修改成功");
        return data;
    }


    /**
     * 根据ids批量删除附件信息
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData deleteBatchSysFileInfoByIds(SysFileInfo request) {
        log.info("根据ids批量删除附件信息,请求参数====>" + JSON.toJSONString(request));
        //检查ids是否为空
        checkParamsIds(request);
        log.info("参数校验成功,ids不为空");
        ResultData data = new ResultData();
        //根据ids批量删除附件信息
        int i = sysFileInfoMapper.deleteBatchIds(request.getIds());
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
    public void checkParamsId(SysFileInfo request) {
        if (CommonUtil.isStringEmpty(request.getId())) {
            throw new ServiceException(ExceptionConstants.ID_NOT_NULL);
        }
    }

    /**
     * 检查参数中的ids是否为空
     *
     * @param request
     */
    public void checkParamsIds(SysFileInfo request) {
        if (CommonUtil.isCollectionEmpty(request.getIds())) {
            throw new ServiceException(ExceptionConstants.IDS_NOT_NULL);
        }
    }

    /**
     * 检查添加参数是否齐全
     *
     * @param request
     */
    public void checkParamsForAdd(SysFileInfo request) {
        //判断文件名称是否为空
        if (CommonUtil.isStringEmpty(request.getName())) {
            throw new ServiceException(ExceptionConstants.FILE_NAME_NOT_NULL);
        }
        //判断文件在服务器的名称是否为空
        if (CommonUtil.isStringEmpty(request.getRealName())) {
            throw new ServiceException(ExceptionConstants.FILE_REALNAME_NOT_NULL);
        }
        //判断文件大小是否为空
        if (Objects.isNull(request.getFileSize())) {
            throw new ServiceException(ExceptionConstants.FILESIZE_NOT_NULL);
        }
        //判断文件类型是否为空
        if (CommonUtil.isStringEmpty(request.getExtension())) {
            throw new ServiceException(ExceptionConstants.EXTENSION_NOT_NULL);
        }
        //判断文件后缀类型是否为空
        if (CommonUtil.isStringEmpty(request.getContentType())) {
            throw new ServiceException(ExceptionConstants.CONTENTTYPE_NOT_NULL);
        }
    }

}