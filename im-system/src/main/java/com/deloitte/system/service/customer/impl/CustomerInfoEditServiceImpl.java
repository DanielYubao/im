package com.deloitte.system.service.customer.impl;

import com.alibaba.fastjson.JSON;
import com.deloitte.common.utils.StringUtils;
import com.deloitte.framework.exception.BusinessException;
import com.deloitte.framework.utils.PageHelperUtils;
import com.deloitte.system.common.CustomerInfoConstants;
import com.deloitte.system.mapper.customer.CustomerInfoEditMapper;
import com.deloitte.system.mapper.customer.CustomerInfoMapper;
import com.deloitte.system.model.customer.CustomerInfo;
import com.deloitte.system.model.customer.CustomerInfoEdit;
import com.deloitte.system.service.customer.CustomerInfoEditService;
import com.deloitte.system.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 17/04/2020
 * @Description: 客户信息审核服务接口实现
 */
@Service
public class CustomerInfoEditServiceImpl extends BaseServiceImpl implements CustomerInfoEditService {

    @Resource
    private CustomerInfoEditMapper customerInfoEditMapper;

    @Resource
    private CustomerInfoMapper customerInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(CustomerInfoEditServiceImpl.class);

    @Override
    public List<CustomerInfoEdit> customerAuditList(CustomerInfoEdit param) {
        logger.info("调用customerAuditList方法，参数为:{}", JSON.toJSONString(param));
        // 根据角色查询需要审核的数据
        Long userId = UserUtils.getUserId();
        param.setAuditRole(getRole(userId));
        PageHelperUtils.startPage();
        return customerInfoEditMapper.selectList(param);
    }

    @Override
    public CustomerInfoEdit queryCustomerEdit(Long id) {
        if (id == null) {
            throw new BusinessException("调用queryCustomerEdit方法参数id为空！");
        }

        return customerInfoEditMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void auditCustomerEdit(String auditType, Long[] ids) {

        logger.info("调用auditCustomerEdit方法，参数auditType为:{}，参数ids为:{}", JSON.toJSONString(auditType), JSON.toJSONString(ids));
        if (StringUtils.isBlank(auditType)) {
            throw new BusinessException("调用auditCustomerEdit方法参数auditType为空！");
        }
        if (ids == null || ids.length == 0) {
            throw new BusinessException("调用auditCustomerEdit方法参数ids为空！");
        }

        // 数据变动审核 管理员--> 超级管理员
        Long userId = UserUtils.getUserId();
        String roleCode = getRole(userId);
        if (StringUtils.equals(auditType, CustomerInfoConstants.AUDIT_TYPE_APPROVE)) {
            for (Long id : ids) {
                CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(id);
                CustomerInfoEdit customerInfoEdit = customerInfoEditMapper.selectByPrimaryKey(id);
                customerInfo.setUpdater(userId);
                customerInfoEdit.setUpdater(userId);

                // 超级管理员审核
                if (StringUtils.equals(roleCode, CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE)) {
                    // 修改类型的数据
                    if (StringUtils.equals(customerInfoEdit.getChangeType(), CustomerInfoConstants.CHANGE_TYPE_EDIT)) {
                        org.springframework.beans.BeanUtils.copyProperties(customerInfoEdit, customerInfo);
                        customerInfo.setStatus(CustomerInfoConstants.CHANGE_STATUS_COMPLETED);
                    }
                    // 删除类型的数据
                    if (StringUtils.equals(customerInfoEdit.getChangeType(), CustomerInfoConstants.CHANGE_TYPE_DELETE)) {
                        customerInfo.setDeleteFlag(1);
                        customerInfo.setStatus(CustomerInfoConstants.CHANGE_STATUS_COMPLETED);
                    }
                    // 删除数据
                    customerInfoEditMapper.deleteByPrimaryKey(id);
                } else {
                    customerInfo.setAuditRole(CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE);
                    customerInfoEdit.setAuditRole(CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE);
                    customerInfoEditMapper.updateByPrimaryKey(customerInfoEdit);
                }
                // 更新状态
                customerInfoMapper.updateByPrimaryKey(customerInfo);
            }
        } else if (StringUtils.equals(auditType, CustomerInfoConstants.AUDIT_TYPE_REFUSE)) {
            // 审核不通过 更改源数据状态为拒绝  删除审核中的数据
            customerInfoMapper.updateStatus(CustomerInfoConstants.CHANGE_STATUS_REFUSE, ids);
            customerInfoEditMapper.deleteCustomerInfoEdit(ids);
        }
    }

}
