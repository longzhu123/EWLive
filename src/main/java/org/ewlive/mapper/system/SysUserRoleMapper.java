package org.ewlive.mapper.system;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.ewlive.entity.system.SysUserRole;

import java.util.List;


/**
 * 用户角色Mapper
 * Create by yangjie on 2018/12/11
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 模糊查询用户角色(分页)
     *
     * @param pagination
     * @param sysUserRole
     * @return
     */
    List<SysUserRole> likeSearchSysUserRoleByPage(Pagination pagination, SysUserRole sysUserRole);

    /**
     * 添加用户角色
     *
     * @param sysUserRole
     * @return
     */
    int addSysUserRole(SysUserRole sysUserRole);

    /**
     * 根据Id修改用户角色
     *
     * @param sysUserRole
     * @return
     */
    int updateSysUserRoleById(SysUserRole sysUserRole);


}