package com.deloitte.system.service.customer;

import com.deloitte.system.model.customer.ChannelInfo;
import com.deloitte.system.model.customer.ChannelInfoExcel;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 28/05/2020
 * @Description: 渠道管理服务接口
 */
public interface ChannelManageService extends BaseService {

    /**
     * 下载渠道信息上传模板
     * @param response
     * @throws Exception
     */
    void downloadChannelExcel(HttpServletResponse response) throws Exception;

    /**
     * 上传渠道信息
     * @param inputStream
     * @param fileName
     */
    void uploadChannelExcel(InputStream inputStream, String fileName);

    /**
     * 保存上传的渠道信息
     */
    void saveChannelExcel();

    /**
     * 查询上传的渠道信息
     * @param param
     * @return
     */
    List<ChannelInfoExcel> channelExcelList(ChannelInfoExcel param);

    /**
     * 查询渠道信息列表
     * @param channelInfo
     * @return
     */
    List<ChannelInfo> channelList(ChannelInfo param);

    /**
     * 根据id查询渠道信息
     * @param id
     * @return
     */
    ChannelInfo queryChannelInfo(Long id);

    /**
     * 查询指定用户创建的渠道信息
     * @param userId
     * @param channelName
     * @return
     */
    List<ChannelInfo> queryChannelByUserAndName(Long userId, String channelName);

    /**
     * 更新渠道信息
     * @param channelInfo
     * @return
     */
    int updateChannelInfo( ChannelInfo channelInfo);

    /**
     * 添加渠道信息
     * @param channelInfo
     * @return
     */
    String addChannelInfo(ChannelInfo channelInfo);

    /**
     * 删除渠道信息
     * @param ids
     * @return
     */
    int deleteChannelInfo(Long[] ids);


}
