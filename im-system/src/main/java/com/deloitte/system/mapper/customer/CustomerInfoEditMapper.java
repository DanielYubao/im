package com.deloitte.system.mapper.customer;


import com.deloitte.system.model.customer.CustomerInfo;
import com.deloitte.system.model.customer.CustomerInfoEdit;

import java.util.List;

public interface CustomerInfoEditMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CustomerInfoEdit record);

    CustomerInfoEdit selectByPrimaryKey(Long id);

    int updateByPrimaryKey(CustomerInfoEdit record);

    /**
     * 查询列表
     * @param param
     * @return
     */
    List<CustomerInfoEdit > selectList(CustomerInfoEdit param);

    /**
     * 物理删除数据
     * @param ids
     * @return
     */
    int deleteCustomerInfoEdit(Long[] ids);

}