package org.ewlive.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.ewlive.entity.SysUser;

import java.util.List;


/**
 * 用户Mapper
 * Create by yangjie on 2018/06/07
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 用户登录
     * @param sysUser
     * @return
     */
    SysUser authLogin(SysUser sysUser);

    /**
     * 模糊查询用户(分页)
     * @param pagination
     * @param sysuser
     * @return
     */
    List<SysUser> likeSearchSysUserByPage(Pagination pagination, SysUser sysuser);

}