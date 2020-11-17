package com.deloitte.system.service.sys.impl;

import com.deloitte.common.utils.Convert;
import com.deloitte.system.mapper.sys.RoleMenuRelMapper;
import com.deloitte.system.model.sys.RoleMenuRel;
import com.deloitte.system.service.RoleMenuRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * daily work角色菜单 服务层实现
 * 
 * @author ps-auto
 * @date 2019-06-21
 */
@Service
public class RoleMenuRelServiceImpl implements RoleMenuRelService
{
	@Autowired
	private RoleMenuRelMapper roleMenuRelMapper;

	/**
     * 查询daily work角色菜单信息
     * 
     * @param id daily work角色菜单ID
     * @return daily work角色菜单信息
     */
    @Override
	public RoleMenuRel selectRoleMenuRelById(Long id)
	{
	    return roleMenuRelMapper.selectRoleMenuRelById(id);
	}
	
	/**
     * 查询daily work角色菜单列表
     * 
     * @param roleMenuRel daily work角色菜单信息
     * @return daily work角色菜单集合
     */
	@Override
	public List<RoleMenuRel> selectRoleMenuRelList(RoleMenuRel roleMenuRel)
	{
	    return roleMenuRelMapper.selectRoleMenuRelList(roleMenuRel);
	}
	
    /**
     * 新增daily work角色菜单
     * 
     * @param roleMenuRel daily work角色菜单信息
     * @return 结果
     */
	@Override
	public int insertRoleMenuRel(RoleMenuRel roleMenuRel)
	{
	    return roleMenuRelMapper.insertRoleMenuRel(roleMenuRel);
	}
	
	/**
     * 修改daily work角色菜单
     * 
     * @param roleMenuRel daily work角色菜单信息
     * @return 结果
     */
	@Override
	public int updateRoleMenuRel(RoleMenuRel roleMenuRel)
	{
	    return roleMenuRelMapper.updateRoleMenuRel(roleMenuRel);
	}

	/**
     * 删除daily work角色菜单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteRoleMenuRelByIds(String ids)
	{
		return roleMenuRelMapper.deleteRoleMenuRelByIds(Convert.toLongArray(ids));
	}

	@Override
	public void insertRoleMenuRelList(List<RoleMenuRel> roleMenuRels) {
		roleMenuRelMapper.insertRoleMenuRelList(roleMenuRels);
	}

	@Override
	public void deleteRoleMenuRelByRoleId(Long id) {
		roleMenuRelMapper.deleteRoleMenuRelByRoleId(id);
	}

}
