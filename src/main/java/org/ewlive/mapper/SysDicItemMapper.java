package org.ewlive.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.ewlive.entity.SysDicItem;


/**
 * 字典项Mapper
 * Create by yangjie on 2018/11/16
 */
public interface SysDicItemMapper extends BaseMapper<SysDicItem> {

    /**
     * 添加字典项
     * @param sysdicitem
     * @return
     */
    int addSysDicItem(SysDicItem sysdicitem);

    /**
     * 根据Id修改字典项
     * @param sysdicitem
     * @return
     */
    int updateSysDicItemById(SysDicItem sysdicitem);
}