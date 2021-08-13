package com.javainuse.springbootsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
	
	@RequestMapping("/hello_user")
	public String getUser()
	{
		return "Hello User";
	}
	
	@RequestMapping("/hello_admin")
	public String getAdmin()
	{
		return "Hello Admin";
	}

}
