package com.deloitte.system.service;

import com.deloitte.system.model.sys.Menu;

import java.util.List;

/**
 * 菜单 服务层
 *
 * @author ps-auto
 * @date 2019-06-21
 */
public interface MenuService {
    /**
     * 查询菜单信息
     *
     * @param id 菜单ID
     * @return 菜单信息
     */
    public Menu selectMenuById(Long id);

    /**
     * 查询菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单集合
     */
    public List<Menu> selectMenuList(Menu menu);

    /**
     * 新增菜单
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public int insertMenu(Menu menu);

    /**
     * 修改菜单
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public int updateMenu(Menu menu);

    /**
     * 删除菜单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMenuByIds(String ids);

    /**
     * 获取树形菜单数据
     * @param menu
     * @return
     */
    public List<Menu> getTreeData(Menu menu);


    /**
     * 根据角色id查询
     * @param roleId
     * @return
     */
   public List<Menu> getByRoleId(Long roleId);

    /**
     * 根据用户查询菜单
     * @param userId
     * @return
     */
   public List<Menu> getMenuByUser(Long  userId);

}
