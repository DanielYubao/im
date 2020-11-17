package com.deloitte.system.service.sys.impl;


import com.deloitte.common.utils.Convert;
import com.deloitte.system.mapper.sys.UserGroupRelMapper;
import com.deloitte.system.model.sys.UserGroupRel;
import com.deloitte.system.service.UserGroupRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户和组的关系 服务层实现
 *
 * @author ps-auto
 * @date 2019-06-21
 */
@Service
public class UserGroupRelServiceImpl implements UserGroupRelService {
    @Autowired
    private UserGroupRelMapper userGroupRelMapper;

    /**
     * 查询用户和组的关系信息
     *
     * @param id 用户和组的关系ID
     * @return 用户和组的关系信息
     */
    @Override
    public UserGroupRel selectUserGroupRelById(Long id) {
        return userGroupRelMapper.selectUserGroupRelById(id);
    }

    /**
     * 查询用户和组的关系列表
     *
     * @param userGroupRel 用户和组的关系信息
     * @return 用户和组的关系集合
     */
    @Override
    public List<UserGroupRel> selectUserGroupRelList(UserGroupRel userGroupRel) {
        return userGroupRelMapper.selectUserGroupRelList(userGroupRel);
    }

    /**
     * 新增用户和组的关系
     *
     * @param userGroupRel 用户和组的关系信息
     * @return 结果
     */
    @Override
    public int insertUserGroupRel(UserGroupRel userGroupRel) {
        return userGroupRelMapper.insertUserGroupRel(userGroupRel);
    }

    /**
     * 修改用户和组的关系
     *
     * @param userGroupRel 用户和组的关系信息
     * @return 结果
     */
    @Override
    public int updateUserGroupRel(UserGroupRel userGroupRel) {
        return userGroupRelMapper.updateUserGroupRel(userGroupRel);
    }

    /**
     * 删除用户和组的关系对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserGroupRelByIds(String ids) {
        return userGroupRelMapper.deleteUserGroupRelByIds(Convert.toLongArray(ids));
    }

    @Override
    public void updateUserGroupRelByUserId(UserGroupRel userGroupRel) {
        userGroupRelMapper.updateUserGroupRelByUserId(userGroupRel);
    }

}
