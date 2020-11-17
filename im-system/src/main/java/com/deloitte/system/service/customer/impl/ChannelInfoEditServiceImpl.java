package com.deloitte.system.service.customer.impl;

import com.alibaba.fastjson.JSON;
import com.deloitte.common.utils.StringUtils;
import com.deloitte.framework.exception.BusinessException;
import com.deloitte.framework.utils.PageHelperUtils;
import com.deloitte.system.common.CustomerInfoConstants;
import com.deloitte.system.mapper.customer.ChannelInfoEditMapper;
import com.deloitte.system.mapper.customer.ChannelInfoMapper;
import com.deloitte.system.model.customer.ChannelInfo;
import com.deloitte.system.model.customer.ChannelInfoEdit;
import com.deloitte.system.service.customer.ChannelInfoEditService;
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
 * @Date: 28/05/2020
 * @Description:
 */
@Service
public class ChannelInfoEditServiceImpl extends BaseServiceImpl implements ChannelInfoEditService {

    @Resource
    private ChannelInfoEditMapper channelInfoEditMapper;

    @Resource
    private ChannelInfoMapper channelInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(ChannelInfoEditServiceImpl.class);

    @Override
    public List<ChannelInfoEdit> channelAuditList(ChannelInfoEdit param) {
        logger.info("调用channelAuditList方法，参数为:{}", JSON.toJSONString(param));
        // 根据角色查询需要审核的数据
        Long userId = UserUtils.getUserId();
        param.setAuditRole(getRole(userId));

        PageHelperUtils.startPage();
        return channelInfoEditMapper.selectList(param);
    }

    @Override
    @Transactional
    public void auditChannelEdit(String auditType, Long[] ids) {

        logger.info("调用auditChannelEdit方法，参数auditType为:{}，参数ids为:{}", JSON.toJSONString(auditType), JSON.toJSONString(ids));
        if(StringUtils.isBlank(auditType)){
            throw new BusinessException("调用auditChannelEdit方法参数auditType为空！");
        }
        if (ids == null || ids.length == 0){
            throw new BusinessException("调用auditChannelEdit方法参数ids为空！");
        }

        // 数据变动审核 管理员--> 超级管理员
        Long userId = UserUtils.getUserId();
        String roleCode = getRole(userId);
        if (StringUtils.equals(auditType, CustomerInfoConstants.AUDIT_TYPE_APPROVE)){
            for (Long id : ids){
                ChannelInfo channelInfo = channelInfoMapper.selectByPrimaryKey(id);
                ChannelInfoEdit channelInfoEdit = channelInfoEditMapper.selectByPrimaryKey(id);
                channelInfo.setUpdater(userId);
                channelInfoEdit.setUpdater(userId);

                // 修改类型的数据
                if (StringUtils.equals(channelInfoEdit.getChangeType(), CustomerInfoConstants.CHANGE_TYPE_EDIT )) {
                    // 超级管理员审核
                    if (StringUtils.equals(CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE, roleCode )) {
                        BeanUtils.copyProperties(channelInfoEdit, channelInfo);
                        channelInfo.setStatus(CustomerInfoConstants.CHANGE_STATUS_COMPLETED);
                        // 删除数据
                        channelInfoEditMapper.deleteByPrimaryKey(id);
                    }else {
                        channelInfo.setAuditRole(CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE);
                        channelInfoEdit.setAuditRole(CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE);
                        channelInfoEditMapper.updateByPrimaryKey(channelInfoEdit);
                    }
                }

                // 删除类型的数据
                if (StringUtils.equals(channelInfoEdit.getChangeType(), CustomerInfoConstants.CHANGE_TYPE_DELETE )){
                    if (StringUtils.equals(CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE, roleCode )) {
                        channelInfo.setDeleteFlag(1);
                        channelInfo.setStatus(CustomerInfoConstants.CHANGE_STATUS_COMPLETED);
                        channelInfoEditMapper.deleteByPrimaryKey(id);
                    }else {
                        channelInfo.setAuditRole(CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE);
                        channelInfoEdit.setAuditRole(CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE);
                        channelInfoEditMapper.updateByPrimaryKey(channelInfoEdit);
                    }
                }
                // 更新状态
                channelInfoMapper.updateByPrimaryKey(channelInfo);
            }
        }else if(StringUtils.equals(auditType, CustomerInfoConstants.AUDIT_TYPE_REFUSE )){
            // 审核不通过 更改源数据状态为拒绝  删除审核中的数据
            channelInfoMapper.updateStatus(CustomerInfoConstants.CHANGE_STATUS_REFUSE, ids);
            channelInfoEditMapper.deleteChannelInfoEdit(ids);
        }
    }
}
