package com.deloitte.system.model.sys;

import com.deloitte.common.model.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色菜单表 sys_role_menu_rel
 * 
 * @author ps-auto
 * @date 2019-06-21
 */
public class RoleMenuRel extends BaseEntity{
	private static final long serialVersionUID = 1L;

	/** 角色id */
	private Long roleId;
	/** 菜单id */
	private Long menuId;

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getRoleId()
	{
		return roleId;
	}
	public void setMenuId(Long menuId) 
	{
		this.menuId = menuId;
	}

	public Long getMenuId() 
	{
		return menuId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roleId", getRoleId())
            .append("menuId", getMenuId())
            .toString();
    }
}
