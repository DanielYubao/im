package com.deloitte.system.mapper.customer;

import com.deloitte.system.model.customer.CustomerInfo;
import com.deloitte.system.model.customer.CustomerInfoExcel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CustomerInfo record);

    int insertSelective(CustomerInfo record);

    CustomerInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerInfo record);

    int updateByPrimaryKey(CustomerInfo record);

    /**
     * 保存上传的客户信息
     * @param userId
     */
    void saveCustomerExcel(Long userId);

    /**
     * 查询列表
     * @param param
     * @return
     */
    List<CustomerInfo> selectList(CustomerInfo param);

    /**
     * 根据customerCode和createBy查询
     * @param customerCode
     * @param createBy
     * @return
     */
    List<CustomerInfo> selectByCustomerCodeAndCreateBy(@Param("customerCode")String customerCode, @Param("createBy")Long createBy);

    /**
     * 删除客户信息
     * @param ids
     * @return
     */
    int deleteCustomerInfo(@Param("array")Long[] ids, @Param("userId")Long userId);

    /**
     * 批量更新数据状态
     * @param status
     * @param ids
     * @return
     */
    int updateStatus(@Param("status")String status, @Param("array")Long[] ids);

    /**
     * 修改客户信息归属人
     * @param customerIds
     * @param targetCreateBy
     * @return
     */
    int modifyCreateBy(@Param("array")Long[] customerIds, @Param("targetCreateBy")String targetCreateBy);

    /**
     * 客户信息统计
     * @param param
     * @return
     */
    Map<String, Object> customerInfoData(CustomerInfo param);

}