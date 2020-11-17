package com.deloitte.system.service.sys.impl;

import com.deloitte.system.mapper.sys.GroupMapper;
import com.deloitte.system.model.sys.Group;
import com.deloitte.system.service.GroupService;
import com.deloitte.system.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组 服务层实现
 *
 * @author ps-auto
 * @date 2019-06-21
 */
@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupMapper groupMapper;

    /**
     * 查询组信息
     *
     * @param id 组ID
     * @return 组信息
     */
    @Override
    public Group selectGroupById(Long id) {
        return groupMapper.selectGroupById(id);
    }

    /**
     * 查询组列表
     *
     * @param SysGroup 组信息
     * @return 组集合
     */
    @Override
    public List<Group> selectGroupList(Group group) {
        return groupMapper.selectGroupList(group);
    }

    /**
     * 新增组
     *
     * @param SysGroup 组信息
     * @return 结果
     */
    @Override
    public int insertGroup(Group group) {
        group.setCreator(UserUtils.getUserId());
        group.setUpdater(UserUtils.getUserId());
        return groupMapper.insertGroup(group);
    }

    /**
     * 修改组
     *
     * @param SysGroup 组信息
     * @return 结果
     */
    @Override
    public int updateGroup(Group group) {
        group.setUpdater(UserUtils.getUserId());
        return groupMapper.updateGroup(group);
    }

    /**
     * 删除组对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGroupByIds(Long[] ids) {
        return groupMapper.deleteGroupByIds(ids);
    }

    /**
     * 检验组名是否唯一
     * @param groupName
     * @return
     */
    @Override
    public int checkGroupNameUnique(String groupName) {
        return groupMapper.checkGroupNameUnique(groupName);
    }

    @Override
    public Group selectGroupByUserId(Long id) {
        if(id == null){
            return null;
        }
        return groupMapper.selectGroupByUserId(id);
    }
}
