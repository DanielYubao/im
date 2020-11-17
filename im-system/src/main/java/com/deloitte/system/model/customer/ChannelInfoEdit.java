package com.deloitte.system.model.customer;

import com.deloitte.common.model.BaseEntity;

public class ChannelInfoEdit extends BaseEntity {

    /**
     * 渠道名称
     */
    private String channelName;

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
     * 渠道银行账号
     */
    private String channelBankCode;

    /**
     * 渠道银行开户行
     */
    private String channelBankName;

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
     * 变更类型  delete：删除 change：修改
     */
    private String changeType;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
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

    public String getChannelBankCode() {
        return channelBankCode;
    }

    public void setChannelBankCode(String channelBankCode) {
        this.channelBankCode = channelBankCode;
    }

    public String getChannelBankName() {
        return channelBankName;
    }

    public void setChannelBankName(String channelBankName) {
        this.channelBankName = channelBankName;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
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
}