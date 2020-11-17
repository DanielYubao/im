package com.deloitte.system.service.customer;

import com.deloitte.system.model.customer.*;
import com.deloitte.system.model.customer.GiveRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author: QiaoYubao
 * @Date: 04/05/2020
 * @Description: 赠送记录服务接口
 */
public interface GiveRecordService extends BaseService {

    /**
     * 下载赠送记录模板
     * @param response
     * @throws Exception
     */
    void downloadGiveExcel(HttpServletResponse response) throws Exception;

    /**
     * 上传赠送记录信息
     * @param inputStream
     * @param fileName
     */
    void uploadGiveExcel(InputStream inputStream, String fileName);

    /**
     * 保存上传的赠送记录信息
     */
    void saveGiveExcel();

    /**
     * 查询上传的赠送记录信息
     * @param param
     * @return
     */
    List<GiveRecordExcel> giveExcelList(GiveRecordExcel param);

    /**
     * 查询赠送记录信息列表
     * @param param
     * @return
     */
    List<GiveRecord> giveList(GiveRecord param);


    /**
     * 导出赠送记录
     * @param paymentRecord
     */
    void exportGiveData(GiveRecord giveRecord, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据id查询赠送记录信息
     * @param id
     * @return
     */
    GiveRecord queryGiveRecord(Long id);

    /**
     * 更新赠送记录信息
     * @param giveRecord
     * @return
     */
    int updateGiveRecord( GiveRecord giveRecord);

    /**
     * 添加赠送记录信息
     * @param giveRecord
     * @return
     */
    String addGiveRecord(GiveRecord giveRecord);

    /**
     * 删除赠送记录信息
     * @param ids
     * @return
     */
    int deleteGiveRecord(Long[] ids);

    /**
     * 赠送信息统计
     * @param param
     * @return
     */
   Map giveInfoData(GiveRecord param);

    /**
     * 查询指定用户的客户信息
     * @param userId
     * @return
     */
   List<Map> queryCustomerInfo(Long userId);
    
}
