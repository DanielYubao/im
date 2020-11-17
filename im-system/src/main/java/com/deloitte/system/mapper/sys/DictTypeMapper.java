package com.deloitte.system.mapper.sys;
import com.deloitte.system.model.sys.DictType;

import java.util.List;

/**
 * 典类型 数据层
 * 
 * @author ps-auto
 * @date 2019-06-21
 */
public interface DictTypeMapper
{
	/**
     * 查询典类型信息
     * 
     * @param id 典类型ID
     * @return 典类型信息
     */
	public DictType selectDictTypeById(Long id);
	
	/**
     * 查询典类型列表
     * 
     * @param dictType 典类型信息
     * @return 典类型集合
     */
	public List<DictType> selectDictTypeList(DictType dictType);
	
	/**
     * 新增典类型
     * 
     * @param dictType 典类型信息
     * @return 结果
     */
	public int insertDictType(DictType dictType);
	
	/**
     * 修改典类型
     * 
     * @param dictType 典类型信息
     * @return 结果
     */
	public int updateDictType(DictType dictType);
	
	/**
     * 删除典类型
     * 
     * @param id 典类型ID
     * @return 结果
     */
	public int deleteDictTypeById(Long id);
	
	/**
     * 批量删除典类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDictTypeByIds(String[] ids);


	/**
	 * 检验字典分类编码是否唯一
	 * @param classifyCode
	 * @return
	 */
	public int checkDictTypeUnique(String classifyCode);

	/**
	 * 检验字典名称是否唯一
	 * @param dictName
	 * @return
	 */
	public int checkDictNameUnique(String dictName);
	
}