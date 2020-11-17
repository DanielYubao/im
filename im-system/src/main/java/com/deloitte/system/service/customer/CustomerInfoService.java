package com.deloitte.system.service.customer;

import com.deloitte.system.model.customer.CustomerInfo;
import com.deloitte.system.model.customer.CustomerInfoEdit;
import com.deloitte.system.model.customer.CustomerInfoExcel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author: QiaoYubao
 * @Date: 19/03/2020 13:14
 * @Description: 直接客户信息管理服务接口
 */
public interface CustomerInfoService extends BaseService{

    /**
     * 下载直接客户信息上传模板
     * @param response
     * @throws Exception
     */
    void downloadCustomerExcel(HttpServletResponse response) throws Exception;

    /**
     * 上传直接客户信息
     * @param inputStream
     * @param fileName
     */
    void uploadCustomerExcel(InputStream inputStream, String fileName);

    /**
     * 保存上传的直接客户信息
     */
    void saveCustomerExcel();

    /**
     * 查询上传的直接客户信息
     * @param param
     * @return
     */
    List<CustomerInfoExcel> customerExcelList(CustomerInfoExcel param);

    /**
     * 查询直接客户信息列表
     * @param customerInfo
     * @return
     */
    List<CustomerInfo> customerList(CustomerInfo param);

    /**
     * 根据id查询直接客户信息
     * @param id
     * @return
     */
    CustomerInfo queryCustomerInfo(Long id);

    /**
     * 判断用户是否有上传excel的权限--管理员可以上传多次，普通用户只能上传一次
     * @param id
     * @return
     */
    boolean uploadPermission(Long id);

    /**
     * 更新直接客户信息
     * @param customerInfo
     * @return
     */
    int updateCustomerInfo( CustomerInfo customerInfo);

    /**
     * 添加直接客户信息
     * @param customerInfo
     * @return
     */
    String addCustomerInfo(CustomerInfo customerInfo);

    /**
     * 删除直接客户信息
     * @param ids
     * @return
     */
    int deleteCustomerInfo(Long[] ids);

    /**
     * 修改直接客户信息归属人
     * @param customerIds
     * @param targetCreateBy
     * @return
     */
    int modifyCreateBy( Long[] customerIds, String targetCreateBy);

    /**
     * 直接客户信息统计
     * @param param
     * @return
     */
    Map<String, Object> customerInfoData(CustomerInfo param);

}
