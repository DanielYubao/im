package com.deloitte.system.mapper.customer;


import com.deloitte.system.model.customer.GiveRecord;
import com.deloitte.system.model.customer.GiveRecordExcel;
import com.deloitte.system.model.customer.GiveRecord;
import com.deloitte.system.model.customer.PaymentRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GiveRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GiveRecord record);

    int insertSelective(GiveRecord record);

    GiveRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GiveRecord record);

    int updateByPrimaryKey(GiveRecord record);

    /**
     * 保存上传的赠送记录信息
     * @param userId
     */
    void saveGiveExcel(Long userId);

    /**
     * 查询列表
     * @param giveRecord
     * @return
     */
    List<GiveRecord> selectList(GiveRecord giveRecord);


    /**
     * 导出赠送记录
     * @param param
     * @return
     */
    List<Map<String,Object>> selectExportData(GiveRecord giveRecord);
    
    /**
     * 删除赠送记录信息
     * @param ids
     * @return
     */
    int deleteGiveRecord(Long[] ids);


    /**
     * 批量更新数据状态
     * @param status
     * @param ids
     * @return
     */
    int updateStatus(@Param("status")String status, @Param("array")Long[] ids);

    /**
     * 批量更新赠送流程审核信息
     * @param auditStatus
     * @param auditRole
     * @param ids
     * @return
     */
    int updateAuditInfo(@Param("auditStatus")String auditStatus, @Param("auditRole")String auditRole, @Param("array")Long[] ids);

    /**
     * 查询赠送流程审核列表
     * @param param
     * @return
     */
    List<GiveRecord> selectGiveFlowList(GiveRecord param);

    /**
     * 赠送信息统计
     * @param param
     * @return
     */
    Map giveInfoData(GiveRecord param);

    /**
     * 查询指定用户创建的客户信息
     * @param userId
     * @return
     */
    List<Map> queryCustomerInfo(Long userId);

    /**
     * 提交赠送记录审核
     * @param ids
     */
    void submitGiveAudit(@Param("array")Long[] ids, @Param("userId")Long userId);

}