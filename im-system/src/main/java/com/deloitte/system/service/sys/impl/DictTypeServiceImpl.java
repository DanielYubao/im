package com.deloitte.system.service.sys.impl;

import com.deloitte.common.utils.Convert;
import com.deloitte.system.mapper.sys.DictDataMapper;
import com.deloitte.system.mapper.sys.DictTypeMapper;
import com.deloitte.system.model.sys.DictType;
import com.deloitte.system.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 字典类型 服务层实现
 *
 * @author ps-auto
 * @date 2019-06-21
 */
@Service
public class DictTypeServiceImpl implements DictTypeService {
    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Autowired
    private DictDataMapper dictDataMapper;

    /**
     * 查询字典类型信息
     *
     * @param id 字典类型ID
     * @return 字典类型信息
     */
    @Override
    public DictType selectDictTypeById(Long id) {
        return dictTypeMapper.selectDictTypeById(id);
    }

    /**
     * 查询d字典类型列表
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合
     */
    @Override
    public List<DictType> selectDictTypeList(DictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    /**
     * 新增字典类型
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(DictType dictType) {
        return dictTypeMapper.insertDictType(dictType);
    }

    /**
     * 修改字典类型
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int updateDictType(DictType dictType) {
        DictType oldDict = dictTypeMapper.selectDictTypeById(dictType.getId());
        dictDataMapper.updateDictDataType(oldDict.getClassifyCode(), dictType.getClassifyCode());
        return dictTypeMapper.updateDictType(dictType);
    }

    /**
     * 删除字典类型对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDictTypeByIds(String ids) throws Exception {

        Long[] dictIds = Convert.toLongArray(ids);
        for (Long dictId : dictIds) {
            DictType dictType = selectDictTypeById(dictId);
            if (dictDataMapper.countDictDataByType(dictType.getClassifyCode()) > 0) {
                throw new Exception(String.format("%s已分配,不能删除", dictType.getDictName()));
            }
        }

        return dictTypeMapper.deleteDictTypeByIds(Convert.toStrArray(ids));
    }

    @Override
    public int checkDictTypeUnique(String classifyCode) {
        return dictTypeMapper.checkDictTypeUnique(classifyCode);
    }

    @Override
    public int checkDictNameUnique(String dictName) {
        return dictTypeMapper.checkDictNameUnique(dictName);
    }
}
