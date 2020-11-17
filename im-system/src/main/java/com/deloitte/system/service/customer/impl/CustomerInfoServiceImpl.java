package com.deloitte.system.service.customer.impl;

import com.alibaba.fastjson.JSON;
import com.deloitte.common.utils.BeanUtils;
import com.deloitte.common.utils.StringUtils;
import com.deloitte.framework.exception.BusinessException;
import com.deloitte.framework.utils.PageHelperUtils;
import com.deloitte.system.common.CustomerInfoConstants;
import com.deloitte.system.common.SystemModualConstants;
import com.deloitte.system.mapper.customer.CustomerInfoEditMapper;
import com.deloitte.system.mapper.customer.CustomerUploadLogMapper;
import com.deloitte.system.mapper.customer.CustomerInfoExcelMapper;
import com.deloitte.system.mapper.customer.CustomerInfoMapper;
import com.deloitte.system.model.customer.CustomerInfo;
import com.deloitte.system.model.customer.CustomerInfoEdit;
import com.deloitte.system.model.customer.CustomerInfoExcel;
import com.deloitte.system.model.customer.CustomerUploadLog;
import com.deloitte.system.model.sys.Role;
import com.deloitte.system.service.RoleService;
import com.deloitte.system.service.customer.CustomerInfoService;
import com.deloitte.system.utils.FileUtils;
import com.deloitte.system.utils.UserUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: QiaoYubao
 * @Date: 19/03/2020 13:14
 * @Description: 客户信息服务接口实现
 */
@Service
public class CustomerInfoServiceImpl extends BaseServiceImpl implements CustomerInfoService {


    private static final Logger logger = LoggerFactory.getLogger(CustomerInfoServiceImpl.class);

    @Resource
    private CustomerInfoMapper customerInfoMapper;

    @Resource
    private CustomerInfoExcelMapper customerInfoExcelMapper;

    @Resource
    private CustomerUploadLogMapper customerUploadLogMapper;

    @Resource
    private CustomerInfoEditMapper customerInfoEditMapper;

    @Override
    public void downloadCustomerExcel(HttpServletResponse response) throws Exception {
        FileUtils.downloadFile("直接客户信息模板.xlsx", null, response);
    }

    @Override
    public void uploadCustomerExcel(InputStream inputStream, String fileName) {

        FileUtils.isExcel(fileName);
        List<List<String>> list = FileUtils.readXlsx(inputStream, 2);
        logger.info("删除当前用户:{},之前导入到customer_info_excel表中的数据", UserUtils.getUserId());
        customerInfoExcelMapper.deleteByCreateBy(UserUtils.getUserId());

        logger.info("读取excel数据为：{}", com.alibaba.fastjson.JSON.toJSONString(list));
        // 验证并保存数据
        validateAndSave(list);

    }

    @Override
    public void saveCustomerExcel() {

        Long userId = UserUtils.getUserId();
        // 同步上传验证通过的excel数据
        customerInfoMapper.saveCustomerExcel(userId);

        // 添加上传日志记录
//        CustomerUploadLog customerUploadLog = new CustomerUploadLog();
//        customerUploadLog.setUserId(userId);
//        customerUploadLogMapper.insert(customerUploadLog);

        // 删除上传的excel数据
        customerInfoExcelMapper.deleteByCreateBy(userId);
    }

    @Override
    public List<CustomerInfoExcel> customerExcelList(CustomerInfoExcel param) {
        logger.info("调用customerExcelList方法，参数为:{}", JSON.toJSONString(param));
        // 管理员可以查看所有人员 销售只能查看自己
        boolean adminPermission = this.adminPermission(null);
        if (!adminPermission) {
            param.setCreateBy(UserUtils.getUserId());
        }
        PageHelperUtils.startPage();
        return customerInfoExcelMapper.selectList(param);
    }

    @Override
    public List<CustomerInfo> customerList(CustomerInfo param) {
        logger.info("调用customerList方法，参数为:{}", JSON.toJSONString(param));
        // 管理员可以查看所有人员 销售只能查看自己
        boolean adminPermission = this.adminPermission(null);
        if (!adminPermission) {
            param.setCreateBy(UserUtils.getUserId());
        }
        PageHelperUtils.startPage();
        return customerInfoMapper.selectList(param);
    }

    @Override
    public CustomerInfo queryCustomerInfo(Long id) {
        if (id == null) {
            throw new BusinessException("调用queryCustomerInfo方法参数id为空！");
        }
        return customerInfoMapper.selectByPrimaryKey(id);
    }


