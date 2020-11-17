package com.deloitte.system.controller.sys;

import com.deloitte.common.model.AjaxResult;
import com.deloitte.framework.page.TableDataInfo;
import com.deloitte.framework.support.BaseController;
import com.deloitte.system.common.SystemModualConstants;
import com.deloitte.system.model.sys.Role;
import com.deloitte.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色信息操作处理
 *
 * @author ps-auto
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {
    private String prefix = SystemModualConstants.DEF_SYSTEM_MODUAL_TEMPLATES_PREFIX + "/role";

    @Autowired
    private RoleService roleService;

    @GetMapping("/index")
    public String role() {
        return prefix + "/roleList";
    }

    /**
     * 查询角色列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Role role) {
        startPage();
        List<Role> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }

    /**
     * 新增角色
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存角色
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody Role role) {
        if(role.getMenuIds().isEmpty()){
            return  AjaxResult.error("请选择权限!");
        }
        return toAjax(roleService.insertRole(role));
    }

    /**
     * 修改角色
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Role role = roleService.selectRoleById(id);
        mmap.put("role", role);
        return prefix + "/edit";
    }

    /**
     * 修改保存角色
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody Role role) {
        if(role.getMenuIds().isEmpty()){
            return  AjaxResult.error("请选择权限!");
        }
        return toAjax(roleService.updateRole(role));
    }


    /**
     * 删除角色
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestParam("ids[]") Long[] ids) {
        return roleService.deleteRoleByIds(ids);
    }


    @PostMapping("/findByUserId")
    @ResponseBody
    public List<Role> findByUserId(@RequestParam Long id) {
        return roleService.findByUserId(id);
    }


    @PostMapping("/findRoleListByNameOrCode")
    @ResponseBody
    public List<Role> findRoleListByNameOrCode(Role role) {
        return roleService.findRoleListByNameOrCode(role);
    }


}
