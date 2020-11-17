package com.deloitte.system.mapper.sys;

import com.deloitte.system.model.sys.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户 数据层
 *
 * @author ps-auto
 * @date 2019-06-21
 */
public interface UserMapper {
    /**
     * 查询用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    public User selectUserById(Long id);

    /**
     * 查询用户列表
     *
     * @param user 用户信息
     * @return 用户集合
     */
    public List<User> selectUserList(User user);

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(User user);

    /**
     * 修改用户
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(User user);

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 结果
     */
    public int deleteUserById(Long id);

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserByIds(Long[] ids);

    User selectUserByUsername(@Param("username") String username);

    Integer updatePassword(User user);
}