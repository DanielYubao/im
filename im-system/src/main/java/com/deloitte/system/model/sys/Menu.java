package com.deloitte.system.model.sys;

import com.deloitte.common.model.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 菜单表 sys_menus
 *
 * @author ps-auto
 * @date 2019-06-21
 */
public class Menu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**  */
    private String name;
    /**  */
    private String type;
    /**  */
    private String permission;
    /**  */
    private String url;
    /**  */
    private Integer orderNum;
    /**  */
    private Long parentId;
    /**  */
    private Integer visible;
    /**  */
    private String icon;

    /**  */
    private Integer disableFlag;
    /**  */
    private Integer deleteFlag;

    /**
     * 孩子节点
     */
    private List<Menu> children;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setDisableFlag(Integer disableFlag) {
        this.disableFlag = disableFlag;
    }

    public Integer getDisableFlag() {
        return disableFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("type", getType())
                .append("permission", getPermission())
                .append("url", getUrl())
                .append("orderNum", getOrderNum())
                .append("parentId", getParentId())
                .append("visible", getVisible())
                .append("icon", getIcon())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("disableFlag", getDisableFlag())
                .append("deleteFlag", getDeleteFlag())
                .toString();
    }
}
