package org.ewlive.mapper.system;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.ewlive.entity.system.SysLogError;

import java.util.List;


/**
 * 异常日志Mapper
 * Create by yangjie on 2019/01/09
 */
public interface SysLogErrorMapper extends BaseMapper<SysLogError> {

    /**
     * 模糊查询异常日志(分页)
     *
     * @param pagination
     * @param sysLogError
     * @return
     */
    List<SysLogError> likeSearchSysLogErrorByPage(Pagination pagination, SysLogError sysLogError);

    /**
     * 添加异常日志
     *
     * @param sysLogError
     * @return
     */
    int addSysLogError(SysLogError sysLogError);

    /**
     * 根据Id修改异常日志
     *
     * @param sysLogError
     * @return
     */
    int updateSysLogErrorById(SysLogError sysLogError);


}