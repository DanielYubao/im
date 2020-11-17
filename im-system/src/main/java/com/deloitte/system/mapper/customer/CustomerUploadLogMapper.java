package com.deloitte.system.mapper.customer;


import com.deloitte.system.model.customer.CustomerUploadLog;

public interface CustomerUploadLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerUploadLog record);

    int insertSelective(CustomerUploadLog record);

    CustomerUploadLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerUploadLog record);

    int updateByPrimaryKey(CustomerUploadLog record);

    /**
     * 统计用户上传次数
     * @param userId
     * @return
     */
    int countByUserId(Long userId);
}