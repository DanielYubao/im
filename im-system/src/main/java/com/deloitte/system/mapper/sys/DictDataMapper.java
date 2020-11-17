package com.deloitte.system.mapper.sys;


import com.deloitte.system.model.sys.DictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典数据 数据层
 * 
 * @author ps-auto
 * @date 2019-06-25
 */
public interface DictDataMapper
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
     * @param dictData 字典数据信息
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
     * 删除字典数据
     * 
     * @param id 字典数据ID
     * @return 结果
     */
	public int deleteDictDataById(Long id);
	
	/**
     * 批量删除字典数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDictDataByIds(Long[] ids);

	/**
	 * 根据字典类型查询字典数据
	 *
	 * @param dictType 字典类型
	 * @return 字典数据集合信息
	 */
	public List<DictData> selectDictDataByType(String dictType);

	/**
	 * 根据字典类型和字典标签查询字典数据信息
	 *
	 * @param dictType 字典类型
	 * @param dictLable 字典标签
	 */
	public String selectDictValue(@Param("dict")String dictType, @Param("dictLable")  String dictLable);

	/**
	 * 根据字典类型和字典键值查询字典数据信息
	 *
	 * @param dictType 字典类型
	 * @param dictValue 字典键值
	 */
	public String selectDictLabel(@Param("dict") String dictType, @Param("dictValue") String dictValue);

	/**
	 * 同步修改字典数据表中的字典类型
	 *
	 * @param oldDictType 旧字典类型
	 * @param newDictType 新旧字典类型
	 * @return 结果
	 */
	public int updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType);

	/**
	 * 检验字典标签是否唯一
	 * @param dictType
	 * @param dictLabel
	 * @return
	 */
	public int checkDictLabelUnique(@Param("dictType") String dictType, @Param("dictLabel") String dictLabel);

	/**
	 * 检验字典值是否唯一
	 * @param dictType
	 * @param dictValue
	 * @return
	 */
	public int checkDictValueUnique(@Param("dictType") String dictType, @Param("dictValue") String dictValue);

	/**
	 * 根据字典类型删除数据
	 * @param dictType
	 * @return
	 */
	public int countDictDataByType(String dictType);

	/**
	 * 获取最大的排序号
	 * @return
	 */
	public int getMaxDictSort();


}