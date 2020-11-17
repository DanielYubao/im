package com.deloitte.framework.security.access;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;

public class PermissionsAttribute implements ConfigAttribute,GrantedAuthority {
	
	public static final String EMPTY_STRING = "";
	
    protected static final String WILDCARD_TOKEN = "*";
    protected static final String PART_DIVIDER_TOKEN = ":";
    protected static final String SUBPART_DIVIDER_TOKEN = ",";
    protected static final boolean DEFAULT_CASE_SENSITIVE = false;
    
    private String attr;
    
    private List<Set<String>> parts;

	private static final long serialVersionUID = 7822916613985526845L;

	public PermissionsAttribute(String value) {
		this.setParts(value,DEFAULT_CASE_SENSITIVE);
	}
	
	public boolean implies(PermissionsAttribute wp) {
        List<Set<String>> otherParts = wp.getParts();
        int i = 0;
        for (Set<String> otherPart : otherParts) {
            // If this permission has less parts than the other permission, everything after the number of parts contained
            // in this permission is automatically implied, so return true
            if (getParts().size() - 1 < i) {
                return true;
            } else {
                Set<String> part = getParts().get(i);
                if (!part.contains(WILDCARD_TOKEN) && !part.containsAll(otherPart)) {
                    return false;
                }
                i++;
            }
        }

        // If this permission has more parts than the other parts, only imply it if all of the other parts are wildcards
        for (; i < getParts().size(); i++) {
            Set<String> part = getParts().get(i);
            if (!part.contains(WILDCARD_TOKEN)) {
                return false;
            }
        }

        return true;
	}
	
    private List<Set<String>> getParts() {
        return this.parts;
    }
    
    private void setParts(List<Set<String>> parts) {
        this.parts = parts;
    }
    
    protected void setParts(String wildcardString, boolean caseSensitive) {
        wildcardString = this.clean(wildcardString);

        if (wildcardString == null || wildcardString.isEmpty()) {
            throw new IllegalArgumentException("Wildcard string cannot be null or empty. Make sure permission strings are properly formatted.");
        }

        if (!caseSensitive) {
            wildcardString = wildcardString.toLowerCase();
        }

        List<String> parts = this.asList(wildcardString.split(PART_DIVIDER_TOKEN));

        this.parts = new ArrayList<Set<String>>();
        for (String part : parts) {
            Set<String> subparts = this.asSet(part.split(SUBPART_DIVIDER_TOKEN));

            if (subparts.isEmpty()) {
                throw new IllegalArgumentException("Wildcard string cannot contain parts with only dividers. Make sure permission strings are properly formatted.");
            }
            this.parts.add(subparts);
        }

        if (this.parts.isEmpty()) {
            throw new IllegalArgumentException("Wildcard string cannot contain only dividers. Make sure permission strings are properly formatted.");
        }
    }

	public String getAttribute() {
		return this.parts.toString();
	}
	
	private static String clean(String in) {
        String out = in;

        if (in != null) {
            out = in.trim();
            if (out.equals(EMPTY_STRING)) {
                out = null;
            }
        }
        return out;
    }
	
    private static <E> List<E> asList(E... elements) {
        if (elements == null || elements.length == 0) {
            return Collections.emptyList();
        }
        // Integer overflow does not occur when a large array is passed in because the list array already exists
        return Arrays.asList(elements);
    }
    
    private static <E> Set<E> asSet(E... elements) {
        if (elements == null || elements.length == 0) {
            return Collections.emptySet();
        }
        if (elements.length == 1) {
            return Collections.singleton(elements[0]);
        }
        LinkedHashSet<E> set = new LinkedHashSet<E>(elements.length * 4 / 3 + 1);
        Collections.addAll(set, elements);
        return set;
    }

	public String getAuthority() {
		return this.getAttribute();
	}

}
