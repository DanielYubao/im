package com.deloitte.system.common;

/**
 * @Author: QiaoYubao
 * @Date: 15/04/2020 10:32
 * @Description: 客户信息管理常量模块
 */
public class CustomerInfoConstants {

    /** 管理员角色编码code */
    public static final String ADMIN_ROLE_CODE = "admin";

    /** 超级管理员角色编码code */
    public static final String ADMINISTRATOR_ROLE_CODE = "administrator";

    /** 销售角色编码code */
    public static final String SALE_ROLE_CODE = "sale";

    /** 财务角色编码code */
    public static final String FINANCE_ROLE_CODE= "finance";

    /** 未授权 */
    public static final String UN_ROLE_CODE = "un_role";


    public static final String OPERATION_SUCCESS = "操作成功";

    // 数据变更状态-未改变、审核中、拒绝、审核完成
    public static final String CHANGE_STATUS_UNCHANGED = "unchanged";
    public static final String CHANGE_STATUS_AUDIT = "audit";
    public static final String CHANGE_STATUS_REFUSE = "refuse";
    public static final String CHANGE_STATUS_COMPLETED = "completed";

    // 变更类型-删除
    public static final String CHANGE_TYPE_DELETE = "delete";
    // 变更类型-修改
    public static final String CHANGE_TYPE_EDIT = "edit";

    // 审核类型 -- 通过
    public static final String AUDIT_TYPE_APPROVE = "approve";
    // 审核类型 -- 拒绝
    public static final String AUDIT_TYPE_REFUSE = "refuse";

    // 打款记录审核状态--未打款、审核中、拒绝、已打款
    public static final String PAYMENT_STATUS_NO = "unpayment";
    public static final String PAYMENT_STATUS_AUDIT = "audit";
    public static final String PAYMENT_STATUS_REFUSE = "refuse";
    public static final String PAYMENT_STATUS_YES = "payment";

    // 赠送记录审核状态--未赠送、审核中、拒绝、已赠送
    public static final String GIVE_STATUS_NO = "ungive";
    public static final String GIVE_STATUS_AUDIT = "audit";
    public static final String GIVE_STATUS_REFUSE = "refuse";
    public static final String GIVE_STATUS_YES = "give";

    // 支付方式--银行卡、其他、两种
    public static final String PEYMENT_TYPE_BANK = "bank";
    public static final String PEYMENT_TYPE_OTHER = "other";
    public static final String PEYMENT_TYPE_ALL = "all";

}
