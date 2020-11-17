package com.deloitte.system.model.customer;

import com.deloitte.common.model.BaseEntity;

public class PaymentRecordEdit extends BaseEntity {

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
     * 数据变更审核人角色
     */
    private String auditRole;

    /**
     * 变更类型  delete：删除 change：修改
     */
    private String changeType;

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

    public String getAuditRole() {
        return auditRole;
    }

    public void setAuditRole(String auditRole) {
        this.auditRole = auditRole;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }
}