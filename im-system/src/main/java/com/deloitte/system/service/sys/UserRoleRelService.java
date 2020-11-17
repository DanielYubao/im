package com.deloitte.system.service;

import com.deloitte.system.model.sys.UserRoleRel;

import java.util.List;

/**
 * 用户角色 服务层
 * 
 * @author ps-auto
 * @date 2019-06-21
 */
public interface UserRoleRelService 
{
	/**
     * 查询用户角色信息
     * 
     * @param id 用户角色ID
     * @return 用户角色信息
     */
	public UserRoleRel selectUserRoleRelById(Long id);
	
	/**
     * 查询用户角色列表
     * 
     * @param userRoleRel 用户角色信息
     * @return 用户角色集合
     */
	public List<UserRoleRel> selectUserRoleRelList(UserRoleRel userRoleRel);
	
	/**
     * 新增用户角色
     * 
     * @param userRoleRel 用户角色信息
     * @return 结果
     */
	public int insertUserRoleRel(UserRoleRel userRoleRel);
	
	/**
     * 修改用户角色
     * 
     * @param userRoleRel 用户角色信息
     * @return 结果
     */
	public int updateUserRoleRel(UserRoleRel userRoleRel);
		
	/**
     * 删除用户角色信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserRoleRelByIds(String ids);

	public int deleteUserRoleRelByUid(Long id);
}
