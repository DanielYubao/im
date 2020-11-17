package com.deloitte.system.mapper.customer;


import com.deloitte.system.model.customer.ChannelInfo;
import com.deloitte.system.model.customer.CustomerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChannelInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ChannelInfo record);

    int insertSelective(ChannelInfo record);

    ChannelInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelInfo record);

    int updateByPrimaryKey(ChannelInfo record);

    /**
     * 保存上传的渠道信息
     * @param userId
     */
    void saveChannelExcel(Long userId);

    /**
     * 查询列表
     * @param param
     * @return
     */
    List<ChannelInfo> selectList(ChannelInfo param);

    /**
     * 删除客户信息
     * @param ids
     * @return
     */
    int deleteChannelInfo(@Param("array")Long[] ids, @Param("userId")Long userId);

    /**
     * 批量更新数据状态
     * @param status
     * @param ids
     * @return
     */
    int updateStatus(@Param("status")String status, @Param("array")Long[] ids);

}