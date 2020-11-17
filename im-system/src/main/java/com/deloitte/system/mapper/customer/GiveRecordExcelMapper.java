package com.deloitte.system.mapper.customer;


import com.deloitte.system.model.customer.GiveRecordExcel;

import java.util.List;

public interface GiveRecordExcelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GiveRecordExcel record);

    int insertSelective(GiveRecordExcel record);

    GiveRecordExcel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GiveRecordExcel record);

    int updateByPrimaryKey(GiveRecordExcel record);

    /**
     * 删除指定人员导入的数据
     * @param createBy
     * @return
     */
    int deleteByCreateBy(Long createBy);

    /**
     * 查询列表
     * @param giveRecordExcel
     * @return
     */
    List<GiveRecordExcel> selectList(GiveRecordExcel giveRecordExcel);
}