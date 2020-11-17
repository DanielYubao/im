package com.deloitte.system.mapper.sys;


import com.deloitte.system.model.sys.Menu;

import java.util.List;

/**
 * 菜单 数据层
 * 
 * @author ps-auto
 * @date 2019-06-21
 */
public interface MenuMapper
{
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
     * 删除菜单
     * 
     * @param id 菜单ID
     * @return 结果
     */
	public int deleteMenuById(Long id);
	
	/**
     * 批量删除菜单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMenuByIds(Long[] ids);


	/**
	 * 根据角色id查询菜单
	 * @param roleId
	 * @return
	 */
    List<Menu> getByRoleId(Long roleId);

	/**
	 * 根据用户id查询菜单
	 * @param userId
	 * @return
	 */
	List<Menu> getMenuByUserId(Long userId);
}