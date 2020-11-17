package com.deloitte.system.service.sys.impl;

import com.deloitte.common.utils.Convert;
import com.deloitte.system.mapper.sys.UserRoleRelMapper;
import com.deloitte.system.model.sys.UserRoleRel;
import com.deloitte.system.service.UserRoleRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * daily work用户角色 服务层实现
 * 
 * @author ps-auto
 * @date 2019-06-21
 */
@Service
public class UserRoleRelServiceImpl implements UserRoleRelService
{
	@Autowired
	private UserRoleRelMapper userRoleRelMapper;

	/**
     * 查询daily work用户角色信息
     * 
     * @param id daily work用户角色ID
     * @return daily work用户角色信息
     */
    @Override
	public UserRoleRel selectUserRoleRelById(Long id)
	{
	    return userRoleRelMapper.selectUserRoleRelById(id);
	}
	
	/**
     * 查询daily work用户角色列表
     * 
     * @param userRoleRel daily work用户角色信息
     * @return daily work用户角色集合
     */
	@Override
	public List<UserRoleRel> selectUserRoleRelList(UserRoleRel userRoleRel)
	{
	    return userRoleRelMapper.selectUserRoleRelList(userRoleRel);
	}
	
    /**
     * 新增daily work用户角色
     * 
     * @param userRoleRel daily work用户角色信息
     * @return 结果
     */
	@Override
	public int insertUserRoleRel(UserRoleRel userRoleRel)
	{
	    return userRoleRelMapper.insertUserRoleRel(userRoleRel);
	}
	
	/**
     * 修改daily work用户角色
     * 
     * @param userRoleRel daily work用户角色信息
     * @return 结果
     */
	@Override
	public int updateUserRoleRel(UserRoleRel userRoleRel)
	{
	    return userRoleRelMapper.updateUserRoleRel(userRoleRel);
	}

	/**
     * 删除daily work用户角色对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUserRoleRelByIds(String ids)
	{
		return userRoleRelMapper.deleteUserRoleRelByIds(Convert.toLongArray(ids));
	}

	@Override
	public int deleteUserRoleRelByUid(Long id) {
		return userRoleRelMapper.deleteUserRoleRelByUid(id);
	}

}
