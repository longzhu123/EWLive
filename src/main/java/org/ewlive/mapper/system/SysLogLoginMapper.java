package org.ewlive.mapper.system;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.ewlive.entity.system.SysLogLogin;

import java.util.List;


/**
 * 登录日志Mapper
 * Create by yangjie on 2019/01/09
 */
public interface SysLogLoginMapper extends BaseMapper<SysLogLogin> {

    /**
     * 模糊查询登录日志(分页)
     *
     * @param pagination
     * @param sysLogLoginMapper
     * @return
     */
    List<SysLogLogin> likeSearchSysLogLoginByPage(Pagination pagination, SysLogLogin sysLogLoginMapper);

    /**
     * 添加登录日志
     *
     * @param sysLogLoginMapper
     * @return
     */
    int addSysLogLogin(SysLogLogin sysLogLoginMapper);

    /**
     * 根据Id修改登录日志
     *
     * @param sysLogLoginMapper
     * @return
     */
    int updateSysLogLoginById(SysLogLogin sysLogLoginMapper);


}