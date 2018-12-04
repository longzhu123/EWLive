package org.ewlive.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.ewlive.entity.SysDic;

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
     * @param SysDic
     * @return
     */
    List<SysDic> likeSearchSysDicByPage(Pagination pagination, SysDic SysDic);

    /**
     * 添加字典
     *
     * @param SysDic
     * @return
     */
    int addSysDic(SysDic SysDic);

    /**
     * 根据Id修改字典
     *
     * @param SysDic
     * @return
     */
    int updateSysDicById(SysDic SysDic);


}