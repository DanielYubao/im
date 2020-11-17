package com.deloitte.system.model.customer;

import com.deloitte.common.model.BaseEntity;
import com.deloitte.common.utils.StringUtils;

public class PaymentRecord extends BaseEntity {

    /**
     * 客户账号
     */
    private String customerCode;

    /**
     * 客户信息id
     */
    private Long customerId;

    /**
     * 客户类型 channel：渠道客户 direct：直接客户
     */
    private String customerType;

    /**
     * 支付方式 bank：银行卡 other：其他
     */
    private String paymentType;

    /**
     * 新购金额
     */
    private Double newAmount;

    /**
     * 新购打款金额
     */
    private Double newPaymentAmount;

    /**
     * 续费金额
     */
    private Double renewAmount;

    /**
     * 续费打款金额
     */
    private Double renewPaymentAmount;

    /**
     * 备注说明
     */
    private String remark;

    /**
     * 只能对未提交（未打款）和拒绝状态的数据进行编辑，审核中和审核完成（已打款）的数据只能进行删除操作
     * 销售对审核完成的数据进行删除需要审核，其他状态数据的操作不需要审核，立即生效
     * 打款状态 unpayment：未打款 audit：审核中  refuse：拒绝 payment：已打款
     */
    private String paymentStatus;

    /**
     * 打款审核人角色
     */
    private String paymentAuditRole;

    /**
     * 数据变更审核人角色
     */
    private String auditRole;

    /**
     * 数据变更状态  unchanged :未改变  audit:审核中  refuse：拒绝 completed:审核完成
     */
    private String status;

    /**
     * 查询开始时间
     */
    private String startDate;

    /**
     * 查询结束时间
     */
    private String endDate;

    /**
     * 是否是管理员标识
     */
    private Boolean adminFlag;

    /**
     * 是否是财务人员标识  财务人员导出打款记录时，审核状态为拒绝的不导出
     */
    private Boolean financeFlag;

    public Boolean getFinanceFlag() {
        return financeFlag;
    }

    public void setFinanceFlag(Boolean financeFlag) {
        this.financeFlag = financeFlag;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        if (StringUtils.isBlank(endDate)){
            return endDate;
        }
        return endDate + " 23:59:59";
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Boolean getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(Boolean adminFlag) {
        this.adminFlag = adminFlag;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(Double newAmount) {
        this.newAmount = newAmount;
    }

    public Double getNewPaymentAmount() {
        return newPaymentAmount;
    }

    public void setNewPaymentAmount(Double newPaymentAmount) {
        this.newPaymentAmount = newPaymentAmount;
    }

    public Double getRenewAmount() {
        return renewAmount;
    }

    public void setRenewAmount(Double renewAmount) {
        this.renewAmount = renewAmount;
    }

    public Double getRenewPaymentAmount() {
        return renewPaymentAmount;
    }

    public void setRenewPaymentAmount(Double renewPaymentAmount) {
        this.renewPaymentAmount = renewPaymentAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentAuditRole() {
        return paymentAuditRole;
    }

    public void setPaymentAuditRole(String paymentAuditRole) {
        this.paymentAuditRole = paymentAuditRole;
    }

    public void setAuditRole(String auditRole) {
        this.auditRole = auditRole;
    }
    public String getAuditRole() {
        return auditRole;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}