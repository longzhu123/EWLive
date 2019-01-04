package org.ewlive.mapper.system;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.ewlive.entity.system.SysMenu;

import java.util.List;


/**
 * 菜单Mapper
 * Create by yangjie on 2018/12/18
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {


    /**
     * 模糊查询菜单(分页)
     *
     * @param pagination
     * @param sysMenuMapper
     * @return
     */
    List<SysMenu> likeSearchSysMenuByPage(Pagination pagination, SysMenu sysMenuMapper);


    /**
     * 添加菜单
     *
     * @param sysMenuMapper
     * @return
     */
    int addSysMenu(SysMenu sysMenuMapper);

    /**
     * 根据Id修改菜单
     *
     * @param sysMenuMapper
     * @return
     */
    int updateSysMenuById(SysMenu sysMenuMapper);


}