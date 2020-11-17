package com.deloitte.framework.utils;

import com.deloitte.framework.security.access.PermissionsAttribute;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PermissionUtil {

    public static boolean hasPermission(PermissionsAttribute per) {
        Collection<GrantedAuthority> authorities =  (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for(GrantedAuthority authorite : authorities) {
            if(authorite instanceof PermissionsAttribute) {
                PermissionsAttribute permissions = (PermissionsAttribute)authorite;
                    if(permissions.implies(per)) {
                        return true;
                    }
            }
        }
        return false;
    }

    public static boolean hasPermission(ArrayList<PermissionsAttribute> attrs) {
        Collection<GrantedAuthority> authorities =  (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for(GrantedAuthority authoritie : authorities) {
            if(authoritie instanceof PermissionsAttribute) {
                PermissionsAttribute permissions = (PermissionsAttribute)authoritie;
                for(PermissionsAttribute attr: attrs) {
                    if(permissions.implies(attr)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean hasPermission(String permissionString) {
        return PermissionUtil.hasPermission(new PermissionsAttribute(permissionString));
    }

    public static boolean hasPermission(List<String> permissionString) {
        List<PermissionsAttribute> authorities = new ArrayList<PermissionsAttribute>();
        for(String permission: permissionString) {
            PermissionsAttribute authority = new PermissionsAttribute(permission);
            authorities.add(authority);
        }
        return PermissionUtil.hasPermission(permissionString);
    }

}
