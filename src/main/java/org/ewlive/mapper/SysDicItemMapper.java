package org.ewlive.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.ewlive.entity.SysDicItem;

import java.util.List;


/**
 * 字典项Mapper
 * Create by yangjie on 2018/12/04
 */
public interface SysDicItemMapper extends BaseMapper<SysDicItem> {

    /**
     * 模糊查询字典项(分页)
     *
     * @param pagination
     * @param sysDicItem
     * @return
     */
    List<SysDicItem> likeSearchSysDicItemByPage(Pagination pagination, SysDicItem sysDicItem);

    /**
     * 添加字典项
     *
     * @param sysDicItem
     * @return
     */
    int addSysDicItem(SysDicItem sysDicItem);

    /**
     * 根据Id修改字典项
     *
     * @param sysDicItem
     * @return
     */
    int updateSysDicItemById(SysDicItem sysDicItem);


}