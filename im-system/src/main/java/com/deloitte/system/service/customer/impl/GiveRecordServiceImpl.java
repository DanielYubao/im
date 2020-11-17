package com.deloitte.system.service.customer.impl;

import com.alibaba.fastjson.JSON;
import com.deloitte.common.utils.BeanUtils;
import com.deloitte.common.utils.StringUtils;
import com.deloitte.framework.exception.BusinessException;
import com.deloitte.framework.utils.PageHelperUtils;
import com.deloitte.system.common.CustomerInfoConstants;
import com.deloitte.system.mapper.customer.*;
import com.deloitte.system.model.customer.*;
import com.deloitte.system.model.customer.GiveRecordExcel;
import com.deloitte.system.model.sys.User;
import com.deloitte.system.service.customer.GiveRecordService;
import com.deloitte.system.utils.FileUtils;
import com.deloitte.system.utils.UserUtils;
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
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: QiaoYubao
 * @Date: 04/05/2020
 * @Description: 赠送记录服务接口实现
 */
@Service
public class GiveRecordServiceImpl extends BaseServiceImpl implements GiveRecordService {

    private static final Logger logger = LoggerFactory.getLogger(GiveRecordServiceImpl.class);

    @Resource
    private GiveRecordMapper giveRecordMapper;

    @Resource
    private GiveRecordExcelMapper giveRecordExcelMapper;

    @Resource
    private GiveRecordEditMapper giveRecordEditMapper;

    @Resource
    private ChannelCustomerInfoMapper channelCustomerInfoMapper;

    @Resource
    private CustomerInfoMapper customerInfoMapper;


    @Override
    public void downloadGiveExcel(HttpServletResponse response) throws Exception {
        FileUtils.downloadFile("赠送记录信息模板.xlsx", null, response);
    }

