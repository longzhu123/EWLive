package org.ewlive.mapper;



import org.ewlive.entity.SysTest;



 * 系统测试Mapper 
 * Create by yangjie on 2019/05/18 
 */
public interface SysTestMapper  extends BaseMapper<SysTest> {

    /**
     * 模糊查询系统测试(分页)
     * @param pagination
     * @param sysTest
     * @return
     */
	List<SysTest> likeSearchSysTestByPage(Pagination pagination,SysTest sysTest);

     * 添加系统测试
     * @param sysTest
     * @return
     */

     * 根据Id修改系统测试
     * @param sysTest
     * @return
     */


}