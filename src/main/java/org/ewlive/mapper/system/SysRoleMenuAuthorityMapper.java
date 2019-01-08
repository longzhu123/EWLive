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
     * @param sysRoleMenuAuthorityMapper
     * @return
     */
    List<SysRoleMenuAuthority> likeSearchSysRoleMenuAuthorityByPage(Pagination pagination, SysRoleMenuAuthority sysRoleMenuAuthorityMapper);

    /**
     * 添加角色菜单权限
     *
     * @param sysRoleMenuAuthorityMapper
     * @return
     */
    int addSysRoleMenuAuthority(SysRoleMenuAuthority sysRoleMenuAuthorityMapper);

    /**
     * 根据Id修改角色菜单权限
     *
     * @param sysRoleMenuAuthorityMapper
     * @return
     */
    int updateSysRoleMenuAuthorityById(SysRoleMenuAuthority sysRoleMenuAuthorityMapper);

    /**
     * 批量插入角色菜单关系
     * @param sysRoleMenuAuthorities
     * @return
     */
    int insertBatchSysRoleMenuAuthority(@Param("list") List<SysRoleMenuAuthority> sysRoleMenuAuthorities);


}