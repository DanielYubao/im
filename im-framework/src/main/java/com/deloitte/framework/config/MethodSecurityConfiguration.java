package com.deloitte.framework.config;

import com.deloitte.framework.security.provisioning.CustomerJdbcUserDetailsManager;
import com.deloitte.framework.security.access.PermissionsAuthorizationAdviceVoter;
import com.deloitte.framework.security.annotation.PermissionsAnnotationMetadataExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.annotation.SecuredAnnotationSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.sql.DataSource;
import java.util.List;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration{
	
	public AccessDecisionManager accessDecisionManager() {
		AffirmativeBased manager = (AffirmativeBased)super.accessDecisionManager();
		List<AccessDecisionVoter<? extends Object>> decisionVoters = manager.getDecisionVoters();
		decisionVoters.add(permissionsAuthorizationAdviceVoter());
		return manager;
	}
	
	public MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
		return new SecuredAnnotationSecurityMetadataSource(permissionsAnnotationMetadataExtractor());
	}
	
	private PermissionsAnnotationMetadataExtractor permissionsAnnotationMetadataExtractor() {
		return new PermissionsAnnotationMetadataExtractor();
	}
	
	private PermissionsAuthorizationAdviceVoter permissionsAuthorizationAdviceVoter() {
		PermissionsAuthorizationAdviceVoter voter = new PermissionsAuthorizationAdviceVoter();
		return voter;
	}
	
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) throws Exception {
    	CustomerJdbcUserDetailsManager manager = new CustomerJdbcUserDetailsManager(dataSource);
        return manager;
    }

}
