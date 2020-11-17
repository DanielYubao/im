package com.deloitte.system.service.sys.impl;

import com.deloitte.common.model.AjaxResult;
import com.deloitte.system.common.SystemModualConstants;
import com.deloitte.system.mapper.sys.RoleMapper;
import com.deloitte.system.model.sys.Role;
import com.deloitte.system.model.sys.RoleMenuRel;
import com.deloitte.system.service.RoleMenuRelService;
import com.deloitte.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色 服务层实现
 *
 * @author ps-auto
 * @date 2019-06-21
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper rolesMapper;

    @Autowired
    private RoleMenuRelService roleMenuRelService;

    /**
     * 查询角色信息
     *
     * @param id 角色ID
     * @return 角色信息
     */
    @Override
    public Role selectRoleById(Long id) {
        return rolesMapper.selectRoleById(id);
    }

    /**
     * 查询角色列表
     *
     * @param role 角色信息
     * @return 角色集合
     */
    @Override
    public List<Role> selectRoleList(Role role) {
        role.setDeleteFlag(SystemModualConstants.DELETE_FLAG_FALSE);
        return rolesMapper.selectRoleList(role);
    }

    /**
     * 新增角色
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertRole(Role role) {
        org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = Long.parseLong(currentUser.getUsername());
        List<Long> menuIds = role.getMenuIds();
        role.setUpdater(userId);
        role.setCreator(userId);
        rolesMapper.insertRole(role);
        List<RoleMenuRel> roleMenuRels = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenuRel roleMenuRel = new RoleMenuRel();
            roleMenuRel.setMenuId(menuId);
            roleMenuRel.setRoleId(role.getId());
            roleMenuRels.add(roleMenuRel);
        }
        roleMenuRelService.insertRoleMenuRelList(roleMenuRels);
        return 1;
    }

    /**
     * 修改角色
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateRole(Role role) {
        org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = Long.parseLong(currentUser.getUsername());
        List<Long> menuIds = role.getMenuIds();
        role.setUpdater(userId);
        rolesMapper.updateRole(role);
        List<RoleMenuRel> roleMenuRels = new ArrayList<>();
        roleMenuRelService.deleteRoleMenuRelByRoleId(role.getId());
        for (Long menuId : menuIds) {
            RoleMenuRel roleMenuRel = new RoleMenuRel();
            roleMenuRel.setMenuId(menuId);
            roleMenuRel.setRoleId(role.getId());
            roleMenuRels.add(roleMenuRel);
        }
        roleMenuRelService.insertRoleMenuRelList(roleMenuRels);
        return 1;
    }

    /**
     * 删除角色对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult deleteRoleByIds(Long[] ids) {
        //判断是否有人员绑定,有的话就返回提示
        for (Long id : ids) {
           Role role =  rolesMapper.selectRoleByIdForDelete(id);
           if(null != role){
              return  AjaxResult.error(role.getName()+"已分配,不能删除");
           }
        }

        rolesMapper.deleteRoleByIds(ids);
        return  AjaxResult.success();
    }

    @Override
    public List<Role> selectRoleByUserId(Long id) {
        return rolesMapper.selectRoleByUserId(id);
    }

    @Override
    public List<Role> findByUserId(Long userId) {
        return rolesMapper.selectRoleByUserId(userId);
    }

    @Override
    public List<Role> findRoleListByNameOrCode(Role role) {
        return rolesMapper.findRoleListByNameOrCode(role);
    }

}
