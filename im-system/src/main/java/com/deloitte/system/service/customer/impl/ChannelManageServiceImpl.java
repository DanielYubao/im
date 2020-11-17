package com.deloitte.system.service.customer.impl;

import com.alibaba.fastjson.JSON;
import com.deloitte.common.utils.BeanUtils;
import com.deloitte.framework.exception.BusinessException;
import com.deloitte.framework.utils.PageHelperUtils;
import com.deloitte.system.common.CustomerInfoConstants;
import com.deloitte.system.mapper.customer.ChannelInfoEditMapper;
import com.deloitte.system.mapper.customer.ChannelInfoExcelMapper;
import com.deloitte.system.mapper.customer.ChannelInfoMapper;
import com.deloitte.system.model.customer.*;
import com.deloitte.system.service.customer.ChannelManageService;
import com.deloitte.system.utils.FileUtils;
import com.deloitte.system.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 28/05/2020
 * @Description: 渠道管理服务接口实现
 */
@Service
public class ChannelManageServiceImpl extends BaseServiceImpl implements ChannelManageService {

    private static final Logger logger = LoggerFactory.getLogger(ChannelManageServiceImpl.class);

    @Resource
    private ChannelInfoMapper channelInfoMapper;

    @Resource
    private ChannelInfoExcelMapper channelInfoExcelMapper;

    @Resource
    private ChannelInfoEditMapper channelInfoEditMapper;


    @Override
    public void downloadChannelExcel(HttpServletResponse response) throws Exception {
        FileUtils.downloadFile("渠道信息模板.xlsx", null, response);
    }

