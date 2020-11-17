package com.deloitte.framework.security.access;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class PermissionsAuthorizationAdviceVoter implements AccessDecisionVoter<MethodInvocation>{

	public boolean supports(ConfigAttribute attribute) {
		return attribute instanceof PermissionsAttribute;
	}

	public boolean supports(Class<?> clazz) {
		return MethodInvocation.class.isAssignableFrom(clazz);
	}

	public int vote(Authentication authentication, MethodInvocation method, Collection<ConfigAttribute> attributes) {
		List<PermissionsAttribute> attrs = this.findPermissionsConfig(attributes);
		if (attrs == null || attrs.size() == 0) {
			return ACCESS_ABSTAIN;
		}
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
		for(GrantedAuthority authoritie : authorities) {
			if(authoritie instanceof PermissionsAttribute) {
				PermissionsAttribute permissions = (PermissionsAttribute)authoritie;
				for(PermissionsAttribute attr: attrs) {
					if(permissions.implies(attr)) {
						return ACCESS_GRANTED;
					}
				}
			}
		}
		return ACCESS_DENIED;
	}
	
	private List<PermissionsAttribute> findPermissionsConfig(
			Collection<ConfigAttribute> config) {
		List<PermissionsAttribute> permissions = new ArrayList<PermissionsAttribute>();
		for (ConfigAttribute attribute : config) {
			if (attribute instanceof PermissionsAttribute) {
				permissions.add((PermissionsAttribute)attribute);
			}
		}
		return permissions;
	}

}
