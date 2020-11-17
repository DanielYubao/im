package com.deloitte.system.model.sys;

import com.deloitte.common.model.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 字典数据表 sys_dict_data
 * 
 * @author ps-auto
 * @date 2019-06-25
 */
public class DictData extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/** 字典标签 */
	private String dictLabel;
	/** 字典键值 */
	private String dictValue;
	/** 字典类型 */
	private String dictType;
	/** 字典排序 */
	private Integer dictSort;
	/** 状态（0正常 1停用） */
	private String status;
	/** 备注 */
	private String remark;

	public void setDictLabel(String dictLabel)
	{
		this.dictLabel = dictLabel;
	}

	public String getDictLabel() 
	{
		return dictLabel;
	}
	public void setDictValue(String dictValue) 
	{
		this.dictValue = dictValue;
	}

	public String getDictValue() 
	{
		return dictValue;
	}
	public void setDictType(String dictType) 
	{
		this.dictType = dictType;
	}

	public String getDictType() 
	{
		return dictType;
	}
	public void setDictSort(Integer dictSort) 
	{
		this.dictSort = dictSort;
	}

	public Integer getDictSort() 
	{
		return dictSort;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dictLabel", getDictLabel())
            .append("dictValue", getDictValue())
            .append("dict", getDictType())
            .append("dictSort", getDictSort())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
