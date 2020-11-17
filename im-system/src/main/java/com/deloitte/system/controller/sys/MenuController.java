package com.deloitte.system.controller.sys;

import com.deloitte.common.model.AjaxResult;
import com.deloitte.framework.page.TableDataInfo;
import com.deloitte.framework.support.BaseController;
import com.deloitte.system.common.SystemModualConstants;
import com.deloitte.system.model.sys.Menu;
import com.deloitte.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单 信息操作处理
 *
 * @author ps-auto
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController {
    
    private String prefix = SystemModualConstants.DEF_SYSTEM_MODUAL_TEMPLATES_PREFIX + "/menu";

    @Autowired
    private MenuService menuService;

    @GetMapping("index")
    public String menus() {
        return prefix + "/menuList";
    }

    /**
     * 获取树形菜单数据
     * @param menu
     * @return
     */
    @RequestMapping("/getTreeData")
    @ResponseBody
    public List<Menu> getTreeData(Menu menu){

        return  menuService.getTreeData(menu);
    }

    /**
     * 查询菜单列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Menu menu) {
        startPage();
        List<Menu> list = menuService.selectMenuList(menu);
        return getDataTable(list);
    }

    /**
     * 新增菜单
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存菜单
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Menu menu) {
        return toAjax(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Menu menu = menuService.selectMenuById(id);
        mmap.put("menu", menu);
        return prefix + "/edit";
    }

    /**
     * 修改保存菜单
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Menu menu) {
        return toAjax(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(menuService.deleteMenuByIds(ids));
    }


    /**
     * 根据角色id查询
     * @param roleId
     * @return
     */
    @PostMapping("/getByRoleId")
    @ResponseBody
    public List<Menu> getByRoleId(Long roleId) {
        List<Menu> menuList =  menuService.getByRoleId(roleId);
        return menuList;
    }


}
