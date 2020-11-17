package com.deloitte.system.mapper.customer;


import com.deloitte.system.model.customer.ChannelInfoExcel;

import java.util.List;

public interface ChannelInfoExcelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ChannelInfoExcel record);

    int insertSelective(ChannelInfoExcel record);

    ChannelInfoExcel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelInfoExcel record);

    int updateByPrimaryKey(ChannelInfoExcel record);

    /**
     * 删除指定人员导入的数据
     * @param createBy
     * @return
     */
    int deleteByCreateBy(Long createBy);

    /**
     * 查询列表
     * @param channelInfoExcel
     * @return
     */
    List<ChannelInfoExcel> selectList(ChannelInfoExcel channelInfoExcel);

}