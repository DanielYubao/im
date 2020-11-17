package com.deloitte.system.service.customer;

import com.deloitte.system.model.customer.GiveRecord;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 03/05/2020
 * @Description: 赠送流程服务接口
 */
public interface GiveFlowService extends BaseService {

    /**
     * 提交赠送记录信息审核
     * @param ids
     */
    void submitGiveAudit(@RequestParam(value = "ids[]") Long[] ids);

    /**
     * 赠送流程审核列表
     * @param param
     * @return
     */
    List<GiveRecord> giveFlowList(GiveRecord param);

    /**
     * 赠送流程审核
     * @param auditType
     * @param ids
     */
    void auditGiveFlow(String auditType, Long[] ids);

}
