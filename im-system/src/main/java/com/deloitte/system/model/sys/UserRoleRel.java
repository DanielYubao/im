package com.deloitte.system.model.sys;

import com.deloitte.common.model.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户角色表 sys_user_role_rel
 *
 * @author ps-auto
 * @date 2019-06-21
 */
public class UserRoleRel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**  */
    private Long userId;
    /**  */
    private Long roleId;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("roleId", getRoleId())
                .toString();
    }
}
