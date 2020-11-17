package com.deloitte.system.service;

import com.deloitte.system.model.sys.DictType;

import java.util.List;

/**
 * 字典类型 服务层
 * 
 * @author ps-auto
 * @date 2019-06-21
 */
public interface DictTypeService
{
	/**
     * 查询字典类型信息
     * 
     * @param id 字典类型ID
     * @return 字典类型信息
     */
	public DictType selectDictTypeById(Long id);
	
	/**
     * 查询字典类型列表
     * 
     * @param dictType 字典类型信息
     * @return 字典类型集合
     */
	public List<DictType> selectDictTypeList(DictType dictType);
	
	/**
     * 新增字典类型
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
	public int insertDictType(DictType dictType);
	
	/**
     * 修改字典类型
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
	public int updateDictType(DictType dictType);
		
	/**
     * 删除字典类型信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDictTypeByIds(String ids) throws Exception;

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
