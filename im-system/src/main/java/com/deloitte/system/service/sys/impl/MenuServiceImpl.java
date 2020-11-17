package com.deloitte.system.service.sys.impl;

import com.deloitte.common.utils.Convert;
import com.deloitte.system.common.SystemModualConstants;
import com.deloitte.system.mapper.sys.MenuMapper;
import com.deloitte.system.model.sys.Menu;
import com.deloitte.system.service.MenuService;
import com.deloitte.system.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单 服务层实现
 *
 * @author ps-auto
 * @date 2019-06-21
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询菜单信息
     *
     * @param id 菜单ID
     * @return 菜单信息
     */
    @Override
    public Menu selectMenuById(Long id) {
        return menuMapper.selectMenuById(id);
    }

    /**
     * 查询菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单集合
     */
    @Override
    public List<Menu> selectMenuList(Menu menu) {
        return menuMapper.selectMenuList(menu);
    }

    /**
     * 新增菜单
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int insertMenu(Menu menu) {
        menu.setCreator(UserUtils.getUserId());
        menu.setUpdater(UserUtils.getUserId());
        menu.setDisableFlag(SystemModualConstants.DISABLE_FLAG_FALSE);
        menu.setDeleteFlag(SystemModualConstants.DELETE_FLAG_FALSE);
        return menuMapper.insertMenu(menu);
    }

    /**
     * 修改菜单
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int updateMenu(Menu menu) {
        menu.setUpdater(UserUtils.getUserId());
        return menuMapper.updateMenu(menu);
    }

    /**
     * 删除菜单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMenuByIds(String ids) {
        return menuMapper.deleteMenuByIds(Convert.toLongArray(ids));
    }

    /**
     * 获取树形菜单数据
     * @param menu
     * @return
     */
    @Override
    public List<Menu> getTreeData(Menu menus) {

        menus.setDeleteFlag(SystemModualConstants.DELETE_FLAG_FALSE);
        menus.setDisableFlag(SystemModualConstants.DISABLE_FLAG_FALSE);
        List<Menu> menuList = menuMapper.selectMenuList(menus);

        Map<Long, Menu> map = new HashMap<>();
        for (Menu menu : menuList) {
            map.put(menu.getId(), menu);
        }

        List<Menu> list = new ArrayList<>();
        for (Menu menu : menuList) {
            // 获取到根节点组装树
            Menu parentMenu = map.get(menu.getParentId());
            if (parentMenu == null){
                menu.setChildren(getChildren(menu, menuList));
                list.add(menu);
            }

        }

        return list;
    }

    /**
     * 获取孩子节点
     * @param parentMenu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu parentMenu, List<Menu> menus){
        List<Menu> list = new ArrayList<>();

        for (Menu menu : menus) {
            if(parentMenu.getId()== menu.getParentId()) {
                list.add(menu);
                menu.setChildren(getChildren(menu, menus));
            }
        }

        return list;
    }


    /**
     * 获取孩子节点
     * @param parentId
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Long parentId, List<Menu> menus){
        List<Menu> list = new ArrayList<>();

        for (Menu menu : menus) {
            if(parentId == menu.getParentId()) {
                list.add(menu);
                menu.setChildren(getChildren(menu, menus));
            }
        }

        return list;
    }

    /**
     * 根据角色查询菜单
     * @param roleId
     * @return
     */
    @Override
    public List<Menu> getByRoleId(Long roleId){
        return menuMapper.getByRoleId(roleId);
    }

    /**
     * 根据用户查询菜单
     * @param userId
     * @return
     */
    @Override
    public List<Menu> getMenuByUser(Long userId) {

        List<Menu> menus = menuMapper.getMenuByUserId(userId);
        List<Menu> result = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                menu.setChildren(getChildren(menu, menus));
                result.add(menu);
            }

        }

        return result;
    }
}
