package com.deloitte.system.controller.sys;

import com.deloitte.common.model.AjaxResult;
import com.deloitte.framework.page.TableDataInfo;
import com.deloitte.framework.support.BaseController;
import com.deloitte.system.common.SystemModualConstants;
import com.deloitte.system.model.sys.DictType;
import com.deloitte.system.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典类型 信息操作处理
 *
 * @author ps-auto
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/system/dictType")
public class DictTypeController extends BaseController {
    
    private String prefix = SystemModualConstants.DEF_SYSTEM_MODUAL_TEMPLATES_PREFIX + "/dict";

    @Autowired
    private DictTypeService dictTypeService;

//    @Permissions("system:dict:view")
    @GetMapping("/index")
    public String dictType() {
        return prefix + "/dictTypeList";
    }

    /**
     * 查询字典类型列表
     */
//    @Permissions("system:dictType:list")
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(DictType dictType) {
        startPage();
        List<DictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    /**
     * 新增字典类型
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存字典类型
     */
//    @Permissions("system:dictType:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DictType dictType) {
        return toAjax(dictTypeService.insertDictType(dictType));
    }

    /**
     * 修改字典类型
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DictType dictType = dictTypeService.selectDictTypeById(id);
        mmap.put("dictType", dictType);
        return prefix + "/edit";
    }

    /**
     * 修改保存字典类型
     */
//    @Permissions("system:dictType:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DictType dictType) {
        return toAjax(dictTypeService.updateDictType(dictType));
    }

    /**
     * 删除字典类型
     */
//    @Permissions("system:dict:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestParam(value="ids[]") String ids) throws Exception {
        return toAjax(dictTypeService.deleteDictTypeByIds(ids));
    }

    /**
     *
     * @param classifyCode
     * @return
     */
    @RequestMapping("/checkDictTypeUnique")
    @ResponseBody
    public int checkDictTypeUnique(String classifyCode){
        return dictTypeService.checkDictTypeUnique(classifyCode);
    }


    /**
     * 检验字典名称是否唯一
     * @param dictName
     * @return
     */
    @RequestMapping("/checkDictNameUnique")
    @ResponseBody
    public int checkDictNameUnique(String dictName){
        return dictTypeService.checkDictNameUnique(dictName);
    }
}
