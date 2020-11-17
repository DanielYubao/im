package com.deloitte.system.controller.customer;

import com.deloitte.common.model.AjaxResult;
import com.deloitte.framework.page.TableDataInfo;
import com.deloitte.framework.support.BaseController;
import com.deloitte.system.model.customer.PaymentRecord;
import com.deloitte.system.service.customer.PaymentFlowService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 03/05/2020
 * @Description: 付款流程控制器
 */
@RestController
@RequestMapping("/system/paymentFlow")
public class PaymentFlowController extends BaseController {

    @Resource
    private PaymentFlowService paymentFlowService;

    /**
     * 提交打款记录流程审核
     *
     * @param param
     * @return
     */
    @RequestMapping("/submitPaymentAudit")
    public AjaxResult submitPaymentAudit(@RequestParam(value = "ids[]") Long[] ids) {
        paymentFlowService.submitPaymentAudit(ids);
        return AjaxResult.success();
    }

    /**
     * 付款流程审核列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/paymentFlowList")
    public TableDataInfo paymentFlowList(PaymentRecord param) {
        List<PaymentRecord> list = paymentFlowService.paymentFlowList(param);
        return getDataTable(list);
    }

    /**
     * 付款流程审核
     *
     * @param auditType
     * @param ids
     * @return
     */
    @RequestMapping("/auditPaymentFlow")
    public AjaxResult auditPaymentFlow(@RequestParam String auditType, @RequestParam("ids[]") Long[] ids) {
        paymentFlowService.auditPaymentFlow(auditType, ids);
        return AjaxResult.success();
    }

}
