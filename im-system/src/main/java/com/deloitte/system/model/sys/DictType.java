package com.deloitte.system.model.sys;

import com.deloitte.common.model.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 字典类型表 sys_dict_type
 *
 * @author ps-auto
 * @date 2019-06-21
 */
public class DictType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 分类编码
     */
    private String classifyCode;
    /**
     * 字典名称
     */
    private String dictName;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
     /**
      * 备注
     */
    private String remark;


    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictName() {
        return dictName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("classifyCode", getClassifyCode())
                .append("dictName", getDictName())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
