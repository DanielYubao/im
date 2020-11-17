package com.deloitte.system.service.customer.impl;

import com.alibaba.fastjson.JSON;
import com.deloitte.common.utils.BeanUtils;
import com.deloitte.common.utils.StringUtils;
import com.deloitte.framework.exception.BusinessException;
import com.deloitte.framework.utils.PageHelperUtils;
import com.deloitte.system.common.CustomerInfoConstants;
import com.deloitte.system.mapper.customer.ChannelCustomerInfoEditMapper;
import com.deloitte.system.mapper.customer.ChannelCustomerInfoExcelMapper;
import com.deloitte.system.mapper.customer.ChannelCustomerInfoMapper;
import com.deloitte.system.mapper.customer.ChannelInfoMapper;
import com.deloitte.system.model.customer.*;
import com.deloitte.system.model.sys.User;
import com.deloitte.system.service.customer.ChannelCustomerService;
import com.deloitte.system.service.customer.ChannelManageService;
import com.deloitte.system.utils.FileUtils;
import com.deloitte.system.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 30/05/2020
 * @Description: 渠道客户服务接口实现
 */
@Service
public class ChannelCustomerServiceImpl extends BaseServiceImpl implements ChannelCustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerInfoServiceImpl.class);

    @Resource
    private ChannelCustomerInfoMapper channelCustomerInfoMapper;

    @Resource
    private ChannelCustomerInfoExcelMapper channelCustomerInfoExcelMapper;

    @Resource
    private ChannelCustomerInfoEditMapper channelCustomerInfoEditMapper;

    @Resource
    private ChannelInfoMapper channelInfoMapper;

    @Override
    public void downloadCustomerExcel(HttpServletResponse response) throws Exception {
        FileUtils.downloadFile("渠道客户信息模板.xlsx", null, response);
    }

    @Override
    public void uploadCustomerExcel(InputStream inputStream, String fileName) {
        FileUtils.isExcel(fileName);
        List<List<String>> list = FileUtils.readXlsx(inputStream, 2);
        logger.info("删除当前用户:{},之前导入到channel_customer_info_excel表中的数据", UserUtils.getUserId());
        channelCustomerInfoExcelMapper.deleteByCreateBy(UserUtils.getUserId());

        logger.info("读取excel数据为：{}", com.alibaba.fastjson.JSON.toJSONString(list));
        // 验证并保存数据
        validateAndSave(list);

    }

    /**
     * 验证并保存数据
     *
     * @param list
     */
    private void validateAndSave(List<List<String>> list) {

        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException("文件内容为空!");
        }
        for (List<String> data : list) {
            Integer excelFlag = 1;
            StringBuffer excelMsg = new StringBuffer();
            // 1.客户账号  2.关联渠道  3.客户名称 4.联系人 5.联系方式
            String customerCode = data.get(0);
            if (StringUtils.isBlank(customerCode)) {
                excelFlag = 0;
                excelMsg.append("客户账号为空；");
            }
            String channelName = StringUtils.trim(data.get(1));
            if (StringUtils.isBlank(channelName)){
                excelFlag = 0;
                excelMsg.append("关联的渠道信息为空；");
            }
            Long channelId = null;
            Long userId = UserUtils.getUserId();
            ChannelInfo param = new ChannelInfo();
            param.setCreateBy(userId);
            param.setChannelName(channelName);
            List<ChannelInfo> channelInfos = channelInfoMapper.selectList(param);
            if (CollectionUtils.isEmpty(channelInfos)) {
                excelFlag = 0;
                excelMsg.append("关联的渠道信息不存在；");
            } else {
                channelId = channelInfos.get(0).getId();
            }
            String customerName = data.get(2);
            String contact = data.get(3);
            String contactInfo = data.get(4);
            // 验证没通过时，如果所有列数据都为空，则直接丢弃此行数据
            if (excelFlag == 0) {
                String totalData = customerCode + channelName + customerName + contact + contactInfo;
                if (StringUtils.isBlank(totalData)) {
                    continue;
                }
            } else {
                excelMsg.append("验证成功；");
            }

            // 组装数据并保存
            ChannelCustomerInfoExcel customerInfoExcel = new ChannelCustomerInfoExcel();
            customerInfoExcel.setCustomerCode(customerCode);
            customerInfoExcel.setChannelName(channelName);
            customerInfoExcel.setChannelId(channelId);
            customerInfoExcel.setCustomerName(customerName);
            customerInfoExcel.setContact(contact);
            customerInfoExcel.setContactInfo(contactInfo);
            customerInfoExcel.setCreateBy(userId);
            customerInfoExcel.setCreateTime(new Date());
            customerInfoExcel.setValidateFlag(excelFlag);
            customerInfoExcel.setValidateMsg(excelMsg.toString());
            channelCustomerInfoExcelMapper.insertSelective(customerInfoExcel);
        }
    }

    @Override
    public void saveCustomerExcel() {
        Long userId = UserUtils.getUserId();
        // 同步上传验证通过的excel数据
        channelCustomerInfoMapper.saveChannelCustomerExcel(userId);

        // 删除上传的excel数据
        channelCustomerInfoExcelMapper.deleteByCreateBy(userId);
    }

    @Override
    public List<ChannelCustomerInfoExcel> customerExcelList(ChannelCustomerInfoExcel param) {
        logger.info("调用customerExcelList方法，参数为:{}", JSON.toJSONString(param));
        // 管理员可以查看所有人员 销售只能查看自己
        boolean adminPermission = this.adminPermission(null);
        if (!adminPermission) {
            param.setCreateBy(UserUtils.getUserId());
        }
        PageHelperUtils.startPage();
        return channelCustomerInfoExcelMapper.selectList(param);
    }

    @Override
    public List<ChannelCustomerInfo> customerList(ChannelCustomerInfo param) {
        logger.info("调用customerList方法，参数为:{}", JSON.toJSONString(param));
        // 管理员可以查看所有人员 销售只能查看自己
        boolean adminPermission = this.adminPermission(null);
        if (!adminPermission) {
            param.setCreateBy(UserUtils.getUserId());
        }
        PageHelperUtils.startPage();
        return channelCustomerInfoMapper.selectList(param);
    }

    @Override
    public ChannelCustomerInfo queryCustomerInfo(Long id) {
        if (id == null) {
            throw new BusinessException("调用queryCustomerInfo方法参数id为空！");
        }
        return channelCustomerInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {

        logger.info("调用updateCustomerInfo方法，参数为:{}", JSON.toJSONString(channelCustomerInfo));
        if (channelCustomerInfo.getId() == null) {
            throw new BusinessException("调用updateCustomerInfo方法，参数id为空！");
        }
        // 管理员角色修改不需要审核
        boolean adminPermission = adminPermission(null);
        Long userId = UserUtils.getUserId();
        if (adminPermission) {
            channelCustomerInfo.setUpdater(userId);
            return channelCustomerInfoMapper.updateByPrimaryKey(channelCustomerInfo);
        }
        // 判断对象的属性是否有改变，有改变需要审核
        ChannelCustomerInfo oldCustomerInfo = channelCustomerInfoMapper.selectByPrimaryKey(channelCustomerInfo.getId());
        boolean changeFlag = BeanUtils.fieldEquals(oldCustomerInfo, channelCustomerInfo, null);
        if (!changeFlag) {
            // 变态变成审核中
            oldCustomerInfo.setStatus(CustomerInfoConstants.CHANGE_STATUS_AUDIT);
            oldCustomerInfo.setAuditRole(CustomerInfoConstants.ADMIN_ROLE_CODE);
            oldCustomerInfo.setUpdater(userId);
            channelCustomerInfoMapper.updateByPrimaryKey(oldCustomerInfo);

            ChannelCustomerInfoEdit customerInfoEdit = new ChannelCustomerInfoEdit();
            BeanUtils.copyBeanProp(customerInfoEdit, channelCustomerInfo);
            customerInfoEdit.setAuditRole(CustomerInfoConstants.ADMIN_ROLE_CODE);
            customerInfoEdit.setUpdater(userId);
            customerInfoEdit.setChangeType(CustomerInfoConstants.CHANGE_TYPE_EDIT);
            return channelCustomerInfoEditMapper.insert(customerInfoEdit);
        }
        return 0;
    }

    @Override
    public String addCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {

        logger.info("调用addCustomerInfo方法，参数为:{}", JSON.toJSONString(channelCustomerInfo));
        channelCustomerInfo.setStatus(CustomerInfoConstants.CHANGE_STATUS_UNCHANGED);
        channelCustomerInfo.setCreator(UserUtils.getUserId());
        channelCustomerInfo.setUpdater(UserUtils.getUserId());
        channelCustomerInfo.setDeleteAndDisableDefault();

        channelCustomerInfoMapper.insert(channelCustomerInfo);
        return CustomerInfoConstants.OPERATION_SUCCESS;
    }

    @Override
    public int deleteCustomerInfo(Long[] ids) {

        logger.info("调用deleteCustomerInfo方法，参数为:{}", JSON.toJSONString(ids));
        if (ids == null || ids.length == 0) {
            throw new BusinessException("调用deleteCustomerInfo方法，参数ids为空");
        }
        // 管理员角色删除不需要审核
        boolean adminPermission = adminPermission(null);
        Long userId = UserUtils.getUserId();
        if (adminPermission) {
            return channelCustomerInfoMapper.deleteChannelCustomerInfo(ids, userId);
        }

        ChannelCustomerInfo oldCustomerInfo = null;
        ChannelCustomerInfoEdit customerInfoEdit = null;
        for (Long id : ids) {
            customerInfoEdit = new ChannelCustomerInfoEdit();
            oldCustomerInfo = channelCustomerInfoMapper.selectByPrimaryKey(id);
            oldCustomerInfo.setAuditRole(CustomerInfoConstants.ADMIN_ROLE_CODE);
            oldCustomerInfo.setStatus(CustomerInfoConstants.CHANGE_STATUS_AUDIT);
            oldCustomerInfo.setUpdater(userId);
            channelCustomerInfoMapper.updateByPrimaryKey(oldCustomerInfo);

            BeanUtils.copyBeanProp(customerInfoEdit, oldCustomerInfo);
            customerInfoEdit.setChangeType(CustomerInfoConstants.CHANGE_TYPE_DELETE);
            channelCustomerInfoEditMapper.insert(customerInfoEdit);
        }

        return 0;
    }

    @Override
    public int modifyCreateBy(Long[] customerIds, String targetCreateBy) {

        logger.info("调用modifyCreateBy方法，参数customerIds为:{}，参数targetCreateBy为:{}", JSON.toJSONString(customerIds), JSON.toJSONString(targetCreateBy));
        if (customerIds == null || customerIds.length == 0) {
            throw new BusinessException("调用modifyCreateBy方法参数customerIds为空！");
        }

        if (StringUtils.isBlank(targetCreateBy)) {
            throw new BusinessException("调用modifyCreateBy方法参数targetCreateBy为空！");
        }

        return channelCustomerInfoMapper.modifyCreateBy(customerIds, targetCreateBy);
    }

    @Override
    public List<ChannelCustomerInfoEdit> customerAuditList(ChannelCustomerInfoEdit param) {
        logger.info("调用customerAuditList方法，参数为:{}", JSON.toJSONString(param));
        // 根据角色查询需要审核的数据
        Long userId = UserUtils.getUserId();
        param.setAuditRole(getRole(userId));

        PageHelperUtils.startPage();
        return channelCustomerInfoEditMapper.selectList(param);
    }

    @Override
    public void auditCustomerEdit(String auditType, Long[] ids) {

        logger.info("调用auditChannelEdit方法，参数auditType为:{}，参数ids为:{}", JSON.toJSONString(auditType), JSON.toJSONString(ids));
        if (StringUtils.isBlank(auditType)) {
            throw new BusinessException("调用auditCustomerEdit方法参数auditType为空！");
        }
        if (ids == null || ids.length == 0) {
            throw new BusinessException("调用auditCustomerEdit方法参数ids为空！");
        }

        // 数据变动审核 管理员--> 超级管理员
        Long userId = UserUtils.getUserId();
        String roleCode = getRole(userId);
        if (StringUtils.equals(auditType, CustomerInfoConstants.AUDIT_TYPE_APPROVE)) {
            for (Long id : ids) {
                ChannelCustomerInfo customerInfo = channelCustomerInfoMapper.selectByPrimaryKey(id);
                ChannelCustomerInfoEdit customerInfoEdit = channelCustomerInfoEditMapper.selectByPrimaryKey(id);
                customerInfo.setUpdater(userId);
                customerInfoEdit.setUpdater(userId);

                // 超级管理员审核
                if (StringUtils.equals(roleCode, CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE)) {
                    // 修改类型的数据
                    if (StringUtils.equals(customerInfoEdit.getChangeType(), CustomerInfoConstants.CHANGE_TYPE_EDIT)) {
                        org.springframework.beans.BeanUtils.copyProperties(customerInfoEdit, customerInfo);
                        customerInfo.setStatus(CustomerInfoConstants.CHANGE_STATUS_COMPLETED);
                    }
                    // 删除类型的数据
                    if (StringUtils.equals(customerInfoEdit.getChangeType(), CustomerInfoConstants.CHANGE_TYPE_DELETE)) {
                        customerInfo.setDeleteFlag(1);
                        customerInfo.setStatus(CustomerInfoConstants.CHANGE_STATUS_COMPLETED);
                    }
                    channelCustomerInfoEditMapper.deleteByPrimaryKey(id);
                } else {
                    customerInfo.setAuditRole(CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE);
                    customerInfoEdit.setAuditRole(CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE);
                    channelCustomerInfoEditMapper.updateByPrimaryKey(customerInfoEdit);
                }
                // 更新状态
                channelCustomerInfoMapper.updateByPrimaryKey(customerInfo);
            }
        } else if (StringUtils.equals(auditType, CustomerInfoConstants.AUDIT_TYPE_REFUSE)) {
            // 审核不通过 更改源数据状态为拒绝  删除审核中的数据
            channelCustomerInfoMapper.updateStatus(CustomerInfoConstants.CHANGE_STATUS_REFUSE, ids);
            channelCustomerInfoEditMapper.deleteCustomerInfoEdit(ids);
        }
    }
}
