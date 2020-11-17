package com.deloitte.system.service.sys.impl;

import com.deloitte.framework.exception.BusinessException;
import com.deloitte.system.common.SystemModualConstants;
import com.deloitte.system.mapper.sys.UserMapper;
import com.deloitte.system.model.sys.*;
import com.deloitte.system.service.*;
import com.deloitte.system.service.GroupService;
import com.deloitte.system.service.RoleService;
import com.deloitte.system.service.UserGroupRelService;
import com.deloitte.system.service.UserRoleRelService;
import com.deloitte.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户 服务层实现
 * 
 * @author ps-auto
 * @date 2019-06-21
 */
@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserGroupRelService userGroupRelService;

	@Autowired
	private UserRoleRelService userRoleRelService;

	@Autowired
	private GroupService groupService;
	@Autowired
	private RoleService roleService;

	/**
     * 查询用户信息
     * 
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
	public User selectUserById(Long id)
	{

		List<Role> roleList =  roleService.selectRoleByUserId(id);
		User user = userMapper.selectUserById(id);
		user.setRoleList(roleList);
		Group group = groupService.selectGroupByUserId(id);
		user.setGroupId(group.getId());
		user.setGroupName(group.getGroupName());
		return user;
	}
	
	/**
     * 查询用户列表
     * 
     * @param user 用户信息
     * @return 用户集合
     */
	@Override
	public List<User> selectUserList(User user)
	{
		user.setDeleteFlag(SystemModualConstants.DELETE_FLAG_FALSE);
	    return userMapper.selectUserList(user);
	}
	
    /**
     * 新增用户
     * 
     * @param user 用户信息
     * @return 结果
     */
	@Override
	@Transactional
	public int insertUser(User user)
	{
		org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long userId = Long.parseLong(currentUser.getUsername());
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("MD5", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("MD5"));
		DelegatingPasswordEncoder en = new DelegatingPasswordEncoder("MD5", encoders);
		user.setPassword(en.encode(user.getPassword()));
		//保存user
		user.setCreateBy(userId);
		user.setUpdateBy(userId);
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setDeleteFlag(0);
		userMapper.insertUser(user);

		//保存组关系
		UserGroupRel userGroupRel = new UserGroupRel();
		userGroupRel.setCreateBy(userId);
		userGroupRel.setCreateTime(new Date());
		userGroupRel.setUpdateBy(userId);
		userGroupRel.setUpdateTime(new Date());
		userGroupRel.setGroupId(user.getGroupId());
		userGroupRel.setUserId(user.getId());
		userGroupRel.setStatus(0+"");
		userGroupRelService.insertUserGroupRel(userGroupRel);
		//保存角色关系
		List<Long> roleIds = user.getRoleIds();
		for (Long roleId : roleIds) {
			UserRoleRel userRoleRel = new UserRoleRel();
			userRoleRel.setRoleId(roleId);
			userRoleRel.setUserId(user.getId());
			userRoleRelService.insertUserRoleRel(userRoleRel);
		}
		return 1;
	}
	
	/**
     * 修改用户
     * 
     * @param user 用户信息
     * @return 结果
     */
	@Override
	@Transactional
	public int updateUser(User user)
	{
        org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = Long.parseLong(currentUser.getUsername());

        user.setUpdateBy(userId);
        user.setUpdateTime(new Date());
        userMapper.updateUser(user);

        //保存组关系
        UserGroupRel userGroupRel = new UserGroupRel();
        userGroupRel.setGroupId(user.getGroupId());
        userGroupRel.setUserId(user.getId());
        userGroupRel.setUpdateTime(new Date());
        userGroupRel.setUpdateBy(userId);
        userGroupRelService.updateUserGroupRelByUserId(userGroupRel);
		//保存角色关系
		List<Long> roleIds = user.getRoleIds();
		if(roleIds != null) {
			userRoleRelService.deleteUserRoleRelByUid(user.getId());
			for (Long roleId : roleIds) {
				UserRoleRel userRoleRel = new UserRoleRel();
				userRoleRel.setRoleId(roleId);
				userRoleRel.setUserId(user.getId());
				userRoleRelService.insertUserRoleRel(userRoleRel);
			}
		}
	    return userMapper.updateUser(user);
	}

	/**
     * 删除用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@Transactional
	public int deleteUserByIds(Long[] ids)
	{
		return userMapper.deleteUserByIds(ids);
	}

	@Override
	public User selectUserByUsername(String username) {
		return userMapper.selectUserByUsername(username);
	}

	@Override
	@Transactional
	public Integer updatePassword(User user) {
		return userMapper.updatePassword(user);
	}

	@Override
	public Integer resetFixedPassword(Long userId) {
		if (userId == null){
			throw new BusinessException("重置密码用户id为空！");
		}
		User user = userMapper.selectUserById(userId);
		if (user == null){
			throw new BusinessException("未查询到当前用户！");
		}
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("MD5", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("MD5"));
		DelegatingPasswordEncoder en = new DelegatingPasswordEncoder("MD5", encoders);
		user.setPassword(en.encode("Init1234"));
		return this.updatePassword(user);
	}
}
