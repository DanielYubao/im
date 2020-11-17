package com.deloitte.system.mapper.customer;


import com.deloitte.system.model.customer.PaymentRecordEdit;

import java.util.List;

public interface PaymentRecordEditMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaymentRecordEdit record);

    int insertSelective(PaymentRecordEdit record);

    PaymentRecordEdit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaymentRecordEdit record);

    int updateByPrimaryKey(PaymentRecordEdit record);

    /**
     * 查询列表
     * @param param
     * @return
     */
    List<PaymentRecordEdit> selectList(PaymentRecordEdit param);


    /**
     * 物理删除数据
     * @param ids
     * @return
     */
    int deletePaymentRecordEdit(Long[] ids);
}