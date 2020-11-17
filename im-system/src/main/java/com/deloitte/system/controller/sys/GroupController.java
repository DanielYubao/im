package com.deloitte.system.controller.sys;

import com.deloitte.common.model.AjaxResult;
import com.deloitte.framework.page.TableDataInfo;
import com.deloitte.framework.support.BaseController;
import com.deloitte.system.common.SystemModualConstants;
import com.deloitte.system.model.sys.Group;
import com.deloitte.system.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组信息操作处理
 *
 * @author ps-auto
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/system/group")
public class GroupController extends BaseController {
    private String prefix = SystemModualConstants.DEF_SYSTEM_MODUAL_TEMPLATES_PREFIX + "/group";

    @Autowired
    private GroupService groupService;

    //	//@Permissions("system:group:view")
    @GetMapping("/index")
    public String group() {
        return prefix + "/groupList";
    }

    /**
     * 查询组列表
     */
//	//@Permissions("system:group:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Group group) {
        startPage();
        List<Group> list = groupService.selectGroupList(group);
        return getDataTable(list);
    }

    /**
     * 新增组
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存组
     */
    //@Permissions("system:group:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Group group) {
        return toAjax(groupService.insertGroup(group));
    }

    /**
     * 修改组
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Group group = groupService.selectGroupById(id);
        mmap.put("group", group);
        return prefix + "/edit";
    }

    /**
     * 修改保存组
     */
    //@Permissions("system:group:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Group group) {
        return toAjax(groupService.updateGroup(group));
    }

    /**
     * 删除组
     */
    //@Permissions("system:group:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestBody Long[] ids) {
        return toAjax(groupService.deleteGroupByIds(ids));
    }

    /**
     * 检验组名是否唯一
     * @param groupName
     * @return
     */
    @RequestMapping("/checkGroupNameUnique")
    @ResponseBody
    public int checkGroupNameUnique(String groupName){
        return groupService.checkGroupNameUnique(groupName);
    }

}
