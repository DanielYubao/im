package com.deloitte.framework.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	private final static String DEF_VIEW_PREFIX = "framework/web";
	
	@RequestMapping(path="/login",method=RequestMethod.GET)
	public String loginView() {
		return DEF_VIEW_PREFIX + "/login";
	}

}
