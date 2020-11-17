package com.deloitte.system.mapper.sys;


import com.deloitte.system.model.sys.Role;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 角色 数据层
 * 
 * @author ps-auto
 * @date 2019-06-21
 */
public interface RoleMapper {
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
     * 删除角色
     * 
     * @param id 角色ID
     * @return 结果
     */
	public int deleteRoleById(Long id);
	
	/**
     * 批量删除角色
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteRoleByIds(Long[] ids);

    List<Role> selectRoleByUserId(@RequestParam("userId") Long id);

	/**
	 * 根据编码和name精确查询
	 * @param role
	 * @return
	 */
	List<Role> findRoleListByNameOrCode(@RequestParam("role") Role role);


	Role selectRoleByIdForDelete(@RequestParam("id") Long id);
}