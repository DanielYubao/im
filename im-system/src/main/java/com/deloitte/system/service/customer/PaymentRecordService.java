package com.deloitte.system.service.customer;


import com.deloitte.system.model.customer.PaymentRecord;
import com.deloitte.system.model.customer.PaymentRecordEdit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author: QiaoYubao
 * @Date: 21/04/2020
 * @Description: 打款记录服务接口
 */
public interface PaymentRecordService extends BaseService {

    /**
     * 添加打款记录信息
     * @param paymentInfo
     * @return
     */
    String addPaymentRecord(PaymentRecord paymentInfo);

    /**
     * 查询打款记录信息列表
     * @param paymentInfo
     * @return
     */
    List<PaymentRecord> paymentList(PaymentRecord param);

    /**
     * 根据id查询打款记录信息
     * @param id
     * @return
     */
    PaymentRecord queryPaymentRecord(Long id);

    /**
     * 更新打款记录信息
     * @param paymentInfo
     * @return
     */
    int updatePaymentRecord( PaymentRecord paymentInfo);


    /**
     * 删除打款记录信息
     * @param ids
     * @return
     */
    int deletePaymentRecord(Long[] ids);

    /**
     * 查询打款记录信息变更审核列表
     * @param param
     * @return
     */
    List<PaymentRecordEdit> paymentAuditList(PaymentRecordEdit param);

    /**
     * 查询打款记录信息修改详情
     * @param id
     * @return
     */
    PaymentRecordEdit queryPaymentEdit(Long id);

    /**
     * 打款记录信息变更审核
     * @param auditType
     * @param ids
     */
    void auditPaymentEdit(String auditType, Long[] ids);

    /**
     * 导出打款记录
     * @param paymentRecord
     */
    void exportPaymentData(PaymentRecord paymentRecord, HttpServletRequest request, HttpServletResponse response);

    /**
     * 打款信息统计
     * @param param
     * @return
     */
    Map<String, Object> paymentInfoData(PaymentRecord param);

}
