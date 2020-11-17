package com.deloitte.framework.utils;//package com.deloitte.framework.utils;
//
//import com.deloitte.common.utils.StringUtils;
//import com.deloitte.common.utils.bean.BeanUtils;
//import com.deloitte.framework.shiro.realm.UserRealm;
//import com.deloitte.system.model.SysUser;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.crypto.SecureRandomNumberGenerator;
//import org.apache.shiro.mgt.RealmSecurityManager;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.subject.SimplePrincipalCollection;
//import org.apache.shiro.subject.Subject;
//
///**
// * shiro 工具类
// *
// * @author p&s
// */
//public class ShiroUtils
//{
//    public static Subject getSubjct()
//    {
//        return SecurityUtils.getSubject();
//    }
//
//    public static Session getSession()
//    {
//        return SecurityUtils.getSubject().getSession();
//    }
//
//    public static void logout()
//    {
//        getSubjct().logout();
//    }
//
//    public static SysUser getUser()
//    {
//        SysUser user = null;
//        Object obj = getSubjct().getPrincipal();
//        if (StringUtils.isNotNull(obj))
//        {
//            user = new SysUser();
//            BeanUtils.copyBeanProp(user, obj);
//        }
//        return user;
//    }
//
//    public static void setUser(SysUser user)
//    {
//        Subject subject = getSubjct();
//        PrincipalCollection principalCollection = subject.getPrincipals();
//        String realmName = principalCollection.getRealmNames().iterator().next();
//        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
//        // 重新加载Principal
//        subject.runAs(newPrincipalCollection);
//    }
//
//    public static void clearCachedAuthorizationInfo()
//    {
//        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
//        UserRealm realm = (UserRealm) rsm.getRealms().iterator().next();
//        realm.clearCachedAuthorizationInfo();
//    }
//
//    public static Long getUserId()
//    {
//        return getUser().getUserId().longValue();
//    }
//
//    public static String getStringUserId() {
//    	return ShiroUtils.getUserId().toString();
//    }
//
//    public static String getLoginName()
//    {
//        return getUser().getLoginName();
//    }
//
//    public static String getIp()
//    {
//        return getSubjct().getSession().getHost();
//    }
//
//    public static String getSessionId()
//    {
//        return String.valueOf(getSubjct().getSession().getId());
//    }
//
//    /**
//     * 生成随机盐
//     */
//    public static String randomSalt()
//    {
//        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
//        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
//        String hex = secureRandom.nextBytes(3).toHex();
//        return hex;
//    }
//}
