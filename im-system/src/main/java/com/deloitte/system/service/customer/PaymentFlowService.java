package com.deloitte.system.service.customer;

import com.deloitte.system.model.customer.PaymentRecord;

import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 03/05/2020
 * @Description: 付款流程服务接口
 */
public interface PaymentFlowService extends BaseService {

    /**
     * 提交打款记录审核
     * @param ids
     */
    void submitPaymentAudit(Long[] ids);


    /**
     * 付款流程审核列表
     * @param param
     * @return
     */
    List<PaymentRecord> paymentFlowList(PaymentRecord param);

    /**
     * 付款流程审核
     * @param auditType
     * @param ids
     */
    void auditPaymentFlow(String auditType, Long[] ids);

}
