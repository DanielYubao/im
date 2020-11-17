package com.deloitte.system.mapper.customer;


import com.deloitte.system.model.customer.GiveRecordEdit;

import java.util.List;

public interface GiveRecordEditMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GiveRecordEdit record);

    int insertSelective(GiveRecordEdit record);

    GiveRecordEdit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GiveRecordEdit record);

    int updateByPrimaryKey(GiveRecordEdit record);

    /**
     * 查询列表
     * @param param
     * @return
     */
    List<GiveRecordEdit> selectList(GiveRecordEdit param);


    /**
     * 物理删除数据
     * @param ids
     * @return
     */
    int deleteGiveRecordEdit(Long[] ids);

}