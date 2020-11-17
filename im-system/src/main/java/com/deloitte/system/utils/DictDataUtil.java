package com.deloitte.system.utils;

import com.deloitte.system.model.sys.DictData;
import com.deloitte.system.service.DictDataService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: QiaoYubao
 * @Date: 25/06/2019
 * @Description: 数据字典工具类
 */
@Component
public class DictDataUtil {

    private static DictDataService dictDataService;


    @Resource
    public void setSysDictDataService(DictDataService dictDataService){
        DictDataUtil.dictDataService = dictDataService;
    }

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 字典类型
     */
    public static List<DictData> getType(String dictType) {
        return dictDataService.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典标签查询字典数据信息
     * @param dictType 字典类型
     * @param dictLable 字典标签
     */
    public static String getValue(String dictType, String dictLable){
        return dictDataService.selectDictValue(dictType, dictLable);
    }

    /**
     * /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType 字典类型
     * @param dictValue 字典键值
     */
    public static String getLable(String dictType, String dictValue){
        return dictDataService.selectDictLabel(dictType, dictValue);
    }

}
