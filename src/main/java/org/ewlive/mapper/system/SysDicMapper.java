package org.ewlive.mapper.system;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.ewlive.entity.system.SysDic;

import java.util.List;


/**
 * 字典Mapper
 * Create by yangjie on 2018/12/04
 */
public interface SysDicMapper extends BaseMapper<SysDic> {

    /**
     * 模糊查询字典(分页)
     *
     * @param pagination
     * @param sysDic
     * @return
     */
    List<SysDic> likeSearchSysDicByPage(Pagination pagination, SysDic sysDic);

    /**
     * 添加字典
     *
     * @param sysDic
     * @return
     */
    int addSysDic(SysDic sysDic);

    /**
     * 根据Id修改字典
     *
     * @param sysDic
     * @return
     */
    int updateSysDicById(SysDic sysDic);


}