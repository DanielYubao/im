package com.deloitte.system.controller.customer;

import com.deloitte.common.model.AjaxResult;
import com.deloitte.framework.exception.BusinessException;
import com.deloitte.framework.page.TableDataInfo;
import com.deloitte.framework.support.BaseController;
import com.deloitte.system.model.customer.PaymentRecord;
import com.deloitte.system.model.customer.PaymentRecordEdit;
import com.deloitte.system.service.customer.PaymentRecordService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 21/04/2020
 * @Description: 打款记录控制器
 */
@RestController
@RequestMapping("/system/paymentRecord")
public class PaymentRecordController extends BaseController {

    @Resource
    private PaymentRecordService paymentRecordService;

    /**
     * 打款记录列表
     *
     * @param user
     * @return
     */
    @RequestMapping("/paymentList")
    public TableDataInfo paymentList(PaymentRecord param) {
        List<PaymentRecord> list = paymentRecordService.paymentList(param);
        return getDataTable(list);
    }

    /**
     * 根据id查询打款记录
     *
     * @param id
     * @return
     */
    @RequestMapping("/queryPaymentRecord")
    public AjaxResult queryPaymentRecord(Long id) {
        return AjaxResult.success(paymentRecordService.queryPaymentRecord(id));
    }

    /**
     * 添加打款记录
     *
     * @param paymentInfo
     * @return
     */
    @RequestMapping("/addPaymentRecord")
    public AjaxResult addPaymentRecord(PaymentRecord paymentInfo) {
        return AjaxResult.success(paymentRecordService.addPaymentRecord(paymentInfo));
    }

    /**
     * 更新打款记录
     *
     * @param paymentInfo
     * @return
     */
    @RequestMapping("/updatePaymentRecord")
    public AjaxResult updatePaymentRecord(@RequestBody PaymentRecord paymentInfo) {
        return AjaxResult.success(paymentRecordService.updatePaymentRecord(paymentInfo));
    }

    /**
     * 删除打款记录
     *
     * @param ids
     * @return
     */
    @RequestMapping("/deletePaymentRecord")
    public AjaxResult deletePaymentRecord(@RequestParam(value = "ids[]") Long[] ids) {
        return AjaxResult.success(paymentRecordService.deletePaymentRecord(ids));
    }

    /**
     * 打款记录数据变更审核列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/paymentAuditList")
    public TableDataInfo paymentAuditList(PaymentRecordEdit param) {
        List<PaymentRecordEdit> list = paymentRecordService.paymentAuditList(param);
        return getDataTable(list);
    }

    /**
     * 根据id查询打款记录修改详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/queryPaymentEdit")
    public AjaxResult queryPaymentEdit(Long id) {
        return AjaxResult.success(paymentRecordService.queryPaymentEdit(id));
    }

    /**
     * 审核打款记录数据变更
     *
     * @param auditType
     * @param ids
     * @return
     */
    @RequestMapping("/auditPaymentEdit")
    public AjaxResult auditPaymentEdit(@RequestParam String auditType, @RequestParam("ids[]") Long[] ids) {
        paymentRecordService.auditPaymentEdit(auditType, ids);
        return AjaxResult.success();
    }

    /**
     * 导出打款记录信息
     *
     * @param paymentRecord
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportPaymentData", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void exportPaymentData(PaymentRecord paymentRecord, HttpServletRequest request, HttpServletResponse response) {
        paymentRecordService.exportPaymentData(paymentRecord, request, response);
    }

    /**
     * 打款记录信息统计
     * @param paymentRecord
     * @return
     */
    @RequestMapping("/paymentInfoData")
    public AjaxResult paymentInfoData(PaymentRecord paymentRecord){
        return AjaxResult.success(paymentRecordService.paymentInfoData(paymentRecord));
    }

}
