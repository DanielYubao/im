package com.deloitte.system.service.customer;

import com.deloitte.system.model.customer.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 30/05/2020
 * @Description: 渠道客户服务接口
 */
public interface ChannelCustomerService extends BaseService {

    /**
     * 下载渠道客户信息上传模板
     * @param response
     * @throws Exception
     */
    void downloadCustomerExcel(HttpServletResponse response) throws Exception;

    /**
     * 上传渠道客户信息
     * @param inputStream
     * @param fileName
     */
    void uploadCustomerExcel(InputStream inputStream, String fileName);

    /**
     * 保存上传的渠道客户信息
     */
    void saveCustomerExcel();

    /**
     * 查询上传的渠道客户信息
     * @param param
     * @return
     */
    List<ChannelCustomerInfoExcel> customerExcelList(ChannelCustomerInfoExcel param);

    /**
     * 查询渠道客户信息列表
     * @param param
     * @return
     */
    List<ChannelCustomerInfo> customerList(ChannelCustomerInfo param);

    /**
     * 根据id查询渠道客户信息
     * @param id
     * @return
     */
    ChannelCustomerInfo queryCustomerInfo(Long id);

    /**
     * 更新渠道客户信息
     * @param channelCustomerInfo
     * @return
     */
    int updateCustomerInfo( ChannelCustomerInfo channelCustomerInfo);

    /**
     * 添加渠道客户信息
     * @param channelCustomerInfo
     * @return
     */
    String addCustomerInfo(ChannelCustomerInfo channelCustomerInfo);

    /**
     * 删除渠道客户信息
     * @param ids
     * @return
     */
    int deleteCustomerInfo(Long[] ids);

    /**
     * 修改渠道客户信息归属人
     * @param customerIds
     * @param targetCreateBy
     * @return
     */
    int modifyCreateBy( Long[] customerIds, String targetCreateBy);

    /**
     * 查询渠道客户审核信息列表
     * @param param
     * @return
     */
    List<ChannelCustomerInfoEdit> customerAuditList(ChannelCustomerInfoEdit param);

    /**
     * 审核渠道客户信息变更
     * @param auditType
     * @param ids
     */
    void auditCustomerEdit(String auditType, Long[] ids);


}
