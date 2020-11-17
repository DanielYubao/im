package com.deloitte.system.mapper.customer;


import com.deloitte.system.model.customer.ChannelCustomerInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ChannelCustomerInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ChannelCustomerInfo record);

    int insertSelective(ChannelCustomerInfo record);

    ChannelCustomerInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelCustomerInfo record);

    int updateByPrimaryKey(ChannelCustomerInfo record);

    /**
     * 保存上传的客户信息
     * @param userId
     */
    void saveChannelCustomerExcel(Long userId);

    /**
     * 查询列表
     * @param param
     * @return
     */
    List<ChannelCustomerInfo> selectList(ChannelCustomerInfo param);


    /**
     * 根据customerCode和createBy查询
     * @param customerCode
     * @param createBy
     * @return
     */
    List<ChannelCustomerInfo>  selectByCustomerCodeAndCreateBy(@Param("customerCode")String customerCode, @Param("createBy")Long createBy);


    /**
     * 删除渠道客户信息
     * @param ids
     * @return
     */
    int deleteChannelCustomerInfo(@Param("array")Long[] ids, @Param("userId")Long userId);

    /**
     * 批量更新数据状态
     * @param status
     * @param ids
     * @return
     */
    int updateStatus(@Param("status")String status, @Param("array")Long[] ids);

    /**
     * 修改渠道客户信息归属人
     * @param customerIds
     * @param targetCreateBy
     * @return
     */
    int modifyCreateBy(@Param("array")Long[] customerIds, @Param("targetCreateBy")String targetCreateBy);
}