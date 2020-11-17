package com.deloitte.system.service;

import com.deloitte.system.model.sys.Group;

import java.util.List;

/**
 * 组 服务层
 * 
 * @author ps-auto
 * @date 2019-06-21
 */
public interface GroupService
{
	/**
     * 查询组信息
     * 
     * @param id 组ID
     * @return 组信息
     */
	public Group selectGroupById(Long id);
	
	/**
     * 查询组列表
     * 
     * @param Group 组信息
     * @return 组集合
     */
	public List<Group> selectGroupList(Group Group);
	
	/**
     * 新增组
     * 
     * @param Group 组信息
     * @return 结果
     */
	public int insertGroup(Group Group);
	
	/**
     * 修改组
     * 
     * @param Group 组信息
     * @return 结果
     */
	public int updateGroup(Group Group);
		
	/**
     * 删除组信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGroupByIds(Long[] ids);

	/**
	 * 检验组名是否唯一
	 * @param groupName
	 * @return
	 */
	public int checkGroupNameUnique(String groupName);

	/**
	 * 根据用户id查询组
	 * @param id
	 * @return
	 */
	Group selectGroupByUserId(Long id);
}
