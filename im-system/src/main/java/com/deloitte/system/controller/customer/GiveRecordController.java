package com.deloitte.system.controller.customer;

import com.deloitte.common.model.AjaxResult;
import com.deloitte.framework.exception.BusinessException;
import com.deloitte.framework.page.TableDataInfo;
import com.deloitte.framework.support.BaseController;
import com.deloitte.system.model.customer.GiveRecord;
import com.deloitte.system.model.customer.GiveRecordEdit;
import com.deloitte.system.model.customer.GiveRecordExcel;
import com.deloitte.system.model.customer.PaymentRecord;
import com.deloitte.system.service.customer.GiveRecordEditService;
import com.deloitte.system.service.customer.GiveRecordService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 04/05/2020
 * @Description: 赠送记录控制器
 */
@RestController
@RequestMapping("/system/giveRecord")
public class GiveRecordController extends BaseController {

    @Resource
    private GiveRecordService giveRecordService;

    @Resource
    private GiveRecordEditService giveRecordEditService;

    /**
     * 下载赠送记录模板
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("/downloadGiveExcel")
    public void downloadGiveExcel(HttpServletResponse response) throws Exception {
        giveRecordService.downloadGiveExcel(response);
    }

    /**
     * 上传赠送记录信息
     *
     * @param file
     * @param request
     */
    @RequestMapping("/uploadGiveExcel")
    public AjaxResult uploadGiveExcel(MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();//原文件名字
                InputStream is = file.getInputStream();//获取输入流
                giveRecordService.uploadGiveExcel(is, originalFilename);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException(e.getMessage());
            }
        }
        return success();
    }

    /**
     * 保存excel上传的赠送记录
     *
     * @return
     */
    @RequestMapping("/saveGiveExcel")
    public AjaxResult saveGiveExcel() {

        giveRecordService.saveGiveExcel();
        return success();
    }

    /**
     * 查询excel上传赠送记录
     *
     * @param user
     * @return
     */
    @RequestMapping("/giveExcelList")
    public TableDataInfo giveExcelList(GiveRecordExcel param) {
        List<GiveRecordExcel> list = giveRecordService.giveExcelList(param);
        return getDataTable(list);
    }

    /**
     * 赠送记录列表
     *
     * @param user
     * @return
     */
    @RequestMapping("/giveList")
    public TableDataInfo giveList(GiveRecord param) {
        List<GiveRecord> list = giveRecordService.giveList(param);
        return getDataTable(list);
    }


    /**
     * 导出赠送记录信息
     *
     * @param paymentRecord
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportGiveData", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void exportGiveData(GiveRecord giveRecord, HttpServletRequest request, HttpServletResponse response) {
        giveRecordService.exportGiveData(giveRecord, request, response);
    }


    /**
     * 根据id查询赠送记录
     *
     * @param id
     * @return
     */
    @RequestMapping("/queryGiveRecord")
    public AjaxResult queryGiveRecord(Long id) {
        return AjaxResult.success(giveRecordService.queryGiveRecord(id));
    }

    /**
     * 更新赠送记录
     *
     * @param giveInfo
     * @return
     */
    @RequestMapping("/updateGiveRecord")
    public AjaxResult updateGiveRecord(@RequestBody GiveRecord giveInfo) {
        return AjaxResult.success(giveRecordService.updateGiveRecord(giveInfo));
    }

    /**
     * 添加赠送记录
     *
     * @param giveInfo
     * @return
     */
    @RequestMapping("/addGiveRecord")
    public AjaxResult addGiveRecord(GiveRecord giveInfo) {
        return AjaxResult.success(giveRecordService.addGiveRecord(giveInfo));
    }

    /**
     * 删除赠送记录
     *
     * @param ids
     * @return
     */
    @RequestMapping("/deleteGiveRecord")
    public AjaxResult deleteGiveRecord(@RequestParam(value = "ids[]") Long[] ids) {
        return AjaxResult.success(giveRecordService.deleteGiveRecord(ids));
    }

    /**
     * 赠送记录数据变动审核列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/giveAuditList")
    public TableDataInfo giveAuditList(GiveRecordEdit param) {
        List<GiveRecordEdit> list = giveRecordEditService.giveAuditList(param);
        return getDataTable(list);
    }

    /**
     * 根据id查询赠送记录修改详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/queryGiveEdit")
    public AjaxResult queryGiveEdit(Long id) {
        return AjaxResult.success(giveRecordEditService.queryGiveEdit(id));
    }

    /**
     * 审核赠送记录数据变更
     *
     * @param auditType
     * @param ids
     * @return
     */
    @RequestMapping("/auditGiveEdit")
    public AjaxResult auditGiveEdit(@RequestParam String auditType, @RequestParam("ids[]") Long[] ids) {
        giveRecordEditService.auditGiveEdit(auditType, ids);
        return AjaxResult.success();
    }

    /**
     * 赠送记录信息统计
     * @param giveRecord
     * @return
     */
    @RequestMapping("/giveInfoData")
    public AjaxResult giveInfoData(GiveRecord giveRecord){
        return AjaxResult.success(giveRecordService.giveInfoData(giveRecord));
    }

    /**
     * 查询指定用户的客户(渠道客户和直接客户)信息-userId为空时查询当前用户
     * @param userId
     * @return
     */
    @RequestMapping("/queryCustomerInfo")
    public AjaxResult queryCustomerInfo(Long userId){
        return AjaxResult.success(giveRecordService.queryCustomerInfo(userId));
    }

}
