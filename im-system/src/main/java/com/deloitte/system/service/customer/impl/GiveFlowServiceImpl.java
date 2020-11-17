package com.deloitte.system.service.customer.impl;

import com.alibaba.fastjson.JSON;
import com.deloitte.common.utils.StringUtils;
import com.deloitte.framework.exception.BusinessException;
import com.deloitte.framework.utils.PageHelperUtils;
import com.deloitte.system.common.CustomerInfoConstants;
import com.deloitte.system.mapper.customer.GiveRecordMapper;
import com.deloitte.system.model.customer.GiveRecord;
import com.deloitte.system.service.customer.GiveFlowService;
import com.deloitte.system.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 03/05/2020
 * @Description: 赠送流程服务接口实现
 */
@Service
public class GiveFlowServiceImpl extends BaseServiceImpl implements GiveFlowService {

    @Resource
    private GiveRecordMapper giveRecordMapper;

    private static final Logger logger = LoggerFactory.getLogger(GiveFlowServiceImpl.class);


    @Override
    public void submitGiveAudit(Long[] ids) {

        logger.info("调用submitGiveAudit方法，参数为：{}", JSON.toJSONString(ids));
        if (ids == null || ids.length == 0) {
            throw new BusinessException("调用submitGiveAudit方法参数ids为空！");
        }
        Long userId = UserUtils.getUserId();
        giveRecordMapper.submitGiveAudit(ids, userId);

    }

    @Override
    public List<GiveRecord> giveFlowList(GiveRecord param) {

        logger.info("调用giveFlowList方法，参数为：{}", JSON.toJSONString(param));
        // 根据角色查询需要审核的数据
        Long userId = UserUtils.getUserId();
        param.setGiveAuditRole(getRole(userId));

        PageHelperUtils.startPage();
        return giveRecordMapper.selectGiveFlowList(param);
    }

    @Override
    public void auditGiveFlow(String auditType, Long[] ids) {

        logger.info("调用auditGiveFlow方法，参数auditType为:{}，参数ids为:{}", JSON.toJSONString(auditType), JSON.toJSONString(ids));
        if(StringUtils.isBlank(auditType)){
            throw new BusinessException("调用auditGiveFlow方法参数auditType为空！");
        }
        if (ids == null || ids.length == 0){
            throw new BusinessException("调用auditGiveFlow方法参数ids为空！");
        }
        Long userId = UserUtils.getUserId();
        String roleCode = getRole(userId);
        String auditRole = CustomerInfoConstants.FINANCE_ROLE_CODE;
        if (StringUtils.equals(CustomerInfoConstants.AUDIT_TYPE_APPROVE, auditType)){
            if (StringUtils.equals(roleCode, CustomerInfoConstants.FINANCE_ROLE_CODE)){
                auditRole =  CustomerInfoConstants.ADMIN_ROLE_CODE;
                auditType = CustomerInfoConstants.GIVE_STATUS_AUDIT;
            }
            if (StringUtils.equals(roleCode, CustomerInfoConstants.ADMIN_ROLE_CODE)){
                auditRole =  CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE;
                auditType = CustomerInfoConstants.GIVE_STATUS_AUDIT;
            }
            if (StringUtils.equals(roleCode, CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE)){
                auditRole =  CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE;
                auditType = CustomerInfoConstants.GIVE_STATUS_YES;
            }
        }else {
            auditRole = roleCode;
        }

        giveRecordMapper.updateAuditInfo(auditType, auditRole, ids);

    }
}
