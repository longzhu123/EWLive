package org.ewlive.mapper.system;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;
import org.ewlive.entity.system.SysFileInfo;

import java.util.List;


/**
 * 附件信息Mapper
 * Create by yangjie on 2019/03/14
 */
public interface SysFileInfoMapper extends BaseMapper<SysFileInfo> {

    /**
     * 模糊查询附件信息(分页)
     *
     * @param pagination
     * @param sysFileInfo
     * @return
     */
    List<SysFileInfo> likeSearchSysFileInfoByPage(Pagination pagination, SysFileInfo sysFileInfo);

    /**
     * 添加附件信息
     *
     * @param sysFileInfo
     * @return
     */
    int addSysFileInfo(SysFileInfo sysFileInfo);


    /**
     * 批量添加附件信息
     * @param sysFileInfos
     * @return
     */
    int batchAddSysFileInfo(@Param("list") List<SysFileInfo> sysFileInfos);

    /**
     * 根据Id修改附件信息
     *
     * @param sysFileInfo
     * @return
     */
    int updateSysFileInfoById(SysFileInfo sysFileInfo);


    /**
     * 根据ids编号集合,修改fkId
     * @param ids
     * @param fkId
     * @return
     */
    int updateFkIdByIds(@Param("ids")List<String> ids, @Param("fkId")String fkId);
}