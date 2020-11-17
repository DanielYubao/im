package com.deloitte.system.model.customer;

import com.deloitte.common.model.BaseEntity;

/**
 * 客户信息
 */
public class CustomerInfo extends BaseEntity {

    /**
     * 客户账号
     */
    private String customerCode;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系方式
     */
    private String contactInfo;

    /**
     * 新购佣金比例
     */
    private Double newAmountRate;

    /**
     * 续费佣金比例
     */
    private Double renewAmountRate;

    /**
     * 支付方式 bank：银行卡 other：其他 all：两种
     */
    private String paymentType;

    /**
     * 客户银行账号
     */
    private String customerBankCode;

    /**
     * 银行开户行
     */
    private String customerBankName;

    /**
     * 支付账号
     */
    private String paymentCode;

    /**
     * 审核人角色
     */
    private String auditRole;

    /**
     * 审核人id
     */
    private Long auditId;

    /**
     * 状态  unchanged :未改变  audit:审核中  completed:审核完成
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


    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Double getNewAmountRate() {
        return newAmountRate;
    }

    public String getAuditRole() {
        return auditRole;
    }

    public void setAuditRole(String auditRole) {
        this.auditRole = auditRole;
    }

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public void setNewAmountRate(Double newAmountRate) {
        this.newAmountRate = newAmountRate;
    }

    public Double getRenewAmountRate() {
        return renewAmountRate;
    }

    public void setRenewAmountRate(Double renewAmountRate) {
        this.renewAmountRate = renewAmountRate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCustomerBankCode() {
        return customerBankCode;
    }

    public void setCustomerBankCode(String customerBankCode) {
        this.customerBankCode = customerBankCode;
    }

    public String getCustomerBankName() {
        return customerBankName;
    }

    public void setCustomerBankName(String customerBankName) {
        this.customerBankName = customerBankName;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
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
}