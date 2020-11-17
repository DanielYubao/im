package com.deloitte.system.model.sys;

import com.deloitte.common.model.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 角色表 sys_roles
 * 
 * @author ps-auto
 * @date 2019-06-21
 */
public class Role extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String code;
	/**  */
	private String name;
	/**  */
	private Integer disableFlag;
	/**  */
	private Integer deleteFlag;

	private List<Long> menuIds;

	public List<Long> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<Long> menuIds) {
		this.menuIds = menuIds;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getCode() 
	{
		return code;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("disableFlag", getDisableFlag())
            .append("deleteFlag", getDeleteFlag())
            .toString();
    }
}
