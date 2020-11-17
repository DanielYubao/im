package com.deloitte.system.mapper.customer;


import com.deloitte.system.model.customer.ChannelCustomerInfoEdit;
import com.deloitte.system.model.customer.ChannelInfoEdit;

import java.util.List;

public interface ChannelCustomerInfoEditMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ChannelCustomerInfoEdit record);

    int insertSelective(ChannelCustomerInfoEdit record);

    ChannelCustomerInfoEdit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelCustomerInfoEdit record);

    int updateByPrimaryKey(ChannelCustomerInfoEdit record);

    /**
     * 查询列表
     * @param param
     * @return
     */
    List<ChannelCustomerInfoEdit> selectList(ChannelCustomerInfoEdit param);

    /**
     * 物理删除数据
     * @param ids
     * @return
     */
    int deleteCustomerInfoEdit(Long[] ids);
}