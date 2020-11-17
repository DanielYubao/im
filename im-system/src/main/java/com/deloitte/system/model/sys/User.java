package com.deloitte.system.model.sys;

import com.deloitte.common.model.BaseEntity;

import java.util.List;

/**
 * 用户表 sys_user
 * 
 * @author ps-auto
 * @date 2019-06-21
 */
public class User extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**  */
	private String name;
	/**  */
	private String loginName;
	/**  */
	private String password;
	/**  */
	private Integer disableFlag;
	/**  */
	private Integer deleteFlag;
	private Long  groupId;
	private String  groupName;
	private Long roleId;
	private List<Long> roleIds;
	private List<Role> roleList;
	private String  roleName;

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setLoginName(String loginName) 
	{
		this.loginName = loginName;
	}

	public String getLoginName() 
	{
		return loginName;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassword() 
	{
		return password;
	}
	public void setDisableFlag(Integer disableFlag)
	{
		this.disableFlag = disableFlag;
	}

	public Integer getDisableFlag() 
	{
		return disableFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) 
	{
		this.deleteFlag = deleteFlag;
	}

	public Integer getDeleteFlag() 
	{
		return deleteFlag;
	}


}
