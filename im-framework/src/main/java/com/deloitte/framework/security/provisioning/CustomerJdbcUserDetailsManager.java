package com.deloitte.framework.security.provisioning;

import com.deloitte.common.utils.StringUtils;
import com.deloitte.framework.security.access.PermissionsAttribute;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class CustomerJdbcUserDetailsManager extends JdbcUserDetailsManager{
	
	private static final String DEF_ROLE_PREFIX = "ROLE_";

	private static final String DEF_CUST_USERS_BY_USERNAME_QUERY = "select id,password,(!(disable_flag + delete_flag)) as enable "
			+ "from sys_users " + "where delete_flag = 0 and binary login_name = ?";

	private static final String DEF_CUST_GROUP_AUTHORITIES_BY_USERNAME_QUERY = " SELECT r.code,r.name,m.permission "
			+ " FROM sys_users u"
			+ " LEFT JOIN sys_user_role_rel ur ON ur.user_id = u.id"
			+ " LEFT JOIN sys_roles r ON r.id = ur.role_id"
			+ " LEFT JOIN sys_role_menu_rel rm ON ur.role_id = rm.role_id  "
			+ " LEFT JOIN sys_menus m ON rm.menu_id = m.id "
			+ "where u.id = ?";


	public CustomerJdbcUserDetailsManager(DataSource dataSource) {
		super(dataSource);
		this.setUsersByUsernameQuery(DEF_CUST_USERS_BY_USERNAME_QUERY);
		this.setEnableAuthorities(false);
		this.setEnableGroups(true);
	}
	
	protected List<GrantedAuthority> loadUserAuthorities(String username) {
		return new ArrayList<GrantedAuthority>();
	}
	
	protected List<GrantedAuthority> loadGroupAuthorities(String username) {
		List<String[]> roles = this.getJdbcTemplate().query(CustomerJdbcUserDetailsManager.DEF_CUST_GROUP_AUTHORITIES_BY_USERNAME_QUERY,
				new String[] { username }, new RowMapper<String[]>() {
					public String[] mapRow(ResultSet rs, int rowNum) throws SQLException {
						String roleCode = rs.getString(1);
						String permission = rs.getString(3);
						return new String[]{roleCode,permission};
				}
		});
		Set<String> roleSet = new HashSet<String>();
		Set<String> pSet = new HashSet<String>();
		for(String[] role:roles) {
			if(!roleSet.contains(role[0])) {
				roleSet.add(role[0]);
			}
			if(!StringUtils.isEmpty(role[1])){
				pSet.add(role[1]);
			}
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.addAll(createPermisssionsAuthority(pSet));
		authorities.addAll(createRoleAuthority(roleSet));
		return authorities;
	}
	
	private List<GrantedAuthority> createPermisssionsAuthority(Collection<String> pSet){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(String permission: pSet) {
			GrantedAuthority authority = new PermissionsAttribute(permission);
			authorities.add(authority);
		}
		return authorities;
	}
	
	private List<GrantedAuthority> createRoleAuthority(Collection<String> roleSet) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(String roleCode: roleSet) {
			GrantedAuthority authority = new SimpleGrantedAuthority(this.getRolePrefix() + roleCode);
			authorities.add(authority);
		}
		return authorities;
	}
	
}
