package com.deloitte.system.service.customer;

import com.deloitte.system.model.customer.GiveRecordEdit;

import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 04/05/2020
 * @Description:
 */
public interface GiveRecordEditService extends BaseService {

    /**
     * 查询赠送记录审核信息列表
     * @param param
     * @return
     */
    List<GiveRecordEdit> giveAuditList(GiveRecordEdit param);

    /**
     * 查询赠送记录信息修改详情
     * @param id
     * @return
     */
    GiveRecordEdit queryGiveEdit(Long id);

    /**
     * 赠送记录信息变更审核
     * @param auditType
     * @param ids
     */
    void auditGiveEdit(String auditType, Long[] ids);

}
