package com.deloitte.system.model.customer;

import com.deloitte.common.model.BaseEntity;
import com.deloitte.common.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 赠送记录
 */
public class GiveRecord extends BaseEntity {

    /**
     * 客户账号
     */
    private String customerCode;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 客户类型 channel：渠道客户 direct：直接客户
     */
    private String customerType;

    /**
     * 受赠人
     */
    private String doneeName;

    /**
     * 账户信息
     */
    private String accountInfo;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 只能对未提交（未赠送）和拒绝状态的数据进行编辑，审核中和审核完成（已赠送）的数据只能进行删除操作
     * 销售对审核完成的数据进行删除需要审核，其他状态数据的操作不需要审核，立即生效
     * 赠送状态 ungive：未赠送 audit：审核中  refuse：拒绝 give：已赠送
     */
    private String giveStatus;

    /**
     * 赠送记录审核人角色
     */
    private String giveAuditRole;

    /**
     * 数据变更审核人角色
     */
    private String auditRole;

    /**
     * 数据变动审核状态  unchanged :未改变  audit:审核中  refuse：拒绝  completed:审核完成
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

    private Boolean adminFlag;

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

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getDoneeName() {
        return doneeName;
    }

    public void setDoneeName(String doneeName) {
        this.doneeName = doneeName;
    }

    public String getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(String accountInfo) {
        this.accountInfo = accountInfo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGiveStatus() {
        return giveStatus;
    }

    public void setGiveStatus(String giveStatus) {
        this.giveStatus = giveStatus;
    }

    public String getGiveAuditRole() {
        return giveAuditRole;
    }

    public void setGiveAuditRole(String giveAuditRole) {
        this.giveAuditRole = giveAuditRole;
    }

    public String getAuditRole() {
        return auditRole;
    }

    public void setAuditRole(String auditRole) {
        this.auditRole = auditRole;
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
}