package com.deloitte.system.controller.customer;

import com.deloitte.common.model.AjaxResult;
import com.deloitte.framework.page.TableDataInfo;
import com.deloitte.framework.support.BaseController;
import com.deloitte.system.model.customer.GiveRecord;
import com.deloitte.system.service.customer.GiveFlowService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 03/05/2020
 * @Description: 赠送流程控制器
 */
@RestController
@RequestMapping("/system/giveFlow")
public class GiveFlowController extends BaseController {

    @Resource
    private GiveFlowService giveFlowService;

    /**
     * 提交赠送记录流程审核
     *
     * @param param
     * @return
     */
    @RequestMapping("/submitGiveAudit")
    public AjaxResult submitPaymentAudit(@RequestParam(value = "ids[]") Long[] ids) {
        giveFlowService.submitGiveAudit(ids);
        return AjaxResult.success();
    }

    /**
     * 赠送流程审核列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/giveFlowList")
    public TableDataInfo giveFlowList(GiveRecord param) {
        List<GiveRecord> list = giveFlowService.giveFlowList(param);
        return getDataTable(list);
    }

    /**
     * 赠送流程审核
     *
     * @param auditType
     * @param ids
     * @return
     */
    @RequestMapping("/auditGiveFlow")
    public AjaxResult auditGiveFlow(@RequestParam String auditType, @RequestParam("ids[]") Long[] ids) {
        giveFlowService.auditGiveFlow(auditType, ids);
        return AjaxResult.success();
    }

}
