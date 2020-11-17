package com.deloitte.system.service.customer.impl;

import com.deloitte.common.utils.StringUtils;
import com.deloitte.system.common.CustomerInfoConstants;
import com.deloitte.system.model.sys.Role;
import com.deloitte.system.service.RoleService;
import com.deloitte.system.service.customer.BaseService;
import com.deloitte.system.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QiaoYubao
 * @Date: 23/04/2020
 * @Description:
 */
@Service
public class BaseServiceImpl implements BaseService {

    @Resource
    private RoleService roleService;

    @Override
    public boolean adminPermission(Long id) {
        if (id == null) {
            id = UserUtils.getUserId();
        }
        List<Role> roleList = roleService.selectRoleByUserId(id);
        //未授权用户
        if (CollectionUtils.isEmpty(roleList)) {
            return false;
        }

        for (Role role : roleList) {
            // 管理员 超级管理员
            if (StringUtils.equals(role.getCode(), CustomerInfoConstants.ADMIN_ROLE_CODE)
                    || StringUtils.equals(role.getCode(), CustomerInfoConstants.ADMINISTRATOR_ROLE_CODE)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean financePermission(Long id) {
        if (id == null) {
            id = UserUtils.getUserId();
        }
        List<Role> roleList = roleService.selectRoleByUserId(id);
        //未授权用户
        if (CollectionUtils.isEmpty(roleList)) {
            return false;
        }

        for (Role role : roleList) {
            if (StringUtils.equals(role.getCode(), CustomerInfoConstants.FINANCE_ROLE_CODE)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasRole(Long id, String roleCode) {
        if (id == null) {
            id = UserUtils.getUserId();
        }
        List<Role> roleList = roleService.selectRoleByUserId(id);
        //未授权用户
        if (CollectionUtils.isEmpty(roleList)) {
            return false;
        }

        for (Role role : roleList) {
            // 管理员 超级管理员
            if (StringUtils.equals(role.getCode(), roleCode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getRole(Long id) {
        if (id == null) {
            id = UserUtils.getUserId();
        }

        List<Role> roleList = roleService.selectRoleByUserId(id);
        //未授权用户
        if (CollectionUtils.isEmpty(roleList)) {
            return CustomerInfoConstants.UN_ROLE_CODE;
        }
        return roleList.get(0).getCode();
    }
}
