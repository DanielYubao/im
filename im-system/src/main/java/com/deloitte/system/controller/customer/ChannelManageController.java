package com.deloitte.system.controller.customer;

/**
 * @Author: QiaoYubao
 * @Date: 28/05/2020
 * @Description:
 */

import com.deloitte.common.model.AjaxResult;
import com.deloitte.framework.exception.BusinessException;
import com.deloitte.framework.page.TableDataInfo;
import com.deloitte.framework.support.BaseController;
import com.deloitte.system.model.customer.ChannelInfo;
import com.deloitte.system.model.customer.ChannelInfoExcel;
import com.deloitte.system.model.customer.ChannelInfoEdit;
import com.deloitte.system.service.customer.ChannelInfoEditService;
import com.deloitte.system.service.customer.ChannelManageService;
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
 * 渠道信息管理控制器
 */
@RestController
@RequestMapping("/system/channelManage")
public class ChannelManageController extends BaseController {
    
    @Resource
    private ChannelManageService channelManageService;

    @Resource
    private ChannelInfoEditService channelInfoEditService;

    /**
     * 下载渠道信息上传模板
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("/downloadChannelExcel")
    public void downloadChannelExcel(HttpServletResponse response) throws Exception {

        channelManageService.downloadChannelExcel(response);
    }

    /**
     * 上传渠道信息
     *
     * @param file
     * @param request
     */
    @RequestMapping("/uploadChannelExcel")
    public AjaxResult uploadChannelExcel(MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();//原文件名字
                InputStream is = file.getInputStream();//获取输入流
                channelManageService.uploadChannelExcel(is, originalFilename);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException(e.getMessage());
            }
        }
        return success();
    }

    /**
     * 保存excel上传的渠道信息
     *
     * @return
     */
    @RequestMapping("/saveChannelExcel")
    public AjaxResult saveChannelExcel() {
        channelManageService.saveChannelExcel();
        return success();
    }

    /**
     * 查询上传渠道信息
     *
     * @param user
     * @return
     */
    @RequestMapping("/channelExcelList")
    public TableDataInfo customerExcelList(ChannelInfoExcel param) {
        List<ChannelInfoExcel> list = channelManageService.channelExcelList(param);
        return getDataTable(list);
    }

    /**
     * 渠道信息列表
     *
     * @param user
     * @return
     */
    @RequestMapping("/channelList")
    public TableDataInfo customerList(ChannelInfo param) {
        List<ChannelInfo> list = channelManageService.channelList(param);
        return getDataTable(list);
    }

    /**
     * 根据id查询渠道信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/queryChannelInfo")
    public AjaxResult queryChannelInfo(Long id) {
        return AjaxResult.success(channelManageService.queryChannelInfo(id));
    }

    /**
     * 查询指定用户创建的渠道-userId为空时查询当前用户
     * @param userId
     * @return
     */
    @RequestMapping("/queryChannelByUserAndName")
    public AjaxResult queryChannelByUserAndName(Long userId, String channelName){
        return AjaxResult.success(channelManageService.queryChannelByUserAndName(userId, channelName));
    }

    /**
     * 更新渠道信息
     *
     * @param channelInfo
     * @return
     */
    @RequestMapping("/updateChannelInfo")
    public AjaxResult updateChannelInfo(@RequestBody ChannelInfo channelInfo) {
        return AjaxResult.success(channelManageService.updateChannelInfo(channelInfo));
    }

    /**
     * 添加渠道信息
     *
     * @param channelInfo
     * @return
     */
    @RequestMapping("/addChannelInfo")
    public AjaxResult addChannelInfo(ChannelInfo channelInfo) {
        return AjaxResult.success(channelManageService.addChannelInfo(channelInfo));
    }

    /**
     * 删除渠道信息
     *
     * @param ids
     * @return
     */
    @RequestMapping("/deleteChannelInfo")
    public AjaxResult deleteChannelInfo(@RequestParam(value = "ids[]") Long[] ids) {
        return AjaxResult.success(channelManageService.deleteChannelInfo(ids));
    }

    /**
     * 渠道信息变更审核列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/channelAuditList")
    public TableDataInfo channelAuditList(ChannelInfoEdit param) {
        List<ChannelInfoEdit> list = channelInfoEditService.channelAuditList(param);
        return getDataTable(list);
    }

    /**
     * 渠道信息变更审核
     *
     * @param auditType
     * @param ids
     * @return
     */
    @RequestMapping("/auditChannelEdit")
    public AjaxResult auditChannelEdit(@RequestParam String auditType, @RequestParam("ids[]") Long[] ids) {
        channelInfoEditService.auditChannelEdit(auditType, ids);
        return AjaxResult.success();
    }


}
