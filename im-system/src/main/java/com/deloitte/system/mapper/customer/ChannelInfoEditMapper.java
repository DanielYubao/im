package com.deloitte.system.mapper.customer;


import com.deloitte.system.model.customer.ChannelInfoEdit;
import com.deloitte.system.model.customer.CustomerInfoEdit;

import java.util.List;

public interface ChannelInfoEditMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ChannelInfoEdit record);

    int insertSelective(ChannelInfoEdit record);

    ChannelInfoEdit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelInfoEdit record);

    int updateByPrimaryKey(ChannelInfoEdit record);

    /**
     * 查询列表
     * @param param
     * @return
     */
    List<ChannelInfoEdit> selectList(ChannelInfoEdit param);

    /**
     * 物理删除数据
     * @param ids
     * @return
     */
    int deleteChannelInfoEdit(Long[] ids);
}