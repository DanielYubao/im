package com.deloitte.system.service;


import com.deloitte.system.model.sys.DictData;

import java.util.List;

/**
 * 字典数据 服务层
 * 
 * @author ps-auto
 * @date 2019-06-25
 */
public interface DictDataService
{
	/**
     * 查询字典数据信息
     * 
     * @param id 字典数据ID
     * @return 字典数据信息
     */
	public DictData selectDictDataById(Long id);
	
	/**
     * 查询字典数据列表
     * 
     * @param SysDictData 字典数据信息
     * @return 字典数据集合
     */
	public List<DictData> selectDictDataList(DictData dictData);
	
	/**
     * 新增字典数据
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
	public int insertDictData(DictData dictData);
	
	/**
     * 修改字典数据
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
	public int updateDictData(DictData dictData);
		
	/**
     * 删除字典数据信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDictDataByIds(Long[] ids);

	/**
	 * 根据字典类型查询字典数据信息
	 * @param dictType
	 * @return
	 */
	public List<DictData> selectDictDataByType(String dictType);

	/**
	 * 根据字典类型和字典标签查询字典数据信息
	 *
	 * @param dictType 字典类型
	 * @param dictLable 字典标签
	 * @return 字典键值
	 */
	public String selectDictValue(String dictType, String dictLable);

	/**
	 * 根据字典类型和字典键值查询字典数据信息
	 *
	 * @param dictType 字典类型
	 * @param dictValue 字典键值
	 */
	public String selectDictLabel(String dictType, String dictValue);

	/**
	 * 检验字典标签是否唯一
	 * @param dictType
	 * @param dicLabel
	 * @return
	 */
	public int checkDictLabelUnique(String dictType,String dicLabel);

	/**
	 * 检验字典值是否唯一
	 * @param dictType
	 * @param dictValue
	 * @return
	 */
	public int checkDictValueUnique(String dictType, String dictValue);

}
