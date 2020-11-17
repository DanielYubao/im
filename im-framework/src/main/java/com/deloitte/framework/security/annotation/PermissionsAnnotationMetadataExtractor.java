package com.deloitte.framework.security.annotation;

import com.deloitte.common.annotation.Permissions;
import com.deloitte.framework.security.access.PermissionsAttribute;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.annotation.AnnotationMetadataExtractor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PermissionsAnnotationMetadataExtractor implements AnnotationMetadataExtractor<Permissions> {
	
	public Collection<ConfigAttribute> extractAttributes(Permissions permissions) {
		String[] attributeTokens = permissions.value();
		List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>(
				attributeTokens.length);

		for (String token : attributeTokens) {
			attributes.add(new PermissionsAttribute(token));
		}

		return attributes;
	}
	
}
