package com.deloitte.system.mapper.customer;


import com.deloitte.system.model.customer.CustomerInfoExcel;

import java.util.List;

public interface CustomerInfoExcelMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CustomerInfoExcel record);

    int insertSelective(CustomerInfoExcel record);

    CustomerInfoExcel selectByPrimaryKey(Long id);


    int updateByPrimaryKey(CustomerInfoExcel record);

    /**
     * 删除指定人员导入的数据
     * @param createBy
     * @return
     */
    int deleteByCreateBy(Long createBy);

    /**
     * 查询列表
     * @param customerInfoExcel
     * @return
     */
    List<CustomerInfoExcel> selectList(CustomerInfoExcel customerInfoExcel);
}