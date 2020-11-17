package com.deloitte.system.service.customer;

/**
 * @Author: QiaoYubao
 * @Date: 23/04/2020
 * @Description:
 */
public interface BaseService {

    /**
     * 判断用户是否有管理员权限和超级管理员权限
     * @param id
     * @return
     */
    boolean adminPermission(Long id);

    /**
     * 判断用户是否有财务权限和超级管理员权限
     * @param id
     * @return
     */
    boolean financePermission(Long id);

    /**
     * 判断用户是否有某个权限
     * @param id
     * @return
     */
    boolean hasRole(Long id, String roleCode);

    /**
     * 获取用户角色
     * @param id
     * @return
     */
    String getRole(Long id);

}
