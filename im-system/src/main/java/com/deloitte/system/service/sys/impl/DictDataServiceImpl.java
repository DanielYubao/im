package com.deloitte.system.service.sys.impl;

import java.util.List;

import com.deloitte.system.mapper.sys.DictDataMapper;
import com.deloitte.system.model.sys.DictData;
import com.deloitte.system.service.DictDataService;
import com.deloitte.system.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典数据 服务层实现
 *
 * @author ps-auto
 * @date 2019-06-25
 */
@Service
public class DictDataServiceImpl implements DictDataService {

    @Autowired
    private DictDataMapper dictDataMapper;

    /**
     * 查询字典数据信息
     *
     * @param id 字典数据ID
     * @return 字典数据信息
     */
    @Override
    public DictData selectDictDataById(Long id) {
        return dictDataMapper.selectDictDataById(id);
    }

    /**
     * 查询字典数据列表
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合
     */
    @Override
    public List<DictData> selectDictDataList(DictData dictData) {
        return dictDataMapper.selectDictDataList(dictData);
    }

    /**
     * 新增字典数据
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(DictData dictData) {
        dictData.setCreator(UserUtils.getUserId());
        dictData.setUpdater(UserUtils.getUserId());
        if (dictData.getDictSort() == null) {
            dictData.setDictSort(dictDataMapper.getMaxDictSort());
        }
        return dictDataMapper.insertDictData(dictData);
    }

    /**
     * 修改字典数据
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(DictData dictData) {
        dictData.setUpdater(UserUtils.getUserId());
        return dictDataMapper.updateDictData(dictData);
    }

    /**
     * 删除字典数据对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDictDataByIds(Long[] ids) {
//        return dictDataMapper.deleteDictDataByIds(Convert.toStrArray(ids));
        return dictDataMapper.deleteDictDataByIds(ids);
    }

    @Override
    public List<DictData> selectDictDataByType(String dictType) {
        return dictDataMapper.selectDictDataByType(dictType);
    }

    @Override
    public String selectDictValue(String dictType, String dictLable) {
        return dictDataMapper.selectDictValue(dictType, dictLable);
    }

    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        return dictDataMapper.selectDictLabel(dictType, dictValue);
    }

    @Override
    public int checkDictLabelUnique(String dictType,String dicLabel) {
        // 修改自己时可以不修改字典标签
//        if(StringUtils.equals(oldDictLabel, dicLabel)) {
//            return SystemModualConstants.UNIQUE_CODE;
//        }
        return dictDataMapper.checkDictLabelUnique(dictType, dicLabel);
    }

    @Override
    public int checkDictValueUnique(String dictType, String dictValue) {
        return dictDataMapper.checkDictValueUnique(dictType, dictValue);
    }
}
