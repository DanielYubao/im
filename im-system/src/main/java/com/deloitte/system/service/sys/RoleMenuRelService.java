package com.deloitte.system.service;

import com.deloitte.system.model.sys.RoleMenuRel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * daily work角色菜单 服务层
 * 
 * @author ps-auto
 * @date 2019-06-21
 */
public interface RoleMenuRelService 
{
	/**
     * 查询daily work角色菜单信息
     * 
     * @param id daily work角色菜单ID
     * @return daily work角色菜单信息
     */
	public RoleMenuRel selectRoleMenuRelById(Long id);
	
	/**
     * 查询daily work角色菜单列表
     * 
     * @param roleMenuRel daily work角色菜单信息
     * @return daily work角色菜单集合
     */
	public List<RoleMenuRel> selectRoleMenuRelList(RoleMenuRel roleMenuRel);
	
	/**
     * 新增daily work角色菜单
     * 
     * @param roleMenuRel daily work角色菜单信息
     * @return 结果
     */
	public int insertRoleMenuRel(RoleMenuRel roleMenuRel);
	
	/**
     * 修改daily work角色菜单
     * 
     * @param roleMenuRel daily work角色菜单信息
     * @return 结果
     */
	public int updateRoleMenuRel(RoleMenuRel roleMenuRel);
		
	/**
     * 删除daily work角色菜单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteRoleMenuRelByIds(String ids);

    public void insertRoleMenuRelList(@RequestParam("roleMenuRels") List<RoleMenuRel> roleMenuRels);

	public void deleteRoleMenuRelByRoleId(Long id);
}
