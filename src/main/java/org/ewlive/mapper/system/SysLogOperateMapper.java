package org.ewlive.mapper.system;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.ewlive.entity.system.SysLogOperate;

import java.util.List;


/**
 * 操作日志Mapper
 * Create by yangjie on 2018/12/26
 */
public interface SysLogOperateMapper extends BaseMapper<SysLogOperate> {

    /**
     * 模糊查询操作日志(分页)
     *
     * @param pagination
     * @param sysLogOperate
     * @return
     */
    List<SysLogOperate> likeSearchSysLogOperateByPage(Pagination pagination, SysLogOperate sysLogOperate);

    /**
     * 添加操作日志
     *
     * @param sysLogOperate
     * @return
     */
    int addSysLogOperate(SysLogOperate sysLogOperate);

    /**
     * 根据Id修改操作日志
     *
     * @param sysLogOperate
     * @return
     */
    int updateSysLogOperateById(SysLogOperate sysLogOperate);


}