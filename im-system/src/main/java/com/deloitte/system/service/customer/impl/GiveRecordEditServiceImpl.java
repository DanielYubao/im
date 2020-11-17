package com.deloitte.system.service.customer.impl;

import com.alibaba.fastjson.JSON;
import com.deloitte.common.utils.StringUtils;
import com.deloitte.framework.exception.BusinessException;
import com.deloitte.framework.utils.PageHelperUtils;
import com.deloitte.system.common.CustomerInfoConstants;
import com.deloitte.system.mapper.customer.GiveRecordEditMapper;
import com.deloitte.system.mapper.customer.GiveRecordMapper;
import com.deloitte.system.model.customer.GiveRecord;
import com.deloitte.system.model.customer.GiveRecordEdit;
import com.deloitte.system.service.customer.GiveRecordEditService;
import com.deloitte.system.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 04/05/2020
 * @Description:
 */
@Service
public class GiveRecordEditServiceImpl extends BaseServiceImpl implements GiveRecordEditService {

    @Resource
    private GiveRecordEditMapper giveRecordEditMapper;

    @Resource
    private GiveRecordMapper giveRecordMapper;

    private static final Logger logger = LoggerFactory.getLogger(GiveRecordEditServiceImpl.class);

    @Override
    public List<GiveRecordEdit> giveAuditList(GiveRecordEdit param) {
        logger.info("调用giveAuditList方法，参数为:{}", JSON.toJSONString(param));
        PageHelperUtils.startPage();
        param.setAuditRole(getRole(UserUtils.getUserId()));
        return giveRecordEditMapper.selectList(param);
    }

    @Override
    public GiveRecordEdit queryGiveEdit(Long id) {
        if (id == null){
            throw new BusinessException("调用queryGiveEdit方法，参数id为空！");
        }

        return giveRecordEditMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void auditGiveEdit(String auditType, Long[] ids) {

        logger.info("调用auditGiveEdit方法，参数auditType为:{}，参数ids为:{}", JSON.toJSONString(auditType), JSON.toJSONString(ids));
        if(StringUtils.isBlank(auditType)){
            throw new BusinessException("调用auditGiveEdit方法参数auditType为空！");
        }
        if (ids == null || ids.length == 0){
            throw new BusinessException("调用auditGiveEdit方法参数ids为空！");
        }
        Long userId = UserUtils.getUserId();
        String roleCode = getRole(userId);
        if (StringUtils.equals(auditType, CustomerInfoConstants.AUDIT_TYPE_APPROVE)){
            for (Long id : ids){
                GiveRecord giveRecord = giveRecordMapper.selectByPrimaryKey(id);
                GiveRecordEdit giveRecordEdit = giveRecordEditMapper.selectByPrimaryKey(id);
                giveRecord.setUpdater(userId);
                giveRecordEdit.setUpdater(userId);

                // 超级管理员审核
                if (StringUtils.equals(roleCode, CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE)) {
                    // 修改类型的数据
                    if (StringUtils.equals(giveRecordEdit.getChangeType(), CustomerInfoConstants.CHANGE_TYPE_EDIT)) {
                        org.springframework.beans.BeanUtils.copyProperties(giveRecordEdit, giveRecord);
                        giveRecord.setStatus(CustomerInfoConstants.CHANGE_STATUS_COMPLETED);
                    }
                    // 删除类型的数据
                    if (StringUtils.equals(giveRecordEdit.getChangeType(), CustomerInfoConstants.CHANGE_TYPE_DELETE)) {
                        giveRecord.setDeleteFlag(1);
                        giveRecord.setStatus(CustomerInfoConstants.CHANGE_STATUS_COMPLETED);
                    }
                    giveRecordEditMapper.deleteByPrimaryKey(id);
                } else {
                    giveRecord.setAuditRole(CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE);
                    giveRecordEdit.setAuditRole(CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE);
                    giveRecordEditMapper.updateByPrimaryKey(giveRecordEdit);
                }
                // 更新状态
                giveRecordMapper.updateByPrimaryKey(giveRecord);
            }
        }else if(StringUtils.equals(auditType, CustomerInfoConstants.AUDIT_TYPE_REFUSE)){
            // 审核不通过 更改源数据状态为拒绝 删除审核中的数据
            giveRecordMapper.updateStatus(CustomerInfoConstants.CHANGE_STATUS_REFUSE, ids);
            giveRecordEditMapper.deleteGiveRecordEdit(ids);
        }
    }
}
