package com.eduardoportfolio.weblibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
	//Binding in the SecurityConfiguration
	@RequestMapping("/login")
	public String loginPage(){
		return "auth/login";
	}
}
