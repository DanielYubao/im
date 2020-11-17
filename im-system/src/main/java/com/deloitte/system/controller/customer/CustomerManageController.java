package com.deloitte.system.controller.customer;

import com.deloitte.common.model.AjaxResult;
import com.deloitte.framework.exception.BusinessException;
import com.deloitte.framework.page.TableDataInfo;
import com.deloitte.framework.support.BaseController;
import com.deloitte.system.common.SystemModualConstants;
import com.deloitte.system.model.customer.CustomerInfo;
import com.deloitte.system.model.customer.CustomerInfoEdit;
import com.deloitte.system.model.customer.CustomerInfoExcel;
import com.deloitte.system.model.sys.User;
import com.deloitte.system.service.customer.CustomerInfoEditService;
import com.deloitte.system.service.customer.CustomerInfoService;
import com.deloitte.system.utils.UserUtils;
import javafx.scene.control.Tab;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 直接客户信息管理控制器
 */
@RestController
@RequestMapping("/system/customerManage")
public class CustomerManageController extends BaseController {

    private String prefix = SystemModualConstants.DEF_SYSTEM_MODUAL_TEMPLATES_PREFIX + "/customer";

    @Resource
    private CustomerInfoService customerInfoService;

    @Resource
    private CustomerInfoEditService customerInfoEditService;

    /**
     * 下载直接客户信息上传模板
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("/downloadCustomerExcel")
    public void downloadCustomerExcel(HttpServletResponse response) throws Exception {

        customerInfoService.downloadCustomerExcel(response);
    }

    /**
     * 上传直接客户信息
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
                customerInfoService.uploadCustomerExcel(is, originalFilename);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException(e.getMessage());
            }
        }
        return success();
    }

    /**
     * 保存excel上传的直接客户信息
     *
     * @return
     */
    @RequestMapping("/saveCustomerExcel")
    public AjaxResult saveCustomerExcel() {

        customerInfoService.saveCustomerExcel();
        return success();
    }

    /**
     * 查询上传直接客户信息
     *
     * @param user
     * @return
     */
    @RequestMapping("/customerExcelList")
    public TableDataInfo customerExcelList(CustomerInfoExcel param) {
        List<CustomerInfoExcel> list = customerInfoService.customerExcelList(param);
        return getDataTable(list);
    }

    /**
     * 直接客户信息列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/customerList")
    public TableDataInfo customerList(CustomerInfo param) {
        List<CustomerInfo> list = customerInfoService.customerList(param);
        return getDataTable(list);
    }

    /**
     * 根据id查询直接客户信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/queryCustomerInfo")
    public AjaxResult queryCustomerInfo(Long id) {
        return AjaxResult.success(customerInfoService.queryCustomerInfo(id));
    }

    /**
     * 更新直接客户信息
     *
     * @param customerInfo
     * @return
     */
    @RequestMapping("/updateCustomerInfo")
    public AjaxResult updateCustomerInfo(@RequestBody CustomerInfo customerInfo) {
        return AjaxResult.success(customerInfoService.updateCustomerInfo(customerInfo));
    }

    /**
     * 添加直接客户信息
     *
     * @param customerInfo
     * @return
     */
    @RequestMapping("/addCustomerInfo")
    public AjaxResult addCustomerInfo(CustomerInfo customerInfo) {
        return AjaxResult.success(customerInfoService.addCustomerInfo(customerInfo));
    }

    /**
     * 删除直接客户信息
     *
     * @param ids
     * @return
     */
    @RequestMapping("/deleteCustomerInfo")
    public AjaxResult deleteCustomerInfo(@RequestParam(value = "ids[]") Long[] ids) {
        return AjaxResult.success(customerInfoService.deleteCustomerInfo(ids));
    }

    /**
     * 判断用户是否有上传excel的权限--管理员可以上传多次，普通用户只能上传一次
     *
     * @param id
     * @return
     */
    @RequestMapping("/uploadPermission")
    public AjaxResult uploadPermission(Long id) {
        return AjaxResult.success(customerInfoService.uploadPermission(id));
    }

    /**
     * 判断用户是否有管理员权限
     *
     * @param id
     * @return
     */
    @RequestMapping("/adminPermission")
    public AjaxResult adminPermission(Long id) {
        return AjaxResult.success(customerInfoService.adminPermission(id));
    }

    /**
     * 判断用户是否有财务权限
     *
     * @param id
     * @return
     */
    @RequestMapping("/financePermission")
    public AjaxResult financePermission(Long id) {
        return AjaxResult.success(customerInfoService.financePermission(id));
    }

    /**
     * 直接客户信息审核列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/customerAuditList")
    public TableDataInfo customerAuditList(CustomerInfoEdit param) {
        List<CustomerInfoEdit> list = customerInfoEditService.customerAuditList(param);
        return getDataTable(list);
    }

    /**
     * 根据id查询直接客户信息修改详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/queryCustomerEdit")
    public AjaxResult queryCustomerEdit(Long id) {
        return AjaxResult.success(customerInfoEditService.queryCustomerEdit(id));
    }

    /**
     * 审核直接客户信息变更
     *
     * @param auditType
     * @param ids
     * @return
     */
    @RequestMapping("/auditCustomerEdit")
    public AjaxResult auditCustomerEdit(@RequestParam String auditType, @RequestParam("ids[]") Long[] ids) {
        customerInfoEditService.auditCustomerEdit(auditType, ids);
        return AjaxResult.success();
    }

    /**
     * 修改直接客户信息归属人
     * @param customerIds
     * @param targetCreateBy
     * @return
     */
    @RequestMapping("/modifyCreateBy")
    public AjaxResult modifyCreateBy(@RequestParam("customerIds[]") Long[] customerIds, @RequestParam String targetCreateBy){
        return AjaxResult.success(customerInfoService.modifyCreateBy(customerIds, targetCreateBy));
    }

    /**
     * 直接客户信息数据统计
     * @param param
     * @return
     */
    @RequestMapping("/customerInfoData")
    public AjaxResult customerInfoData(CustomerInfo param){
        return AjaxResult.success(customerInfoService.customerInfoData(param));
    }
}
