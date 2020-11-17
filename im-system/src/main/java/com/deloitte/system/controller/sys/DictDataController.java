package com.deloitte.system.controller.sys;

import java.util.List;

import com.deloitte.common.model.AjaxResult;
import com.deloitte.framework.page.TableDataInfo;
import com.deloitte.framework.support.BaseController;
import com.deloitte.system.common.SystemModualConstants;
import com.deloitte.system.model.sys.DictData;
import com.deloitte.system.service.DictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 字典数据 信息操作处理
 *
 * @author ps-auto
 * @date 2019-06-25
 */
@Controller
@RequestMapping("/system/dictData")
public class DictDataController extends BaseController {
    private String prefix = SystemModualConstants.DEF_SYSTEM_MODUAL_TEMPLATES_PREFIX + "/dict";

    @Autowired
    private DictDataService dictDataService;

    // @Permissions("system:dictData:view")
    @GetMapping("/index")
    public String dictData(String dictType, ModelMap modelMap) {
        modelMap.put("dictType", dictType);
        return prefix + "/dictDataList";
    }

    /**
     * 查询字典数据列表
     */
    // @Permissions("system:dictData:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DictData dictData) {
        startPage();
        List<DictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    /**
     * 根据字典类型查询数据
     * @param dictType
     * @return
     */
    @RequestMapping("/queryByDictType")
    @ResponseBody
    public List<DictData> queryByDictType(String dictType){
        return dictDataService.selectDictDataByType(dictType);
    }

    /**
     * 新增字典数据
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存字典数据
     */
    // @Permissions("system:dictData:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DictData dictData) {
        return toAjax(dictDataService.insertDictData(dictData));
    }

    /**
     * 修改字典数据
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DictData dictData = dictDataService.selectDictDataById(id);
        mmap.put("dictData", dictData);
        return prefix + "/edit";
    }

    /**
     * 修改保存字典数据
     */
    // @Permissions("system:dictData:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DictData dictData) {
        return toAjax(dictDataService.updateDictData(dictData));
    }

    /**
     * 删除字典数据
     */
    // @Permissions("system:dictData:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestParam(value="ids[]") Long[] ids) {
        return toAjax(dictDataService.deleteDictDataByIds(ids));
    }

    /**
     * 检验字典标签是否唯一
     *
     * @param dictType
     * @param dictLabel
     * @return
     */
    @RequestMapping("/checkDictLabelUnique")
    @ResponseBody
    public int checkDictLabelUnique(String dictType, String dictLabel) {

        return dictDataService.checkDictLabelUnique(dictType, dictLabel);
    }

    /**
     * 检验字典值是否唯一
     *
     * @param dictType
     * @param dictValue
     * @return
     */
    @RequestMapping("/checkDictValueUnique")
    @ResponseBody
    public int checkDictValueUnique(String dictType, String dictValue) {

        return dictDataService.checkDictValueUnique(dictType, dictValue);
    }

}
