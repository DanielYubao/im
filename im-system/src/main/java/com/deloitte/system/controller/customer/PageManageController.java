package com.deloitte.system.controller.customer;

import com.deloitte.system.common.SystemModualConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: QiaoYubao
 * @Date: 17/04/2020
 * @Description: 地址映射页面管理控制器
 */
@Controller
@RequestMapping("/system/pageManage")
public class PageManageController {

    private String prefix = SystemModualConstants.DEF_SYSTEM_MODUAL_TEMPLATES_PREFIX + "/customer";

    @GetMapping("/test")
    public String test() {
        return prefix + "/test";
    }

    /**
     * 客户信息页面
     *
     * @return
     */
    @GetMapping("/customerIndex")
    public String customerIndex() {
        return prefix + "/customerList";
    }


    /**
     * 佣金计算页面
     *
     * @return
     */
    @GetMapping("/amountIndex")
    public String amountIndex() {
        return prefix + "/amountList";
    }


    /**
     * 用户管理页面
     *
     * @return
     */
    @GetMapping("/userIndex")
    public String userIndex() {
        return prefix + "/userList";
    }

    /**
     * 客户信息审核页面
     *
     * @return
     */
    @RequestMapping("/customerAuditIndex")
    public String customerAuditIndex() {
        return prefix + "/customerAuditList";
    }

    /**
     * 打款记录页面
     *
     * @return
     */
    @RequestMapping("/paymentRecordIndex")
    public String paymentRecordIndex() {
        return prefix + "/paymentRecordList";
    }

    /**
     * 打款记录数据变动审核页面
     *
     * @return
     */
    @RequestMapping("/paymentRecordAuditIndex")
    public String paymentRecordAuditIndex() {
        return prefix + "/paymentRecordAuditList";
    }

    /**
     * 付款流程审核页面
     *
     * @return
     */
    @RequestMapping("/paymentFlowAuditIndex")
    public String paymentFlowAuditIndex() {
        return prefix + "/paymentFlowAuditList";
    }

    /**
     * 赠送记录页面
     *
     * @return
     */
    @RequestMapping("/giveRecordIndex")
    public String giveRecordIndex() {
        return prefix + "/giveRecordList";
    }

    /**
     * 赠送记录数据变动审核页面
     *
     * @return
     */
    @RequestMapping("/giveRecordAuditIndex")
    public String giveRecordAuditIndex() {
        return prefix + "/giveRecordAuditList";
    }

    /**
     * 赠送流程审核页面
     *
     * @return
     */
    @RequestMapping("/giveFlowAuditIndex")
    public String giveFlowAuditIndex() {
        return prefix + "/giveFlowAuditList";
    }

    /**
     * 客户信息统计页面
     *
     * @return
     */
    @RequestMapping("/customerDataIndex")
    public String customerDataIndex() {
        return prefix + "/customerDataList";
    }

    /**
     * 赠送信息统计页面
     *
     * @return
     */
    @RequestMapping("/giveDataIndex")
    public String giveDataIndex() {
        return prefix + "/giveDataList";
    }

    /**
     * 渠道管理信息页面
     *
     * @return
     */
    @GetMapping("/channelIndex")
    public String channelIndex() {
        return prefix + "/channelList";
    }

    /**
     * 渠道信息变动审核页面
     *
     * @return
     */
    @RequestMapping("/channelAuditIndex")
    public String channelAuditIndex() {
        return prefix + "/channelAuditList";
    }

    /**
     * 渠道客户信息页面
     *
     * @return
     */
    @GetMapping("/channelCustomerIndex")
    public String channelCustomerIndex() {
        return prefix + "/channelCustomerList";
    }

    /**
     * 渠道客户信息变更审核页面
     *
     * @return
     */
    @RequestMapping("/channelCustomerAuditIndex")
    public String channelCustomerAuditIndex() {
        return prefix + "/channelCustomerAuditList";
    }


    /**
     * 打款信息统计页面
     *
     * @return
     */
    @RequestMapping("/paymentDataIndex")
    public String paymentDataIndex() {
        return prefix + "/paymentDataList";
    }


}
