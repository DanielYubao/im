package com.deloitte.framework.test;

import com.deloitte.common.annotation.Permissions;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
	
	@Permissions("smd:user:view")
	@RequestMapping(path="/test/hello")
	public String test() {
		return "hello";
	}
	
	@Permissions({"smd:user:add","smd:user:view"})
	@RequestMapping(path="/test/hello2")
	public String test2() {
		return "hello";
	}
	
	public static void main(String[] args) {
		MessageDigestPasswordEncoder en = new MessageDigestPasswordEncoder("MD5");
		System.out.println(en.encode("123"));
	}
}
