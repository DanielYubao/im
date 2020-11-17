package com.deloitte.system.mapper.customer;


import com.deloitte.system.model.customer.CustomerInfo;
import com.deloitte.system.model.customer.PaymentRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PaymentRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaymentRecord record);

    int insertSelective(PaymentRecord record);

    PaymentRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaymentRecord record);

    int updateByPrimaryKey(PaymentRecord record);

    /**
     * 查询列表
     * @param param
     * @return
     */
    List<PaymentRecord> selectList(PaymentRecord param);

    /**
     * 导出打款记录
     * @param param
     * @return
     */
    List<Map<String,Object>> selectExportData(PaymentRecord param);

    /**
     * 查询付款流程审核列表
     * @param param
     * @return
     */
    List<PaymentRecord> selectPaymentFlowList(PaymentRecord param);

    /**
     * 删除打款记录信息
     * @param ids
     * @return
     */
    int deletePaymentRecord(@Param("array")Long[] ids, @Param("userId")Long userId);

    /**
     * 批量更新数据b变更审核状态
     * @param status
     * @param ids
     * @return
     */
    int updateStatus(@Param("status")String status, @Param("array")Long[] ids);

    /**
     * 批量更新付款流程审核信息
     * @param auditStatus
     * @param auditRole
     * @param ids
     * @return
     */
    int updateAuditInfo(@Param("auditStatus")String auditStatus, @Param("auditRole")String auditRole, @Param("array")Long[] ids);

    /**
     * 提交打款记录审核
     * @param ids
     * @param userId
     */
    void submitPaymentAudit(@Param("array")Long[] ids, @Param("userId")Long userId);

    /**
     * 打款信息统计
     * @param param
     * @return
     */
    Map<String, Object> paymentInfoData(PaymentRecord param);
}