    @Override
    public void uploadGiveExcel(InputStream inputStream, String fileName) {
        FileUtils.isExcel(fileName);
        List<List<String>> list = FileUtils.readXlsx(inputStream, 2);
        logger.info("删除当前用户:{},之前导入到give_excel表中的数据", UserUtils.getUserId());
        giveRecordExcelMapper.deleteByCreateBy(UserUtils.getUserId());

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

            // 1.受赠人  2.关联客户账号  3.账户信息  4.金额 5.备注
            String doneeName = data.get(0);
            if (StringUtils.isBlank(doneeName)) {
                excelFlag = 0;
                excelMsg.append("受赠人为空；");
            }
            String customerCode = data.get(1);
            Long customerId = null;
            String customerType = null;
            List<CustomerInfo> customerInfos = customerInfoMapper.selectByCustomerCodeAndCreateBy(customerCode, UserUtils.getUserId());
            if (CollectionUtils.isEmpty(customerInfos)) {
                List<ChannelCustomerInfo> channelCustomerInfos = channelCustomerInfoMapper.selectByCustomerCodeAndCreateBy(customerCode, UserUtils.getUserId());
                if (CollectionUtils.isEmpty(channelCustomerInfos)) {
                    excelFlag = 0;
                    excelMsg.append("关联的客户账号不存在；");
                } else {
                    customerId = channelCustomerInfos.get(0).getId();
                    customerType = "channel";
                }
            } else {
                customerId = customerInfos.get(0).getId();
                customerType = "direct";
            }
            String accountInfo = data.get(2);
//            if (StringUtils.isBlank(accountInfo)) {
//                excelFlag = 0;
//                excelMsg.append("账户信息为空；");
//            }
            String amount = data.get(3);
            if (StringUtils.isBlank(amount)) {
                excelFlag = 0;
                excelMsg.append("金额为空；");
            }
            String remark = data.get(4);
            // 验证没通过时，如果所有列数据都为空，则直接丢弃此行数据
            if (excelFlag == 0) {
                String totalData = doneeName + customerCode + accountInfo + amount + remark;
                if (StringUtils.isBlank(totalData)) {
                    continue;
                }
            } else {
                excelMsg.append("验证成功；");
            }
            GiveRecordExcel giveRecordExcel = new GiveRecordExcel();
            giveRecordExcel.setCustomerCode(customerCode);
            giveRecordExcel.setCustomerId(customerId);
            giveRecordExcel.setCustomerType(customerType);
            giveRecordExcel.setDoneeName(doneeName);
            giveRecordExcel.setAccountInfo(accountInfo);
            giveRecordExcel.setAmount(amount);
            giveRecordExcel.setRemark(remark);
            giveRecordExcel.setValidateFlag(excelFlag);
            giveRecordExcel.setValidateMsg(excelMsg.toString());
            giveRecordExcel.setCreateBy(UserUtils.getUserId());
            giveRecordExcel.setCreateTime(new Date());

            giveRecordExcelMapper.insertSelective(giveRecordExcel);
        }
    }

    @Override
    public void saveGiveExcel() {
        Long userId = UserUtils.getUserId();
        // 同步上传验证通过的excel数据
        giveRecordMapper.saveGiveExcel(userId);

        // 删除上传的excel数据
        giveRecordExcelMapper.deleteByCreateBy(userId);
    }

    @Override
    public List<GiveRecordExcel> giveExcelList(GiveRecordExcel param) {
        logger.info("调用giveExcelList方法，参数为:{}", JSON.toJSONString(param));
        // 管理员可以查看所有人员 销售只能查看自己
        boolean adminPermission = this.adminPermission(null);
        if (!adminPermission) {
            param.setCreateBy(UserUtils.getUserId());
        }
        PageHelperUtils.startPage();
        return giveRecordExcelMapper.selectList(param);
    }

    @Override
    public List<GiveRecord> giveList(GiveRecord param) {
        logger.info("调用giveList方法，参数为:{}", JSON.toJSONString(param));
        // 管理员 财务 可以查看所有人员 销售只能查看自己
        boolean adminPermission = this.adminPermission(null);
        boolean financePermission = this.financePermission(null);
        if (!adminPermission && !financePermission) {
            param.setCreateBy(UserUtils.getUserId());
        }
        PageHelperUtils.startPage();
        return giveRecordMapper.selectList(param);
    }

    @Override
    public void exportGiveData(GiveRecord giveRecord, HttpServletRequest request, HttpServletResponse response) {
        logger.info("调用exportPaymentData方法，参数为：{}", JSON.toJSONString(giveRecord));
        String fileName = "赠送记录名单.xlsx";
        XSSFWorkbook wb = new XSSFWorkbook();
        //列表
        XSSFSheet sheet = wb.createSheet();
        XSSFRow titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("受赠人");
        titleRow.createCell(1).setCellValue("关联客户账号");
        titleRow.createCell(2).setCellValue("打款方式");
        titleRow.createCell(3).setCellValue("关联客户的账号信息");
        titleRow.createCell(4).setCellValue("银行开户行");
        titleRow.createCell(5).setCellValue("支付账号");
        titleRow.createCell(6).setCellValue("赠送记录账户信息");
        titleRow.createCell(7).setCellValue("金额");
        titleRow.createCell(8).setCellValue("归属人");
        titleRow.createCell(9).setCellValue("状态");
        titleRow.createCell(10).setCellValue("赠送状态");
        titleRow.createCell(11).setCellValue("赠送审核人");
        titleRow.createCell(12).setCellValue("备注");
        titleRow.createCell(13).setCellValue("创建时间");

        boolean adminPermission = this.adminPermission(null);
        boolean financePermission = this.financePermission(null);
        if (!adminPermission && !financePermission) {
            giveRecord.setCreateBy(UserUtils.getUserId());
        }
        List<Map<String, Object>> list = giveRecordMapper.selectExportData(giveRecord);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 1;
            XSSFRow row = null;
            for (Map<String, Object> map : list) {
                row = sheet.createRow(i);
                row.createCell(0).setCellValue(map.get("donee_name") == null ? "" : map.get("donee_name") + "");
                row.createCell(1).setCellValue(map.get("customer_code") == null ? "" : map.get("customer_code") + "");
                String paymentType = map.get("payment_type") + "";
                if (StringUtils.equals(paymentType, "other")) {
                    paymentType = "其他";
                }
                if (StringUtils.equals(paymentType, "bank")) {
                    paymentType = "银行卡";
                }
                if (StringUtils.equals(paymentType, "all")) {
                    paymentType = "两种";
                }
                row.createCell(2).setCellValue(paymentType);
                row.createCell(3).setCellValue(map.get("customer_bank_code") == null ? "" : map.get("customer_bank_code") + "");
                row.createCell(4).setCellValue(map.get("customer_bank_name") == null ? "" : map.get("customer_bank_name") + "");
                row.createCell(5).setCellValue(map.get("payment_code") == null ? "" : map.get("payment_code") + "");
                row.createCell(6).setCellValue(map.get("account_info") == null ? "" : map.get("account_info") + "");
                row.createCell(7).setCellValue(map.get("amount") == null ? "0" : map.get("amount") + "");
                row.createCell(8).setCellValue(map.get("create_name") == null ? "" : map.get("create_name") + "");
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
                String giveStatus = map.get("give_status") + "";
                switch (giveStatus) {
                    case CustomerInfoConstants.GIVE_STATUS_NO:
                        giveStatus = "未提交";
                        break;
                    case CustomerInfoConstants.GIVE_STATUS_AUDIT:
                        giveStatus = "审核中";
                        break;
                    case CustomerInfoConstants.GIVE_STATUS_REFUSE:
                        giveStatus = "拒绝";
                        break;
                    case CustomerInfoConstants.GIVE_STATUS_YES:
                        giveStatus = "已赠送";
                        break;
                    default:
                        giveStatus = "其他";
                }
                String auditRole = map.get("give_audit_role") + "";
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
                row.createCell(9).setCellValue(status);
                row.createCell(10).setCellValue(giveStatus);
                row.createCell(11).setCellValue(auditRole);
                row.createCell(12).setCellValue(map.get("remark") == null ? "" : map.get("remark") + "");
                row.createCell(13).setCellValue(map.get("create_time") == null ? "" : map.get("create_time") + "");
                i++;
            }
        }

        FileUtils.writeXlsx(request, response, fileName, wb);
    }

    @Override
    public GiveRecord queryGiveRecord(Long id) {
        if (id == null) {
            throw new BusinessException("调用queryGiveRecord方法，参数id为空！");
        }
        return giveRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int updateGiveRecord(GiveRecord giveRecord) {

        logger.info("调用updateGiveRecord方法，参数为:{}", JSON.toJSONString(giveRecord));
        if (giveRecord.getId() == null) {
            throw new BusinessException("调用updateGiveRecord方法，参数id为空");
        }

        // 只有未提交和拒绝的可以今夕编辑 审核中和审核完成的只能删除，销售删除审核完成的需要审核
        giveRecord.setUpdater(UserUtils.getUserId());
        return giveRecordMapper.updateByPrimaryKey(giveRecord);
    }

    @Override
    public String addGiveRecord(GiveRecord giveRecord) {

        logger.info("调用addGiveRecord方法，参数为:{}", JSON.toJSONString(giveRecord));
        giveRecord.setStatus(CustomerInfoConstants.CHANGE_STATUS_UNCHANGED);
        giveRecord.setGiveStatus(CustomerInfoConstants.GIVE_STATUS_NO);
        giveRecord.setCreateBy(UserUtils.getUserId());
        giveRecord.setCreateTime(new Date());
        giveRecord.setUpdateBy(UserUtils.getUserId());
        giveRecord.setUpdateTime(new Date());
        giveRecordMapper.insertSelective(giveRecord);

        return CustomerInfoConstants.OPERATION_SUCCESS;
    }

    @Override
    @Transactional
    public int deleteGiveRecord(Long[] ids) {

        logger.info("调用deleteGiveRecord方法，参数为:{}", JSON.toJSONString(ids));
        if (ids == null || ids.length == 0) {
            throw new BusinessException("调用deleteGiveRecord方法，参数ids为空");
        }
        // 管理员角色删除不需要审核
        boolean adminPermission = adminPermission(null);
        if (adminPermission) {
            return giveRecordMapper.deleteGiveRecord(ids);
        }

        GiveRecord oldGiveRecord = null;
        GiveRecordEdit giveRecordEdit = null;
        for (Long id : ids) {
            giveRecordEdit = new GiveRecordEdit();
            oldGiveRecord = giveRecordMapper.selectByPrimaryKey(id);
            if(StringUtils.equals(oldGiveRecord.getGiveStatus(), CustomerInfoConstants.GIVE_STATUS_YES)){
                oldGiveRecord.setStatus(CustomerInfoConstants.CHANGE_STATUS_AUDIT);
                oldGiveRecord.setAuditRole(CustomerInfoConstants.ADMIN_ROLE_CODE);
                oldGiveRecord.setUpdater(UserUtils.getUserId());
                giveRecordMapper.updateByPrimaryKey(oldGiveRecord);

                BeanUtils.copyBeanProp(giveRecordEdit, oldGiveRecord);
                giveRecordEdit.setUpdateTime(new Date());
                giveRecordEdit.setChangeType(CustomerInfoConstants.CHANGE_TYPE_DELETE);
                giveRecordEditMapper.insert(giveRecordEdit);
            }else{
                giveRecordMapper.deleteByPrimaryKey(id);
            }
        }
        return 0;
    }

    @Override
    public Map giveInfoData(GiveRecord param) {
        logger.info("调用giveInfoData方法，参数为:{}", JSON.toJSONString(param));
        boolean adminPermission = this.adminPermission(null);
        if (!adminPermission) {
            param.setCreateBy(UserUtils.getUserId());
        }
        param.setAdminFlag(adminPermission);
        return giveRecordMapper.giveInfoData(param);
    }

    @Override
    public List<Map> queryCustomerInfo(Long userId) {
        if (userId == null ){
            userId = UserUtils.getUserId();
        }
        return giveRecordMapper.queryCustomerInfo(userId);
    }
}