    @Override
    public boolean uploadPermission(Long id) {
        if (id == null) {
            id = UserUtils.getUserId();
        }
        boolean adminPermission = this.adminPermission(id);
        // 管理员可以多次上传
        if (adminPermission) {
            return true;
        }
        // 销售只能上传一次
        int count = customerUploadLogMapper.countByUserId(id);
        if (count < 1) {
            return true;
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCustomerInfo(CustomerInfo customerInfo) {

        logger.info("调用updateCustomerInfo方法，参数为:{}", JSON.toJSONString(customerInfo));
        if (customerInfo.getId() == null) {
            throw new BusinessException("调用updateCustomerInfo方法，参数id为空");
        }

        // 管理员角色修改不需要审核
        boolean adminPermission = adminPermission(null);
        Long userId = UserUtils.getUserId();
        if (adminPermission) {
            customerInfo.setUpdater(userId);
            return customerInfoMapper.updateByPrimaryKey(customerInfo);
        }

        // 判断对象的属性是否有改变，有改变需要审核
        CustomerInfo oldCustomerInfo = customerInfoMapper.selectByPrimaryKey(customerInfo.getId());
        boolean changeFlag = BeanUtils.fieldEquals(oldCustomerInfo, customerInfo, null);
        if (!changeFlag) {
            // 变态变成审核中
            oldCustomerInfo.setStatus(CustomerInfoConstants.CHANGE_STATUS_AUDIT);
            oldCustomerInfo.setAuditRole(CustomerInfoConstants.ADMIN_ROLE_CODE);
            oldCustomerInfo.setUpdater(userId);
            customerInfoMapper.updateByPrimaryKey(oldCustomerInfo);

            CustomerInfoEdit customerInfoEdit = new CustomerInfoEdit();
            BeanUtils.copyBeanProp(customerInfoEdit, customerInfo);
            customerInfoEdit.setUpdater(userId);
            customerInfoEdit.setAuditRole(CustomerInfoConstants.ADMIN_ROLE_CODE);
            customerInfoEdit.setChangeType(CustomerInfoConstants.CHANGE_TYPE_EDIT);
            return customerInfoEditMapper.insert(customerInfoEdit);
        }
        return 0;
    }

    @Override
    public String addCustomerInfo(CustomerInfo customerInfo) {

        logger.info("调用addCustomerInfo方法，参数为:{}", JSON.toJSONString(customerInfo));
        // 新购和续费不能同时为空
        Double newAmountRate = customerInfo.getNewAmountRate();
        Double renewAmountRate = customerInfo.getRenewAmountRate();
        if (newAmountRate == null && renewAmountRate == null) {
            throw new BusinessException("新购和续费不能同时为空!");
        }
        // 支付方式为银行卡时需填写银行卡信息  为其他时需填写支付账号
        if (org.apache.commons.lang3.StringUtils.equals(customerInfo.getPaymentType(), CustomerInfoConstants.PEYMENT_TYPE_BANK)) {
            customerInfo.setPaymentCode(null);
        }
        if (org.apache.commons.lang3.StringUtils.equals(customerInfo.getPaymentType(), CustomerInfoConstants.PEYMENT_TYPE_OTHER)) {
            customerInfo.setCustomerBankCode(null);
            customerInfo.setCustomerBankName(null);
        }
        customerInfo.setStatus(CustomerInfoConstants.CHANGE_STATUS_UNCHANGED);
        customerInfo.setCreator(UserUtils.getUserId());
        customerInfo.setUpdater(UserUtils.getUserId());
        customerInfo.setDeleteAndDisableDefault();

        customerInfoMapper.insert(customerInfo);
        return CustomerInfoConstants.OPERATION_SUCCESS;
    }

    @Override
    @Transactional
    public int deleteCustomerInfo(Long[] ids) {

        logger.info("调用deleteCustomerInfo方法，参数为:{}", JSON.toJSONString(ids));
        if (ids == null || ids.length == 0) {
            throw new BusinessException("调用deleteCustomerInfo方法，参数ids为空");
        }
        // 管理员角色删除不需要审核
        boolean adminPermission = adminPermission(null);
        Long userId = UserUtils.getUserId();
        if (adminPermission) {
            return customerInfoMapper.deleteCustomerInfo(ids,userId);
        }

        CustomerInfo oldCustomerInfo = null;
        CustomerInfoEdit customerInfoEdit = null;
        for (Long id : ids) {
            customerInfoEdit = new CustomerInfoEdit();
            oldCustomerInfo = customerInfoMapper.selectByPrimaryKey(id);
            oldCustomerInfo.setStatus(CustomerInfoConstants.CHANGE_STATUS_AUDIT);
            oldCustomerInfo.setAuditRole(CustomerInfoConstants.ADMIN_ROLE_CODE);
            oldCustomerInfo.setUpdater(userId);
            customerInfoMapper.updateByPrimaryKey(oldCustomerInfo);

            BeanUtils.copyBeanProp(customerInfoEdit, oldCustomerInfo);
            customerInfoEdit.setChangeType(CustomerInfoConstants.CHANGE_TYPE_DELETE);
            customerInfoEditMapper.insert(customerInfoEdit);
        }

        return 0;
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
            //1.客户账号	2.客户名称	3.联系人	4.联系方式	5.新购佣金比例	6.续费佣金比例
            //7.支付方式  8.客户银行账号  9.客户银行开户行  10.支付账号
            String customerCode = data.get(0);
            if (StringUtils.isBlank(customerCode)) {
                excelFlag = 0;
                excelMsg.append("客户账号为空；");
            }
            String customerName = data.get(1);
            if (StringUtils.isBlank(customerName)) {
                excelFlag = 0;
                excelMsg.append("客户名称为空；");
            }
            String contact = data.get(2);
            if (StringUtils.isBlank(contact)) {
                excelFlag = 0;
                excelMsg.append("联系人为空；");
            }
            String contactInfo = data.get(3);
            if (StringUtils.isBlank(contactInfo)) {
                excelFlag = 0;
                excelMsg.append("联系方式为空；");
            }
            // 新购和续费不能同时为空，且配对出现
            String newAmountRate = data.get(4);
            String renewAmountRate = data.get(5);
            if (StringUtils.isBlank(newAmountRate) && StringUtils.isBlank(renewAmountRate)) {
                excelFlag = 0;
                excelMsg.append("新购和续费信息不能同时为空；");
            }

            String paymentType = data.get(6);
            String customerBankCode = data.get(7);
            String customerBankName = data.get(8);
            String paymentCode = data.get(9);
            if (StringUtils.isBlank(paymentType)){
                excelFlag = 0;
                excelMsg.append("支付方式为空；");
            }
            if (org.apache.commons.lang3.StringUtils.equals("银行卡", paymentType)) {
                paymentCode = null;
                paymentType = "bank";
                if (org.apache.commons.lang3.StringUtils.isBlank(customerBankCode)) {
                    excelFlag = 0;
                    excelMsg.append("客户银行账号信息为空；");
                }
            }
            if (org.apache.commons.lang3.StringUtils.equals("其他", paymentType)) {
                customerBankCode = null;
                customerBankName = null;
                paymentType = "other";
                if (org.apache.commons.lang3.StringUtils.isBlank(paymentCode)) {
                    excelFlag = 0;
                    excelMsg.append("支付账号信息为空；");
                }
            }
            if (org.apache.commons.lang3.StringUtils.equals("两种", paymentType)) {
                paymentType = "all";
                if (org.apache.commons.lang3.StringUtils.isBlank(customerBankCode) || org.apache.commons.lang3.StringUtils.isBlank(paymentCode)) {
                    excelFlag = 0;
                    excelMsg.append("客户银行账号信息或支付账号信息为空；");
                }
            }
            // 验证没通过时，如果所有列数据都为空，则直接丢弃此行数据
            if (excelFlag == 0) {
                String totalData = customerCode + customerName + contact + contactInfo + newAmountRate
                        + renewAmountRate + paymentType + customerBankCode + customerBankName + paymentCode;
                if (StringUtils.isBlank(totalData)) {
                    continue;
                }
            } else {
                excelMsg.append("验证成功；");
            }
            // 组装数据并保存
            CustomerInfoExcel customer = new CustomerInfoExcel();
            customer.setCustomerCode(customerCode);
            customer.setCustomerName(customerName);
            customer.setContact(contact);
            customer.setContactInfo(contactInfo);
            customer.setNewAmountRate(newAmountRate);
            customer.setRenewAmountRate(renewAmountRate);
            customer.setPaymentType(paymentType);
            customer.setCustomerBankCode(customerBankCode);
            customer.setCustomerBankName(customerBankName);
            customer.setPaymentCode(paymentCode);
            customer.setValidateFlag(excelFlag);
            customer.setValidateMsg(excelMsg.toString());
            customer.setCreateBy(UserUtils.getUserId());
            customer.setCreateTime(new Date());
            customerInfoExcelMapper.insertSelective(customer);
        }
    }

    /**
     * 判断是否是百分比，保留四位小数
     *
     * @param s
     * @return
     */
    public static boolean isPercent(String s) {
        Pattern pattern = Pattern.compile("^\\d\\.([1-9]{1,4}|[0-9][1-9])$|^[1-9]\\d{0,1}(\\.\\d{1,4}){0,1}$|^100(\\.0{1,4}){0,1}$");
        Matcher match = pattern.matcher(s);
        boolean result = match.matches();
        return result;
    }

    /**
     * 判断是否为合法 Double类型
     *
     * @param s
     * @return
     */
    public static boolean isDouble(String s) {
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,})?$");
        Matcher match = pattern.matcher(s);
        boolean result = match.matches();
        return result;
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

        return customerInfoMapper.modifyCreateBy(customerIds, targetCreateBy);
    }

    @Override
    public Map<String, Object> customerInfoData(CustomerInfo param) {
        logger.info("调用customerInfoData方法，参数为:{}", JSON.toJSONString(param));
        boolean adminPermission = this.adminPermission(null);
        if (!adminPermission) {
            param.setCreateBy(UserUtils.getUserId());
        }
        param.setAdminFlag(adminPermission);

        return customerInfoMapper.customerInfoData(param);
    }

}
