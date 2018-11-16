package org.ewlive.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.ewlive.entity.SysDic;


/**
 * 字典Mapper
 * Create by yangjie on 2018/11/16
 */
public interface SysDicMapper extends BaseMapper<SysDic> {

    /**
     * 添加字典
     * @param sysdic
     * @return
     */
    int addSysDic(SysDic sysdic);

    /**
     * 根据Id修改字典
     * @param sysdic
     * @return
     */
    int updateSysDicById(SysDic sysdic);

}