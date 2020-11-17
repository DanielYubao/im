package com.deloitte.system.service.customer;


import com.deloitte.system.model.customer.ChannelInfoEdit;

import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 28/05/2020
 * @Description: 渠道信息变更服务接口
 */
public interface ChannelInfoEditService extends BaseService {

    /**
     * 查询渠道变更审核信息列表
     * @param param
     * @return
     */
    List<ChannelInfoEdit> channelAuditList(ChannelInfoEdit param);


    /**
     * 审核渠道信息变更
     * @param auditType
     * @param ids
     */
    void auditChannelEdit(String auditType, Long[] ids);

}
