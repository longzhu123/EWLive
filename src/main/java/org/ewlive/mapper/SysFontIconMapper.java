package org.ewlive.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.ewlive.entity.SysFontIcon;



/**
 * 系统字体图标Mapper 
 * Create by yangjie on 2018/12/17 
 */
public interface SysFontIconMapper  extends BaseMapper<SysFontIcon> {

    /**
     * 模糊查询系统字体图标(分页)
     * @param pagination
     * @param sysFontIcon
     * @return
     */
	List<SysFontIcon> likeSearchSysFontIconByPage(Pagination pagination,SysFontIcon sysFontIcon);

    /**
     * 添加系统字体图标
     * @param sysFontIcon
     * @return
     */
	int addSysFontIcon(SysFontIcon sysFontIcon);

    /**
     * 根据Id修改系统字体图标
     * @param sysFontIcon
     * @return
     */
	int updateSysFontIconById(SysFontIcon sysFontIcon);



}