package com.deloitte.system.service.customer;

import com.deloitte.system.model.customer.CustomerInfoEdit;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 17/04/2020
 * @Description: 客户信息审核服务接口
 */
public interface CustomerInfoEditService extends BaseService {

    /**
     * 查询客户审核信息列表
     * @param param
     * @return
     */
    List<CustomerInfoEdit> customerAuditList(CustomerInfoEdit param);

    /**
     * 查询客户信息修改详情
     * @param id
     * @return
     */
    CustomerInfoEdit queryCustomerEdit(Long id);

    /**
     * 审核客户信息
     * @param auditType
     * @param ids
     */
    void auditCustomerEdit(String auditType, Long[] ids);

}
