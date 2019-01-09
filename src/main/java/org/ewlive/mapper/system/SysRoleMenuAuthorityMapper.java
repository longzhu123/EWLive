package org.ewlive.mapper.system;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;
import org.ewlive.entity.system.SysRoleMenuAuthority;

import java.util.List;


/**
 * 角色菜单权限Mapper
 * Create by yangjie on 2019/01/08
 */
public interface SysRoleMenuAuthorityMapper extends BaseMapper<SysRoleMenuAuthority> {

    /**
     * 模糊查询角色菜单权限(分页)
     *
     * @param pagination
     * @param sysRoleMenuAuthority
     * @return
     */
    List<SysRoleMenuAuthority> likeSearchSysRoleMenuAuthorityByPage(Pagination pagination, SysRoleMenuAuthority sysRoleMenuAuthority);

    /**
     * 添加角色菜单权限
     *
     * @param sysRoleMenuAuthority
     * @return
     */
    int addSysRoleMenuAuthority(SysRoleMenuAuthority sysRoleMenuAuthority);

    /**
     * 根据Id修改角色菜单权限
     *
     * @param sysRoleMenuAuthority
     * @return
     */
    int updateSysRoleMenuAuthorityById(SysRoleMenuAuthority sysRoleMenuAuthority);

    /**
     * 批量插入角色菜单关系
     * @param sysRoleMenuAuthorities
     * @return
     */
    int insertBatchSysRoleMenuAuthority(@Param("list") List<SysRoleMenuAuthority> sysRoleMenuAuthorities);


}