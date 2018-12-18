package org.ewlive.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.ewlive.entity.SysMenu;


/**
 * 菜单Mapper
 * Create by yangjie on 2018/12/18
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {


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