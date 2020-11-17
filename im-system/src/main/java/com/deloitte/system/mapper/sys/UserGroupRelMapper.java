package com.deloitte.system.mapper.sys;

import com.deloitte.system.model.sys.UserGroupRel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户和组的关系 数据层
 * 
 * @author ps-auto
 * @date 2019-06-21
 */
public interface UserGroupRelMapper {
	/**
     * 查询用户和组的关系信息
     * 
     * @param id 用户和组的关系ID
     * @return 用户和组的关系信息
     */
	public UserGroupRel selectUserGroupRelById(Long id);
	
	/**
     * 查询用户和组的关系列表
     * 
     * @param userGroupRel 用户和组的关系信息
     * @return 用户和组的关系集合
     */
	public List<UserGroupRel> selectUserGroupRelList(UserGroupRel userGroupRel);
	
	/**
     * 新增用户和组的关系
     * 
     * @param userGroupRel 用户和组的关系信息
     * @return 结果
     */
	public int insertUserGroupRel(UserGroupRel userGroupRel);
	
	/**
     * 修改用户和组的关系
     * 
     * @param userGroupRel 用户和组的关系信息
     * @return 结果
     */
	public int updateUserGroupRel(UserGroupRel userGroupRel);
	
	/**
     * 删除用户和组的关系
     * 
     * @param id 用户和组的关系ID
     * @return 结果
     */
	public int deleteUserGroupRelById(Long id);
	
	/**
     * 批量删除用户和组的关系
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserGroupRelByIds(Long[] ids);

    void updateUserGroupRelByUserId(@RequestParam("userGroupRel") UserGroupRel userGroupRel);
}