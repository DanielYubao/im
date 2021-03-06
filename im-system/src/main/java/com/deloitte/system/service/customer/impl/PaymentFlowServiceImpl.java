package com.deloitte.system.service.customer.impl;

import com.alibaba.fastjson.JSON;
import com.deloitte.common.utils.StringUtils;
import com.deloitte.framework.exception.BusinessException;
import com.deloitte.framework.utils.PageHelperUtils;
import com.deloitte.system.common.CustomerInfoConstants;
import com.deloitte.system.mapper.customer.PaymentRecordMapper;
import com.deloitte.system.model.customer.PaymentRecord;
import com.deloitte.system.service.customer.PaymentFlowService;
import com.deloitte.system.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 03/05/2020
 * @Description: 付款流程服务接口实现
 */
@Service
public class PaymentFlowServiceImpl extends BaseServiceImpl implements PaymentFlowService {

    @Resource
    private PaymentRecordMapper paymentRecordMapper;

    private static final Logger logger = LoggerFactory.getLogger(PaymentFlowServiceImpl.class);

    @Override
    public void submitPaymentAudit(Long[] ids) {

        logger.info("调用submitPaymentAudit方法，参数为:{}", JSON.toJSONString(ids));
        if (ids == null || ids.length == 0) {
            throw new BusinessException("调用submitPaymentAudit方法参数ids为空！");
        }
        Long userId = UserUtils.getUserId();

        paymentRecordMapper.submitPaymentAudit(ids, userId);
    }


    @Override
    public List<PaymentRecord> paymentFlowList(PaymentRecord param) {

        logger.info("调用paymentFlowList方法，参数为：{}", JSON.toJSONString(param));
        // 根据角色查询需要审核的数据
        Long userId = UserUtils.getUserId();
        param.setPaymentAuditRole(getRole(userId));

        PageHelperUtils.startPage();
        return paymentRecordMapper.selectPaymentFlowList(param);
    }

    @Override
    public void auditPaymentFlow(String auditType, Long[] ids) {

        logger.info("调用auditPaymentFlow方法，参数为auditType:{},参数ids为：{}", JSON.toJSONString(auditType), JSON.toJSONString(ids));
        if(StringUtils.isBlank(auditType)){
            throw new BusinessException("调用auditPaymentFlow方法参数auditType为空！");
        }
        if (ids == null || ids.length == 0){
            throw new BusinessException("调用auditPaymentFlow方法参数ids为空！");
        }
        Long userId = UserUtils.getUserId();
        String roleCode = getRole(userId);
        String auditRole = CustomerInfoConstants.FINANCE_ROLE_CODE;
        // 审核通过
        if (StringUtils.equals(CustomerInfoConstants.AUDIT_TYPE_APPROVE, auditType)){
            if (StringUtils.equals(roleCode, CustomerInfoConstants.FINANCE_ROLE_CODE)){
                auditRole =  CustomerInfoConstants.ADMIN_ROLE_CODE;
                auditType = CustomerInfoConstants.PAYMENT_STATUS_AUDIT;
            }
            if (StringUtils.equals(roleCode, CustomerInfoConstants.ADMIN_ROLE_CODE)){
                auditRole =  CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE;
                auditType = CustomerInfoConstants.PAYMENT_STATUS_AUDIT;
            }
            if (StringUtils.equals(roleCode, CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE)){
                auditRole =  CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE;
                auditType = CustomerInfoConstants.PAYMENT_STATUS_YES;
            }
        }else {
            auditRole = roleCode;
        }

        paymentRecordMapper.updateAuditInfo(auditType, auditRole, ids);
    }
}
