package org.ewlive.service.system;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.ewlive.constants.CommonConstants;
import org.ewlive.constants.ExceptionConstants;
import org.ewlive.entity.system.SysMenu;
import org.ewlive.entity.system.SysUser;
import org.ewlive.exception.ServiceException;
import org.ewlive.mapper.system.SysMenuMapper;
import org.ewlive.result.ResultData;
import org.ewlive.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单Service
 * Create by yangjie on 2018/12/18
 */
@Slf4j
@Service
public class SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    /**
     * 根据id查询菜单
     *
     * @param request
     * @return
     */
    public ResultData<SysMenu> getSysMenuById(SysMenu request) {
        //检查参数Id是否为空
        checkParamsId(request);
        log.info("根据id查询菜单:请求参数=====>" + JSON.toJSONString(request));
        ResultData<SysMenu> data = new ResultData<>();
        //根据id查询菜单
        SysMenu sysMenu = sysMenuMapper.selectById(request.getId());
        data.setData(sysMenu);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysMenu));
        return data;
    }


    /**
     * 多条件查询菜单
     *
     * @param request
     * @return
     */
    public ResultData<List<SysMenu>> getSysMenuByParams(SysMenu request) {
        log.info("多条件查询菜单信息:请求参数=====>" + JSON.toJSONString(request));
        ResultData<List<SysMenu>> data = new ResultData<>();
        //多条件查询菜单信息
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(new EntityWrapper<>(request));
        data.setData(sysMenuList);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysMenuList));
        return data;
    }


    /**
     * 模糊查询菜单(分页)
     *
     * @param request
     * @return
     */
    public ResultData<Page<SysMenu>> likeSearchSysMenuByPage(SysMenu request) {
        log.info("模糊查询菜单(分页):请求参数=====>" + JSON.toJSONString(request));
        ResultData<Page<SysMenu>> data = new ResultData<>();
        Page<SysMenu> page = new Page<>(request.getCurrent(), request.getSize());
        //模糊查询菜单(分页)
        List<SysMenu> sysMenuList = sysMenuMapper.likeSearchSysMenuByPage(page, request);
        page.setRecords(sysMenuList);
        data.setData(page);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysMenuList));
        return data;
    }

    /**
     * 查询菜单(层级展示,分页)
     *
     * @param request
     * @return
     */
    public ResultData<Page<SysMenu>> likeSearchSysMenuTreeByPage(SysMenu request) {
        log.info("查询菜单(层级展示,分页):请求参数=====>" + JSON.toJSONString(request));
        ResultData<Page<SysMenu>> data = new ResultData<>();
        Page<SysMenu> page = new Page<>(request.getCurrent(), request.getSize());

        //查询所有的菜单
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.orderBy("menu_sort", true);
        List<SysMenu> allSysMenu = sysMenuMapper.selectList(entityWrapper);

        //查询最顶层的菜单列表
        SysMenu topParentReq = new SysMenu();
        topParentReq.setId(CommonConstants.TOP_MENU_ID);
        List<SysMenu> sysMenuList = sysMenuMapper.likeSearchSysMenuByPage(page, topParentReq);

        //将sysMenuList下面所有的子菜单进行拼接
        for (SysMenu sysMenu : sysMenuList) {
            List<SysMenu> childList = sortTreeMenuList(sysMenu, allSysMenu);
            sysMenu.setChildren(childList);
        }
        page.setRecords(sysMenuList);
        data.setData(page);
        log.info("数据请求成功,=====>返回:" + JSON.toJSONString(sysMenuList));
        return data;
    }


    /**
     * 添加菜单
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData addSysMenu(SysMenu request) {
        log.info("添加菜单,请求参数====>" + JSON.toJSONString(request));
        //检查必填参数项是否空
        checkParamsForAdd(request);
        log.info("添加====>参数校验成功");
        ResultData data = new ResultData();
        //获取当前用户
        SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
        request.setId(CommonUtil.createUUID());
        request.setCreateTime(new Timestamp(System.currentTimeMillis()));
        request.setCreateUserId(currentUser.getId());
        //添加菜单
        int i = sysMenuMapper.addSysMenu(request);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.ADD_FAIL);
        }
        log.info("添加成功");
        return data;
    }


    /**
     * 根据id修改菜单
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData updateSysMenuById(SysMenu request) {
        log.info("修改菜单,请求参数====>" + JSON.toJSONString(request));
        //检查id是否为空
        checkParamsId(request);
        log.info("参数校验成功,id不为空");
        ResultData data = new ResultData();
        //获取当前用户
        SysUser currentUser = CommonUtil.getCurrentSysUserByToken(request.getToken());
        request.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        request.setUpdateUserId(currentUser.getId());
        //根据Id修改菜单
        int i = sysMenuMapper.updateSysMenuById(request);
        if (i == 0) {
            throw new ServiceException(ExceptionConstants.UPDATE_FAIL);
        }
        log.info("修改成功");
        return data;
    }


    /**
     * 根据ids批量删除菜单
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultData deleteBatchSysMenuByIds(SysMenu request) {
        log.info("根据ids批量删除菜单,请求参数====>" + JSON.toJSONString(request));
        //检查ids是否为空
        checkParamsIds(request);
        log.info("参数校验成功,ids不为空");
        if (request.getIds().contains(CommonConstants.TOP_MENU_ID)) {
            log.info("不能删除顶层的菜单节点");
            throw new ServiceException(ExceptionConstants.NOT_DEL_TOP_MENU);
        }
        ResultData data = new ResultData();
        //根据ids批量删除菜单
        int i = sysMenuMapper.deleteBatchIds(request.getIds());
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
    public void checkParamsId(SysMenu request) {
        if (CommonUtil.isStringEmpty(request.getId())) {
            throw new ServiceException(ExceptionConstants.ID_NOT_NULL);
        }
    }

    /**
     * 检查参数中的ids是否为空
     *
     * @param request
     */
    public void checkParamsIds(SysMenu request) {
        if (CommonUtil.isCollectionEmpty(request.getIds())) {
            throw new ServiceException(ExceptionConstants.IDS_NOT_NULL);
        }
    }

    /**
     * 检查添加参数是否齐全
     *
     * @param request
     */
    public void checkParamsForAdd(SysMenu request) {
        //判断菜单名称是否为空
        if (CommonUtil.isStringEmpty(request.getMenuName())) {
            throw new ServiceException(ExceptionConstants.MENUNAME_NOT_NULL);
        }
        //判断父菜单编号是否为空
        if (CommonUtil.isStringEmpty(request.getParentId())) {
            throw new ServiceException(ExceptionConstants.PARENTID_NOT_NULL);
        }
    }


    /**
     * 将当前菜单下面所有的子菜单递归拼接
     *
     * @param sysMenu    当前菜单对象
     * @param allSysMenu 所有的菜单List
     */
    public List<SysMenu> sortTreeMenuList(SysMenu sysMenu, List<SysMenu> allSysMenu) {
        List<SysMenu> childList = new ArrayList<>();
        for (SysMenu menu : allSysMenu) {
            if (sysMenu.getId().equals(menu.getParentId())) {
                List<SysMenu> sysMenus = sortTreeMenuList(menu, allSysMenu);
                menu.setChildren(sysMenus.size() == 0 ? null : sysMenus);
                childList.add(menu);
            }
        }
        return childList;
    }


}