package com.deloitte.system.mapper.customer;


import com.deloitte.system.model.customer.ChannelCustomerInfoExcel;
import com.deloitte.system.model.customer.CustomerInfoExcel;

import java.util.List;

public interface ChannelCustomerInfoExcelMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ChannelCustomerInfoExcel record);

    int insertSelective(ChannelCustomerInfoExcel record);

    ChannelCustomerInfoExcel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelCustomerInfoExcel record);

    int updateByPrimaryKey(ChannelCustomerInfoExcel record);

    /**
     * 删除指定人员导入的数据
     * @param createBy
     * @return
     */
    int deleteByCreateBy(Long createBy);

    /**
     * 查询列表
     * @param channelCustomerInfoExcel
     * @return
     */
    List<ChannelCustomerInfoExcel> selectList(ChannelCustomerInfoExcel channelCustomerInfoExcel);
}