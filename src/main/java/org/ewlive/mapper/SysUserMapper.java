package org.ewlive.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.ewlive.entity.SysUser;


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

}