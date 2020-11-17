package com.deloitte.system.mapper.sys;

import com.deloitte.system.model.sys.RoleMenuRel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 角色菜单 数据层
 *
 * @author ps-auto
 * @date 2019-06-21
 */
public interface RoleMenuRelMapper {
    /**
     * 查询角色菜单信息
     *
     * @param id 角色菜单ID
     * @return 角色菜单信息
     */
    public RoleMenuRel selectRoleMenuRelById(Long id);

    /**
     * 查询角色菜单列表
     *
     * @param roleMenuRel 角色菜单信息
     * @return 角色菜单集合
     */
    public List<RoleMenuRel> selectRoleMenuRelList(RoleMenuRel roleMenuRel);

    /**
     * 新增角色菜单
     *
     * @param roleMenuRel 角色菜单信息
     * @return 结果
     */
    public int insertRoleMenuRel(RoleMenuRel roleMenuRel);

    /**
     * 修改角色菜单
     *
     * @param roleMenuRel 角色菜单信息
     * @return 结果
     */
    public int updateRoleMenuRel(RoleMenuRel roleMenuRel);

    /**
     * 删除角色菜单
     *
     * @param id 角色菜单ID
     * @return 结果
     */
    public int deleteRoleMenuRelById(Long id);

    /**
     * 批量删除角色菜单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleMenuRelByIds(Long[] ids);

    void insertRoleMenuRelList(@RequestParam("roleMenuRels") List<RoleMenuRel> roleMenuRels);

    /**
     * 根据角色id删除
     * @param id
     */
    void deleteRoleMenuRelByRoleId(Long id);
}