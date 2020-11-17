package com.deloitte.system.controller.customer;

import com.deloitte.common.model.AjaxResult;
import com.deloitte.framework.exception.BusinessException;
import com.deloitte.framework.page.TableDataInfo;
import com.deloitte.framework.support.BaseController;
import com.deloitte.system.model.customer.*;
import com.deloitte.system.service.customer.ChannelCustomerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.nio.channels.Channel;
import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 30/05/2020
 * @Description: 渠道客户控制器
 */
@RestController
@RequestMapping("/system/channelCustomer")
public class ChannelCustomerController extends BaseController {
    
    @Resource
    private ChannelCustomerService channelCustomerService;

    /**
     * 下载渠道客户信息上传模板
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("/downloadCustomerExcel")
    public void downloadCustomerExcel(HttpServletResponse response) throws Exception {

        channelCustomerService.downloadCustomerExcel(response);
    }

    /**
     * 上传渠道客户信息
     *
     * @param file
     * @param request
     */
    @RequestMapping("/uploadCustomerExcel")
    public AjaxResult uploadCustomerExcel(MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();//原文件名字
                InputStream is = file.getInputStream();//获取输入流
                channelCustomerService.uploadCustomerExcel(is, originalFilename);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException(e.getMessage());
            }
        }
        return success();
    }

    /**
     * 保存excel上传的渠道客户信息
     *
     * @return
     */
    @RequestMapping("/saveCustomerExcel")
    public AjaxResult saveCustomerExcel() {

        channelCustomerService.saveCustomerExcel();
        return success();
    }

    /**
     * 查询上传渠道客户信息
     *
     * @param user
     * @return
     */
    @RequestMapping("/customerExcelList")
    public TableDataInfo customerExcelList(ChannelCustomerInfoExcel param) {
        List<ChannelCustomerInfoExcel> list = channelCustomerService.customerExcelList(param);
        return getDataTable(list);
    }

    /**
     * 渠道客户信息列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/customerList")
    public TableDataInfo customerList(ChannelCustomerInfo param) {
        List<ChannelCustomerInfo> list = channelCustomerService.customerList(param);
        return getDataTable(list);
    }

    /**
     * 根据id查询渠道客户信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/queryCustomerInfo")
    public AjaxResult queryCustomerInfo(Long id) {
        return AjaxResult.success(channelCustomerService.queryCustomerInfo(id));
    }

    /**
     * 更新渠道客户信息
     *
     * @param channelCustomerInfo
     * @return
     */
    @RequestMapping("/updateCustomerInfo")
    public AjaxResult updateCustomerInfo(@RequestBody ChannelCustomerInfoVO channelCustomerInfo) {
        return AjaxResult.success(channelCustomerService.updateCustomerInfo(channelCustomerInfo));
    }

    /**
     * 添加渠道客户信息
     *
     * @param channelCustomerInfo
     * @return
     */
    @RequestMapping("/addCustomerInfo")
    public AjaxResult addCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {
        return AjaxResult.success(channelCustomerService.addCustomerInfo(channelCustomerInfo));
    }

    /**
     * 删除渠道客户信息
     *
     * @param ids
     * @return
     */
    @RequestMapping("/deleteCustomerInfo")
    public AjaxResult deleteCustomerInfo(@RequestParam(value = "ids[]") Long[] ids) {
        return AjaxResult.success(channelCustomerService.deleteCustomerInfo(ids));
    }

    /**
     * 渠道客户信息审核列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/customerAuditList")
    public TableDataInfo customerAuditList(ChannelCustomerInfoEdit param) {
        List<ChannelCustomerInfoEdit> list = channelCustomerService.customerAuditList(param);
        return getDataTable(list);
    }


    /**
     * 审核渠道客户信息变更
     *
     * @param auditType
     * @param ids
     * @return
     */
    @RequestMapping("/auditCustomerEdit")
    public AjaxResult auditCustomerEdit(@RequestParam String auditType, @RequestParam("ids[]") Long[] ids) {
        channelCustomerService.auditCustomerEdit(auditType, ids);
        return AjaxResult.success();
    }

    /**
     * 修改客户信息归属人
     * @param customerIds
     * @param targetCreateBy
     * @return
     */
    @RequestMapping("/modifyCreateBy")
    public AjaxResult modifyCreateBy(@RequestParam("customerIds[]") Long[] customerIds, @RequestParam String targetCreateBy){
        return AjaxResult.success(channelCustomerService.modifyCreateBy(customerIds, targetCreateBy));
    }

}
