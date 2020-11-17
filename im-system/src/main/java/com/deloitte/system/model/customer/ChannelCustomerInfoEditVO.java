package com.deloitte.system.model.customer;

/**
 * @Author: QiaoYubao
 * @Date: 03/06/2020
 * @Description:
 */
public class ChannelCustomerInfoEditVO extends ChannelCustomerInfoEdit {
    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 渠道新购佣金比例
     */
    private Double newAmountRate;

    /**
     * 渠道续费佣金比例
     */
    private Double renewAmountRate;

    /**
     * 渠道支付方式 bank：银行卡 other：其他 all：两种
     */
    private String paymentType;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
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
}