    @Override
    public void uploadChannelExcel(InputStream inputStream, String fileName) {

        FileUtils.isExcel(fileName);
        List<List<String>> list = FileUtils.readXlsx(inputStream, 2);
        logger.info("删除当前用户:{},之前导入到channel_info_excel表中的数据", UserUtils.getUserId());
        channelInfoExcelMapper.deleteByCreateBy(UserUtils.getUserId());

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
            //1.渠道名称  2.联系人  3.联系方式	4.新购佣金比例	5.续费佣金比例 6.支付方式 7.渠道银行账号 8.渠道银行开户行 9.支付账号
            String channelName = data.get(0);
            if (StringUtils.isBlank(channelName)) {
                excelFlag = 0;
                excelMsg.append("渠道名称为空；");
            }
            String contact = data.get(1);
            if (StringUtils.isBlank(contact)) {
                excelFlag = 0;
                excelMsg.append("联系人为空；");
            }
            String contactInfo = data.get(2);
            if (StringUtils.isBlank(contactInfo)) {
                excelFlag = 0;
                excelMsg.append("联系方式为空；");
            }
            String newAmountRate = data.get(3);
            String renewAmountRate = data.get(4);
            if (StringUtils.isBlank(newAmountRate) && StringUtils.isBlank(renewAmountRate)) {
                excelFlag = 0;
                excelMsg.append("新购和续费信息不能同时为空；");
            }
            String paymentType = data.get(5);
            if (StringUtils.isBlank(paymentType)) {
                excelFlag = 0;
                excelMsg.append("支付方式为空；");
            }
            String channelBankCode = data.get(6);
            String channelBankName = data.get(7);
            String paymentCode = data.get(8);
            if (StringUtils.equals("银行卡", paymentType)) {
                paymentCode = null;
                paymentType = "bank";
                if (StringUtils.isBlank(channelBankCode)) {
                    excelFlag = 0;
                    excelMsg.append("渠道银行账号信息为空；");
                }
            }
            if (StringUtils.equals("其他", paymentType)) {
                channelBankCode = null;
                channelBankName = null;
                paymentType = "other";
                if (StringUtils.isBlank(paymentCode)) {
                    excelFlag = 0;
                    excelMsg.append("支付账号信息为空；");
                }
            }
            if (StringUtils.equals("两种", paymentType)) {
                paymentType = "all";
                if (StringUtils.isBlank(channelBankCode) || StringUtils.isBlank(paymentCode)) {
                    excelFlag = 0;
                    excelMsg.append("渠道银行账号信息或支付账号信息为空；");
                }
            }
            // 验证没通过时，如果所有列数据都为空，则直接丢弃此行数据
            if (excelFlag == 0) {
                String totalData = channelName + contact + contact + contactInfo + newAmountRate
                        + renewAmountRate + paymentType + channelBankCode + channelBankName + paymentCode;
                if (com.deloitte.common.utils.StringUtils.isBlank(totalData)) {
                    continue;
                }
            } else {
                excelMsg.append("验证成功；");
            }

            // 组装数据并保存
            ChannelInfoExcel channelInfoExcel = new ChannelInfoExcel();
            channelInfoExcel.setChannelName(channelName);
            channelInfoExcel.setContact(contact);
            channelInfoExcel.setContactInfo(contactInfo);
            channelInfoExcel.setNewAmountRate(newAmountRate);
            channelInfoExcel.setRenewAmountRate(renewAmountRate);
            channelInfoExcel.setPaymentType(paymentType);
            channelInfoExcel.setChannelBankCode(channelBankCode);
            channelInfoExcel.setChannelBankName(channelBankName);
            channelInfoExcel.setPaymentCode(paymentCode);
            channelInfoExcel.setCreateBy(UserUtils.getUserId());
            channelInfoExcel.setCreateTime(new Date());
            channelInfoExcel.setValidateFlag(excelFlag);
            channelInfoExcel.setValidateMsg(excelMsg.toString());
            channelInfoExcelMapper.insertSelective(channelInfoExcel);
        }
    }

    @Override
    public void saveChannelExcel() {

        Long userId = UserUtils.getUserId();
        // 同步上传验证通过的excel数据
        channelInfoMapper.saveChannelExcel(userId);

        // 删除上传的excel数据
        channelInfoExcelMapper.deleteByCreateBy(userId);
    }

    @Override
    public List<ChannelInfoExcel> channelExcelList(ChannelInfoExcel param) {
        logger.info("调用channelExcelList方法，参数为:{}", JSON.toJSONString(param));
        // 管理员可以查看所有人员 销售只能查看自己
        boolean adminPermission = this.adminPermission(null);
        if (!adminPermission) {
            param.setCreateBy(UserUtils.getUserId());
        }
        PageHelperUtils.startPage();
        return channelInfoExcelMapper.selectList(param);
    }

    @Override
    public List<ChannelInfo> channelList(ChannelInfo param) {
        logger.info("调用channelList方法，参数为:{}", JSON.toJSONString(param));
        // 管理员可以查看所有人员 销售只能查看自己
        boolean adminPermission = this.adminPermission(null);
        if (!adminPermission) {
            param.setCreateBy(UserUtils.getUserId());
        }
        PageHelperUtils.startPage();
        return channelInfoMapper.selectList(param);
    }

    @Override
    public ChannelInfo queryChannelInfo(Long id) {
        if (id == null) {
            throw new BusinessException("调用queryChannelInfo方法参数id为空！");
        }
        return channelInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ChannelInfo> queryChannelByUserAndName(Long userId, String channelName) {
        if (userId == null) {
            userId = UserUtils.getUserId();
        }
        ChannelInfo param = new ChannelInfo();
        param.setCreateBy(userId);
        param.setChannelName(channelName);
        return channelInfoMapper.selectList(param);
    }

    @Override
    public int updateChannelInfo(ChannelInfo channelInfo) {

        logger.info("调用updateChannelInfo方法，参数为:{}", JSON.toJSONString(channelInfo));
        if (channelInfo.getId() == null) {
            throw new BusinessException("调用updateChannelInfo方法，参数id为空");
        }

        // 管理员角色修改不需要审核
        boolean adminPermission = adminPermission(null);
        Long userId = UserUtils.getUserId();
        if (adminPermission) {
            channelInfo.setUpdater(userId);
            return channelInfoMapper.updateByPrimaryKey(channelInfo);
        }

        // 判断对象的属性是否有改变，有改变需要审核
        ChannelInfo oldChannelInfo = channelInfoMapper.selectByPrimaryKey(channelInfo.getId());
        boolean changeFlag = BeanUtils.fieldEquals(oldChannelInfo, channelInfo, null);
        if (!changeFlag) {
            // 变态变成审核中
            oldChannelInfo.setStatus(CustomerInfoConstants.CHANGE_STATUS_AUDIT);
            oldChannelInfo.setAuditRole(CustomerInfoConstants.ADMIN_ROLE_CODE);
            oldChannelInfo.setUpdater(userId);
            channelInfoMapper.updateByPrimaryKey(oldChannelInfo);

            ChannelInfoEdit channelInfoEdit = new ChannelInfoEdit();
            BeanUtils.copyBeanProp(channelInfoEdit, channelInfo);
            channelInfoEdit.setUpdater(userId);
            channelInfoEdit.setAuditRole(CustomerInfoConstants.ADMIN_ROLE_CODE);
            channelInfoEdit.setChangeType(CustomerInfoConstants.CHANGE_TYPE_EDIT);
            return channelInfoEditMapper.insert(channelInfoEdit);
        }
        return 0;
    }

    @Override
    public String addChannelInfo(ChannelInfo channelInfo) {

        logger.info("调用addChannelInfo方法，参数为:{}", JSON.toJSONString(channelInfo));
        // 新购和续费不能同时为空
        Double newAmountRate = channelInfo.getNewAmountRate();
        Double renewAmountRate = channelInfo.getRenewAmountRate();
        if (newAmountRate == null && renewAmountRate == null) {
            throw new BusinessException("新购和续费不能同时为空!");
        }
        // 支付方式为银行卡时需填写银行卡信息  为其他时需填写支付账号
        if (StringUtils.equals(channelInfo.getPaymentType(), CustomerInfoConstants.PEYMENT_TYPE_BANK)) {
            channelInfo.setPaymentCode(null);
        }
        if (StringUtils.equals(channelInfo.getPaymentType(), CustomerInfoConstants.PEYMENT_TYPE_OTHER)) {
            channelInfo.setChannelBankCode(null);
            channelInfo.setChannelBankName(null);
        }
        channelInfo.setStatus(CustomerInfoConstants.CHANGE_STATUS_UNCHANGED);
        channelInfo.setCreator(UserUtils.getUserId());
        channelInfo.setUpdater(UserUtils.getUserId());
        channelInfo.setDeleteAndDisableDefault();

        channelInfoMapper.insert(channelInfo);
        return CustomerInfoConstants.OPERATION_SUCCESS;
    }

    @Override
    public int deleteChannelInfo(Long[] ids) {

        logger.info("调用addChannelInfo方法，参数为:{}", JSON.toJSONString(ids));
        if (ids == null || ids.length == 0) {
            throw new BusinessException("调用deleteChannelInfo方法，参数ids为空");
        }
        // 管理员角色删除不需要审核
        boolean adminPermission = adminPermission(null);
        Long userId = UserUtils.getUserId();
        if (adminPermission) {
            return channelInfoMapper.deleteChannelInfo(ids, userId);
        }

        ChannelInfo oldChannelInfo = null;
        ChannelInfoEdit customerInfoEdit = null;
        for (Long id : ids) {
            customerInfoEdit = new ChannelInfoEdit();
            oldChannelInfo = channelInfoMapper.selectByPrimaryKey(id);
            oldChannelInfo.setStatus(CustomerInfoConstants.CHANGE_STATUS_AUDIT);
            oldChannelInfo.setAuditRole(CustomerInfoConstants.ADMIN_ROLE_CODE);
            oldChannelInfo.setUpdater(userId);
            channelInfoMapper.updateByPrimaryKey(oldChannelInfo);

            BeanUtils.copyBeanProp(customerInfoEdit, oldChannelInfo);
            customerInfoEdit.setChangeType(CustomerInfoConstants.CHANGE_TYPE_DELETE);
            channelInfoEditMapper.insert(customerInfoEdit);
        }

        return 0;
    }
}
