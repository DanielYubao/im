package com.deloitte.system.service.customer.impl;

import com.alibaba.fastjson.JSON;
import com.deloitte.common.utils.BeanUtils;
import com.deloitte.common.utils.StringUtils;
import com.deloitte.framework.exception.BusinessException;
import com.deloitte.framework.utils.PageHelperUtils;
import com.deloitte.system.common.CustomerInfoConstants;
import com.deloitte.system.mapper.customer.PaymentRecordEditMapper;
import com.deloitte.system.mapper.customer.PaymentRecordMapper;
import com.deloitte.system.model.customer.*;
import com.deloitte.system.service.customer.PaymentRecordService;
import com.deloitte.system.utils.FileUtils;
import com.deloitte.system.utils.UserUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: QiaoYubao
 * @Date: 21/04/2020
 * @Description: 打款记录服务接口实现
 */
@Service
public class PaymentRecordServiceImpl extends BaseServiceImpl implements PaymentRecordService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentRecordServiceImpl.class);

    @Resource
    private PaymentRecordMapper paymentRecordMapper;

    @Resource
    private PaymentRecordEditMapper paymentRecordEditMapper;

    @Override
    public String addPaymentRecord(PaymentRecord paymentInfo) {

        logger.info("调用addPaymentRecord方法，参数为{}",JSON.toJSONString(paymentInfo));
        paymentInfo.setStatus(CustomerInfoConstants.CHANGE_STATUS_UNCHANGED);
        paymentInfo.setPaymentStatus(CustomerInfoConstants.PAYMENT_STATUS_NO);
        paymentInfo.setCreateBy(UserUtils.getUserId());
        paymentInfo.setCreateTime(new Date());
        paymentInfo.setUpdateBy(UserUtils.getUserId());
        paymentInfo.setUpdateTime(new Date());
        paymentRecordMapper.insertSelective(paymentInfo);

        return CustomerInfoConstants.OPERATION_SUCCESS;
    }

    @Override
    public List<PaymentRecord> paymentList(PaymentRecord param) {
        logger.info("调用customerList方法，参数为:{}", JSON.toJSONString(param));
        // 管理员 财务 可以查看所有人员 销售只能查看自己
        boolean adminPermission = this.adminPermission(null);
        boolean financePermission = this.financePermission(null);
        param.setFinanceFlag(financePermission);
        if (!adminPermission && !financePermission) {
            param.setCreateBy(UserUtils.getUserId());
        }
        PageHelperUtils.startPage();
        return paymentRecordMapper.selectList(param);
    }

    @Override
    public PaymentRecord queryPaymentRecord(Long id) {
        if (id == null) {
            throw new BusinessException("调用queryPaymentRecord方法，参数id为空！");
        }
        return paymentRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int updatePaymentRecord(PaymentRecord paymentInfo) {

        logger.info("调用updatePaymentRecord方法，参数为{}",JSON.toJSONString(paymentInfo));
        if (paymentInfo.getId() == null) {
            throw new BusinessException("调用updatePaymentRecord方法，参数id为空");
        }

        // 管理员角色修改不需要审核
//        boolean adminPermission = adminPermission(null);
        Long userId = UserUtils.getUserId();
//        if (adminPermission) {
        paymentInfo.setUpdater(userId);
        return paymentRecordMapper.updateByPrimaryKey(paymentInfo);
//        }

        // 判断对象的属性是否有改变，有改变需要审核
//        PaymentRecord oldPaymentRecord = paymentRecordMapper.selectByPrimaryKey(paymentInfo.getId());
//        boolean changeFlag = BeanUtils.fieldEquals(oldPaymentRecord, paymentInfo, null);
//        if (!changeFlag) {
//            // 变态变成审核中
//            oldPaymentRecord.setStatus(CustomerInfoConstants.CHANGE_STATUS_AUDIT);
//            oldPaymentRecord.setAuditRole(CustomerInfoConstants.ADMIN_ROLE_CODE);
//            oldPaymentRecord.setUpdater(userId);
//            paymentRecordMapper.updateByPrimaryKey(oldPaymentRecord);
//
//            PaymentRecordEdit paymentRecordEdit = new PaymentRecordEdit();
//            BeanUtils.copyBeanProp(paymentRecordEdit, paymentInfo);
//            paymentRecordEdit.setUpdater(userId);
//            paymentRecordEdit.setAuditRole(CustomerInfoConstants.ADMIN_ROLE_CODE);
//            paymentRecordEdit.setChangeType(CustomerInfoConstants.CHANGE_TYPE_EDIT);
//            return paymentRecordEditMapper.insert(paymentRecordEdit);
//        }
//        return 0;
    }

    @Override
    @Transactional
    public int deletePaymentRecord(Long[] ids) {

        logger.info("调用deletePaymentRecord方法，参数为{}",JSON.toJSONString(ids));
        if (ids == null || ids.length == 0) {
            throw new BusinessException("调用deletePaymentRecord方法，参数ids为空");
        }
        // 管理员角色删除不需要审核
        boolean adminPermission = adminPermission(null);
        Long userId = UserUtils.getUserId();
        if (adminPermission) {
            return paymentRecordMapper.deletePaymentRecord(ids, userId);
        }

        PaymentRecord oldPaymentRecord = null;
        PaymentRecordEdit paymentRecordEdit = null;
        // 销售对审核完成的数据进行删除需要审核
        for (Long id : ids) {
            paymentRecordEdit = new PaymentRecordEdit();
            oldPaymentRecord = paymentRecordMapper.selectByPrimaryKey(id);
            if (StringUtils.equals(oldPaymentRecord.getPaymentStatus(), CustomerInfoConstants.PAYMENT_STATUS_YES)) {
                oldPaymentRecord.setStatus(CustomerInfoConstants.CHANGE_STATUS_AUDIT);
                oldPaymentRecord.setAuditRole(CustomerInfoConstants.ADMIN_ROLE_CODE);
                oldPaymentRecord.setUpdater(userId);
                paymentRecordMapper.updateByPrimaryKey(oldPaymentRecord);

                BeanUtils.copyBeanProp(paymentRecordEdit, oldPaymentRecord);
                paymentRecordEdit.setChangeType(CustomerInfoConstants.CHANGE_TYPE_DELETE);
                paymentRecordEditMapper.insert(paymentRecordEdit);
            }else {
                paymentRecordMapper.deleteByPrimaryKey(id);
            }
        }
        return 0;
    }

    @Override
    public List<PaymentRecordEdit> paymentAuditList(PaymentRecordEdit param) {
        logger.info("调用paymentAuditList方法，参数为:{}", JSON.toJSONString(param));
        param.setAuditRole(getRole(UserUtils.getUserId()));
        PageHelperUtils.startPage();
        return paymentRecordEditMapper.selectList(param);
    }

    @Override
    public PaymentRecordEdit queryPaymentEdit(Long id) {
        if (id == null) {
            throw new BusinessException("调用queryPaymentEdit方法，参数id为空！");
        }

        return paymentRecordEditMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void auditPaymentEdit(String auditType, Long[] ids) {

        logger.info("调用auditPaymentEdit方法，参数auditType为:{}，参数ids为：{}", JSON.toJSONString(auditType), JSON.toJSONString(ids));
        if (StringUtils.isBlank(auditType)) {
            throw new BusinessException("调用auditPaymentEdit方法参数auditType为空！");
        }
        if (ids == null || ids.length == 0) {
            throw new BusinessException("调用auditPaymentEdit方法参数ids为空！");
        }

        Long userId = UserUtils.getUserId();
        String roleCode = getRole(userId);
        if (StringUtils.equals(auditType, CustomerInfoConstants.AUDIT_TYPE_APPROVE)) {
            for (Long id : ids) {
                PaymentRecord paymentRecord = paymentRecordMapper.selectByPrimaryKey(id);
                PaymentRecordEdit paymentRecordEdit = paymentRecordEditMapper.selectByPrimaryKey(id);
                paymentRecord.setUpdater(userId);
                paymentRecordEdit.setUpdater(userId);

                // 超级管理员审核
                if (StringUtils.equals(roleCode, CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE)) {
                    // 修改类型的数据
                    if (StringUtils.equals(paymentRecordEdit.getChangeType(), CustomerInfoConstants.CHANGE_TYPE_EDIT)) {
                        org.springframework.beans.BeanUtils.copyProperties(paymentRecordEdit, paymentRecord);
                        paymentRecord.setStatus(CustomerInfoConstants.CHANGE_STATUS_COMPLETED);
                    }
                    // 删除类型的数据
                    if (StringUtils.equals(paymentRecordEdit.getChangeType(), CustomerInfoConstants.CHANGE_TYPE_DELETE)) {
                        paymentRecord.setDeleteFlag(1);
                        paymentRecord.setStatus(CustomerInfoConstants.CHANGE_STATUS_COMPLETED);
                    }
                    paymentRecordEditMapper.deleteByPrimaryKey(id);
                } else {
                    paymentRecord.setAuditRole(CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE);
                    paymentRecordEdit.setAuditRole(CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE);
                    paymentRecordEditMapper.updateByPrimaryKey(paymentRecordEdit);
                }
                // 更新状态
                paymentRecordMapper.updateByPrimaryKey(paymentRecord);

            }
        } else if (StringUtils.equals(auditType, CustomerInfoConstants.AUDIT_TYPE_REFUSE)) {
            // 审核不通过 更改源数据状态为拒绝  删除审核中的数据
            paymentRecordMapper.updateStatus(CustomerInfoConstants.CHANGE_STATUS_REFUSE, ids);
            paymentRecordEditMapper.deletePaymentRecordEdit(ids);
        }
    }

    @Override
    public void exportPaymentData(PaymentRecord paymentRecord, HttpServletRequest request, HttpServletResponse response) {

        logger.info("调用exportPaymentData方法，参数为：{}", JSON.toJSONString(paymentRecord));
        String fileName = "打款记录名单.xlsx";
        XSSFWorkbook wb = new XSSFWorkbook();
        //列表
        XSSFSheet sheet = wb.createSheet();
        XSSFRow titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("客户账号");
        titleRow.createCell(1).setCellValue("打款方式");
        titleRow.createCell(2).setCellValue("客户银行账号");
        titleRow.createCell(3).setCellValue("银行开户行");
        titleRow.createCell(4).setCellValue("支付账号");
        titleRow.createCell(5).setCellValue("新购金额");
        titleRow.createCell(6).setCellValue("新购打款金额");
        titleRow.createCell(7).setCellValue("续费金额");
        titleRow.createCell(8).setCellValue("续费打款金额");
        titleRow.createCell(9).setCellValue("备注说明");
        titleRow.createCell(10).setCellValue("归属人");
        titleRow.createCell(11).setCellValue("状态");
        titleRow.createCell(12).setCellValue("打款状态");
        titleRow.createCell(13).setCellValue("创建时间");
        titleRow.createCell(14).setCellValue("打款审核人");

        boolean adminPermission = this.adminPermission(null);
        boolean financePermission = this.financePermission(null);
        if (!adminPermission && !financePermission) {
            paymentRecord.setCreateBy(UserUtils.getUserId());
        }
        List<Map<String, Object>> list = paymentRecordMapper.selectExportData(paymentRecord);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 1;
            XSSFRow row = null;
            for (Map<String, Object> map : list) {
                row = sheet.createRow(i);
                row.createCell(0).setCellValue(map.get("customer_code") == null ? "" : map.get("customer_code") + "");
                String paymentType = map.get("payment_type") + "";
                if (StringUtils.equals(paymentType, "other")) {
                    paymentType = "其他";
                }
                if (StringUtils.equals(paymentType, "bank")) {
                    paymentType = "银行卡";
                }
                row.createCell(1).setCellValue(paymentType);
                row.createCell(2).setCellValue(map.get("customer_bank_code") == null ? "" : map.get("customer_bank_code") + "");
                row.createCell(3).setCellValue(map.get("customer_bank_name") == null ? "" : map.get("customer_bank_name") + "");
                row.createCell(4).setCellValue(map.get("payment_code") == null ? "" : map.get("payment_code") + "");
                row.createCell(5).setCellValue(map.get("new_amount") == null ? "0" : map.get("new_amount") + "");
                row.createCell(6).setCellValue(map.get("new_payment_amount") == null ? "0" : map.get("new_payment_amount") + "");
                row.createCell(7).setCellValue(map.get("renew_amount") == null ? "0" : map.get("renew_amount") + "");
                row.createCell(8).setCellValue(map.get("renew_payment_amount") == null ? "0" : map.get("renew_payment_amount") + "");
                row.createCell(9).setCellValue(map.get("remark") == null ? "" : map.get("remark") + "");
                row.createCell(10).setCellValue(map.get("create_name") == null ? "" : map.get("create_name") + "");
                String status = map.get("status") + "";
                switch (status) {
                    case CustomerInfoConstants.CHANGE_STATUS_UNCHANGED:
                        status = "未改变";
                        break;
                    case CustomerInfoConstants.CHANGE_STATUS_AUDIT:
                        status = "审核中";
                        break;
                    case CustomerInfoConstants.CHANGE_STATUS_REFUSE:
                        status = "拒绝";
                        break;
                    case CustomerInfoConstants.CHANGE_STATUS_COMPLETED:
                        status = "审核完成";
                        break;
                    default:
                        status = "其他";
                }
                String paymentStatus = map.get("payment_status") + "";
                switch (paymentStatus) {
                    case CustomerInfoConstants.PAYMENT_STATUS_NO:
                        paymentStatus = "未提交";
                        break;
                    case CustomerInfoConstants.PAYMENT_STATUS_AUDIT:
                        paymentStatus = "审核中";
                        break;
                    case CustomerInfoConstants.PAYMENT_STATUS_REFUSE:
                        paymentStatus = "拒绝";
                        break;
                    case CustomerInfoConstants.PAYMENT_STATUS_YES:
                        paymentStatus = "已打款";
                        break;
                    default:
                        paymentStatus = "其他";
                }
                String auditRole = map.get("payment_audit_role") + "";
                if (StringUtils.equals(auditRole, CustomerInfoConstants.FINANCE_ROLE_CODE)) {
                    auditRole = "财务人员";
                }
                else if (StringUtils.equals(auditRole, CustomerInfoConstants.ADMIN_ROLE_CODE)) {
                    auditRole = "管理员";
                }
                else if (StringUtils.equals(auditRole, CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE)) {
                    auditRole = "超级管理员";
                }else {
                    auditRole = "";
                }
                row.createCell(11).setCellValue(status);
                row.createCell(12).setCellValue(paymentStatus);
                row.createCell(13).setCellValue(map.get("create_time") == null ? "" : map.get("create_time") + "");
                row.createCell(14).setCellValue(auditRole);
                i++;
            }
        }

        FileUtils.writeXlsx(request, response, fileName, wb);
    }

    @Override
    public Map<String, Object> paymentInfoData(PaymentRecord param) {
        logger.info("调用paymentInfoData方法，参数为:{}", JSON.toJSONString(param));
        boolean adminPermission = this.adminPermission(null);
        if (!adminPermission) {
            param.setCreateBy(UserUtils.getUserId());
        }
        param.setAdminFlag(adminPermission);

        return paymentRecordMapper.paymentInfoData(param);
    }
}
