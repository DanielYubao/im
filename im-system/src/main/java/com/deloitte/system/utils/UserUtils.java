package com.deloitte.system.utils;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Auther: QiaoYubao
 * @Date: 27/06/2019
 * @Description: 用户工具类
 */
public class UserUtils {


    /**
     * 获取用户id
     * @return
     */
    public static Long getUserId(){
        org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Long.parseLong(currentUser.getUsername());
    }
}
