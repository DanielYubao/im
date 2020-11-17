package com.deloitte.system.service;

import com.deloitte.common.model.AjaxResult;
import com.deloitte.system.model.sys.Role;

import java.util.List;

/**
 * 角色 服务层
 * 
 * @author ps-auto
 * @date 2019-06-21
 */
public interface RoleService
{
	/**
     * 查询角色信息
     * 
     * @param id 角色ID
     * @return 角色信息
     */
	public Role selectRoleById(Long id);
	
	/**
     * 查询角色列表
     * 
     * @param role 角色信息
     * @return 角色集合
     */
	public List<Role> selectRoleList(Role role);
	
	/**
     * 新增角色
     * 
     * @param role 角色信息
     * @return 结果
     */
	public int insertRole(Role role);
	
	/**
     * 修改角色
     * 
     * @param role 角色信息
     * @return 结果
     */
	public int updateRole(Role role);
		
	/**
     * 删除角色信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public AjaxResult deleteRoleByIds(Long[] ids);

    List<Role> selectRoleByUserId(Long id);

    List<Role> findByUserId(Long userId);
    List<Role> findRoleListByNameOrCode(Role role);
}
