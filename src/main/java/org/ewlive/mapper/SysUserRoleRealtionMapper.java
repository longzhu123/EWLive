package org.ewlive.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;
import org.ewlive.entity.SysUserRoleRealtion;

import java.util.List;


/**
 * 用户角色关系Mapper
 * Create by yangjie on 2018/12/12
 */
public interface SysUserRoleRealtionMapper extends BaseMapper<SysUserRoleRealtion> {

    /**
     * 模糊查询用户角色关系(分页)
     *
     * @param pagination
     * @param sysUserRoleRealtion
     * @return
     */
    List<SysUserRoleRealtion> likeSearchSysUserRoleRealtionByPage(Pagination pagination, SysUserRoleRealtion sysUserRoleRealtion);

    /**
     * 添加用户角色关系
     *
     * @param sysUserRoleRealtion
     * @return
     */
    int addSysUserRoleRealtion(SysUserRoleRealtion sysUserRoleRealtion);

    /**
     * 根据Id修改用户角色关系
     *
     * @param sysUserRoleRealtion
     * @return
     */
    int updateSysUserRoleRealtionById(SysUserRoleRealtion sysUserRoleRealtion);


    /**
     * 批量插入用户角色关系
     * @param sysUserRoleRealtions 数据集合
     * @return
     */
    int insertBatchSysUserRoleRealtion(@Param("list") List<SysUserRoleRealtion> sysUserRoleRealtions);
}