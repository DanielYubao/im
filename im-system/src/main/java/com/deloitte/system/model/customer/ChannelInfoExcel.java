package com.deloitte.system.model.customer;

import java.util.Date;

public class ChannelInfoExcel {
    private Long id;

    private String channelName;

    private String contact;

    private String contactInfo;

    private String newAmountRate;

    private String renewAmountRate;

    private String paymentType;

    private String channelBankCode;

    private String channelBankName;

    private String paymentCode;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private String validateMsg;

    private Integer validateFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo == null ? null : contactInfo.trim();
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
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public String getChannelBankCode() {
        return channelBankCode;
    }

    public void setChannelBankCode(String channelBankCode) {
        this.channelBankCode = channelBankCode == null ? null : channelBankCode.trim();
    }

    public String getChannelBankName() {
        return channelBankName;
    }

    public void setChannelBankName(String channelBankName) {
        this.channelBankName = channelBankName == null ? null : channelBankName.trim();
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode == null ? null : paymentCode.trim();
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

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getValidateMsg() {
        return validateMsg;
    }

    public void setValidateMsg(String validateMsg) {
        this.validateMsg = validateMsg == null ? null : validateMsg.trim();
    }

    public Integer getValidateFlag() {
        return validateFlag;
    }

    public void setValidateFlag(Integer validateFlag) {
        this.validateFlag = validateFlag;
    }
}