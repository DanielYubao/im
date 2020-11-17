package com.deloitte.system.model.customer;

import java.util.Date;

public class CustomerInfoExcel {

    private Long id;

    private String customerCode;

    private String customerName;

    private String contact;

    private String contactInfo;

    private String newAmountRate;

    private String renewAmountRate;

    private String paymentType;

    private String customerBankCode;

    private String customerBankName;

    private String paymentCode;

    private Long createBy;

    private Date createTime;

    private String validateMsg;

    private Integer validateFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getNewAmountRate() {
        return newAmountRate;
    }

    public void setNewAmountRate(String newAmountRate) {
        this.newAmountRate = newAmountRate;
    }

    public String getRenewAmountRate() {
        return renewAmountRate;
    }

    public void setRenewAmountRate(String renewAmountRate) {
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

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getValidateMsg() {
        return validateMsg;
    }

    public void setValidateMsg(String validateMsg) {
        this.validateMsg = validateMsg;
    }

    public Integer getValidateFlag() {
        return validateFlag;
    }

    public void setValidateFlag(Integer validateFlag) {
        this.validateFlag = validateFlag;
    }
}